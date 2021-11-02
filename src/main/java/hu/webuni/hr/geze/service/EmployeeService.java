package hu.webuni.hr.geze.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.repository.EmployeeRepository;

@Service
public class EmployeeService {

	EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

	@Transactional
	public Employee modify(long id, Employee employee) {
		if (employeeRepository.existsById(id))
			return employeeRepository.save(employee);
		else
			throw new NoSuchElementException();
	}

	public List<Employee> findByPosition(String position) {
		return employeeRepository.findByPosition(position);
	}

	public List<Employee> findByNameDetail(String nameDetail) {
		return employeeRepository.findByNameStartingWithIgnoreCase(nameDetail);
	}

	public List<Employee> findByDatesBetween(LocalDateTime start, LocalDateTime end) {
		return employeeRepository.findByYearInWorkBetween(start, end);
	}
}
