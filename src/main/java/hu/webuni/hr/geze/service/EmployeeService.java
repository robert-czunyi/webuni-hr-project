package hu.webuni.hr.geze.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import hu.webuni.hr.geze.model.Employee;

public interface EmployeeService {

	public int getPayRaisePercent(Employee employee);

	public Employee save(Employee employee);

	public Employee update(Employee employee);

	public List<Employee> findAll();

	public Optional<Employee> findById(long id);

	public void delete(long id);

	public List<Employee> findByDatesBetween(LocalDateTime start, LocalDateTime end);

	public List<Employee> findByNameDetail(String name);

	public List<Employee> findByPosition(String position);
}
