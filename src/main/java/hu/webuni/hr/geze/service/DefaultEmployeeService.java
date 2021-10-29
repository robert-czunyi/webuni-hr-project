package hu.webuni.hr.geze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.config.EmployeeConfigProperties;
import hu.webuni.hr.geze.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeServiceInterface{

	@Autowired
	EmployeeConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getRaise().getDef().getPercent();
	}
}
