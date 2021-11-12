package hu.webuni.hr.geze.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long id;
	private int registerNumber;
	private String name;
	private String address;

	@OneToMany(mappedBy = "company")
	private List<Employee> employees;
	
	@ManyToOne
	private CompanyType companyType;

	public Company() {
	}

	public Company(Long id, int registerNumber, String name, String address,  List<Employee> employees) {
		super();
		this.id = id;
		this.registerNumber = registerNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		if(this.employees == null)
			this.employees = new ArrayList<>();
		
		this.employees.add(employee);
		employee.setCompany(this);
	}
    
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}	
}
