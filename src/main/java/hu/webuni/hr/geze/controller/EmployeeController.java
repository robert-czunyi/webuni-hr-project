package hu.webuni.hr.geze.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import hu.webuni.hr.geze.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController{
	
	@Autowired
	EmployeeService empServAb;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeesToDtos(empServAb.findAll());
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = empServAb.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return employeeMapper.employeeToDto(employee);
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = empServAb.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = empServAb.modify(id, employeeMapper.dtoToEmployee(employeeDto));
		if (employee != null)
			return employeeMapper.employeeToDto(employee);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		empServAb.delete(id);
	}
	
	@GetMapping(params = "position")
	public List<Employee> getByPosition(@RequestParam String position) {
		return empServAb.findByPosition(position);
	}
	
	@GetMapping(params = "name")
	public List<Employee> getByNameDetail (@RequestParam String name){
		return empServAb.findByNameDetail(name);
	}
	
	@GetMapping(params = "start, end")
	public List<Employee> getByYearInWorkBetween(@RequestParam String start, @RequestParam String end){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startDate = LocalDateTime.parse(start, formatter);
		LocalDateTime endDate = LocalDateTime.parse(end, formatter);
		return empServAb.findByDatesBetween(startDate, endDate);
	}

//	@GetMapping(params = "minSalary")
//	public List<EmployeeDto> getSalaryMoreThan(@RequestParam int minSalary) {
//		List<EmployeeDto> employs = new ArrayList<>();
//		for (EmployeeDto employeeDto : employees.values()) {
//			if (employeeDto.getSalary() > minSalary) {
//				employs.add(employeeDto);
//			}
//		}
//		return employs;
//	}

//	@PostMapping("/payRaise")
//	public int getPayRaise(@RequestBody Employee employee) {
//		return employeeService.getPayRaisePercent(employee);
//	}
}
