package hu.webuni.hr.geze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.config.HrConfigProperties;
import hu.webuni.hr.geze.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService{

	@Autowired
	HrConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getRaise().getDef().getPercent();
	}
}
