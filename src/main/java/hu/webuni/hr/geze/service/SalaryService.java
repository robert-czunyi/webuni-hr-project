package hu.webuni.hr.geze.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;

	public SalaryService (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public int getRaise(Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
}
