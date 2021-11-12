package hu.webuni.hr.geze.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.model.Position;
import hu.webuni.hr.geze.model.PositionDetailsByCompany;
import hu.webuni.hr.geze.model.Qualification;
import hu.webuni.hr.geze.repository.CompanyRepository;
import hu.webuni.hr.geze.repository.EmployeeRepository;
import hu.webuni.hr.geze.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.geze.repository.PositionRepository;

@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	@Transactional
	public void clearDB() {
		companyRepository.deleteAll();
		employeeRepository.deleteAll();
	}
	
	@Transactional
	public void insertTestData(){
		Position developer = positionRepository.save(new Position("fejlesztő", Qualification.UNIVERSITY));
		Position tester = positionRepository.save(new Position("tesztelő", Qualification.HIGH_SCHOOL));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(null, "ssdf",200000, LocalDateTime.now()));
		newEmployee1.setPosition(developer);
		
		Employee newEmployee2 = employeeRepository.save(new Employee(null, "t35", 200000, LocalDateTime.now()));
		newEmployee2.setPosition(tester);
		Company newCompany = companyRepository.save(new Company(null, 10, "sdfsd", "", null));
		newCompany.addEmployee(newEmployee2);
		newCompany.addEmployee(newEmployee1);
		
		PositionDetailsByCompany pd = new PositionDetailsByCompany();
		pd.setCompany(newCompany);
		pd.setMinSalary(250000);
		pd.setPosition(developer);
		positionDetailsByCompanyRepository.save(pd);
		
		PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
		pd2.setCompany(newCompany);
		pd2.setMinSalary(200000);
		pd2.setPosition(tester);
		positionDetailsByCompanyRepository.save(pd2);
	}
}
