package hu.webuni.hr.geze.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeServiceInterface employeeServiceInterface;

	public SalaryService (EmployeeServiceInterface employeeServiceInterface) {
		this.employeeServiceInterface = employeeServiceInterface;
	}
	
	public int getRaise(Employee employee) {
		return (int)(employee.getSalary() * (employeeServiceInterface.getPayRaisePercent(employee) / 100.0 + 1));
	}
}
