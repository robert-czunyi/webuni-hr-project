package hu.webuni.hr.geze.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.repository.EmployeeRepository;
import hu.webuni.hr.geze.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.geze.repository.PositionRepository;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	private EmployeeRepository employeeRepository;
	

	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}
	
	@Transactional
	public void raiseMinSalary(String positionName, int minSalary, long companyId) {
		positionDetailsByCompanyRepository
			.findByPositionNameAndCompanyId(positionName, companyId).forEach(pd ->
			{
				pd.setMinSalary(minSalary);
				//1. megoldás, pazarló, betölti az összes employee-t + egyesével lesznek visszaupdate-elve
//				pd.getCompany().getEmployees().forEach(e ->{
//					if(e.getPosition().getName().equals(positionName) && e.getSalary() < minSalary)
//						e.setSalary(minSalary);
//				});
			});
		
		//2. megoldás
		employeeRepository.updateSalaries(positionName, minSalary, companyId);
	}

}
