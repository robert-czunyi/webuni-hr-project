package hu.webuni.hr.geze.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.repository.CompanyRepository;
import hu.webuni.hr.geze.repository.EmployeeRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Company addEmployee(long id, Employee employee) {
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}

	@Transactional
	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		return company;
	}

	@Transactional
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).get();
		company.getEmployees().forEach(e -> {
			e.setCompany(null);
		});
		company.getEmployees().clear();

		employees.forEach(e -> {
			employeeRepository.save(e);
			company.addEmployee(e);
		});
		return company;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Transactional
	public void delete(long id) {
		companyRepository.deleteById(id);
	}

	@Transactional
	public Company modify(Company company) {
		if (company == null || !companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}
}
