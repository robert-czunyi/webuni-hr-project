package hu.webuni.hr.geze.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.config.EmployeeConfigProperties;
import hu.webuni.hr.geze.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService{

	@Autowired
	EmployeeConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getRaise().getDef().getPercent();
	}

	@Override
	public List<Employee> findByDatesBetween(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByNameDetail(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByPosition(String position) {
		// TODO Auto-generated method stub
		return null;
	}
}
