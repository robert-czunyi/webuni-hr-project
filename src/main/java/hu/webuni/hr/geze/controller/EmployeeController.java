package hu.webuni.hr.geze.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.geze.dto.EmployeeDto;
import hu.webuni.hr.geze.mapper.EmployeeMapper;
import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.repository.EmployeeRepository;
import hu.webuni.hr.geze.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<EmployeeDto> getAll(@RequestParam(required = false) Integer minSalary, Pageable page) {
		List<Employee> employees = null;
		if(minSalary == null) {
			employees = employeeService.findAll();
		} else {
//			employees = employeeRepository.findBySalaryGreaterThan(minSalary);
			Page<Employee> empPage = employeeRepository.findBySalaryGreaterThan(minSalary, page);
			System.out.println(empPage.getNumber());
			System.out.println(empPage.isLast());
			System.out.println(empPage.isFirst());
			System.out.println(empPage.getSize());
			System.out.println(empPage.getTotalElements());
			System.out.println(empPage.getTotalPages());
			employees = empPage.getContent();
		}
		return employeeMapper.employeesToDtos(employees);
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = findByIdOrThrow(id);
		return employeeMapper.employeeToDto(employee);
	}
	
	private Employee findByIdOrThrow(long id) {
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setIdentifier(id);
		Employee employee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if (employee != null)
			return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
	
	@GetMapping(params = "position")
	public List<Employee> getByPosition(@RequestParam String position) {
		return employeeService.findByPosition(position);
	}
	
	@GetMapping(params = "name")
	public List<Employee> getByNameDetail (@RequestParam String name){
		return employeeService.findByNameDetail(name);
	}
	
	@GetMapping(params = {"start", "end"})
	public List<Employee> getByYearInWorkBetween(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end){
		return employeeService.findByDatesBetween(start, end);
	}

	@GetMapping(params = "minSalary")
	public List<EmployeeDto> getSalaryMoreThan(@RequestParam int minSalary) {
		List<EmployeeDto> employs = new ArrayList<>();
		for (EmployeeDto employeeDto : employeeMapper.employeesToDtos(employeeService.findAll())) {
			if (employeeDto.getSalary() > minSalary) {
				employs.add(employeeDto);
			}
		}
		return employs;
	}

	@PostMapping("/payRaise")
	public int getPayRaise(@RequestBody Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
}
