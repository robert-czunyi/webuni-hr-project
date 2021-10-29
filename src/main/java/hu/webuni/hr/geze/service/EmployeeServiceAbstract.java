package hu.webuni.hr.geze.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Employee;

@Service
public abstract class EmployeeServiceAbstract {

	private Map<Long, Employee> employees = new HashMap<>();

	{
		employees.put(1L, new Employee(1, "Feri", "futár", 180000, LocalDateTime.of(2014, 10, 15, 12, 15, 51)));
		employees.put(2L, new Employee(2, "Fecó", "takarító", 220000, LocalDateTime.of(2017, 8, 16, 9, 51, 0)));
	}

	public List<Employee> findAll() {
		return new ArrayList<>(employees.values());
	}

	public Employee findById(long id) {
		return employees.get(id);
	}

	public Employee save(Employee employee) {
		employees.put(employee.getIdentifier(), employee);
		return employee;
	}

	public List<Employee> delete(long id) {
		employees.remove(id);
		return new ArrayList<>(employees.values());
	}

	public Employee modify(long id, Employee employee) {
		//employee.setIdentifier(id);
		employees.put(id, employee);
		return employee;
	}
}
