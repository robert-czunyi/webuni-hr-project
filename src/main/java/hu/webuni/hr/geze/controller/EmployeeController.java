package hu.webuni.hr.geze.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.geze.dto.EmployeeDto;
import hu.webuni.hr.geze.mapper.EmployeeMapper;
import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.service.EmployeeServiceAbstract;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends EmployeeServiceAbstract{
	
	@Autowired
	EmployeeServiceAbstract empServAb;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeesToDtos(empServAb.findAll());
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = empServAb.findById(id);
		if (employee != null)
			return employeeMapper.employeeToDto(employee);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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
	public List<Employee> deleteEmployee(@PathVariable long id) {
		employeeMapper.employeesToDtos(empServAb.delete(id));
		return new ArrayList<>(empServAb.findAll());
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
