package hu.webuni.hr.geze.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long identifier;
	private String name;
	//private String position;
	private int salary;
	private LocalDateTime yearInWork;
	
	@ManyToOne
	private Position position;

	@ManyToOne
	private Company company;

	public Employee() {
	}

	public Employee(Long identifier, String name, int salary, LocalDateTime yearInWork) {
		this.identifier = identifier;
		this.name = name;
		this.salary = salary;
		this.yearInWork = yearInWork;
	}
	
	public Employee(int salary, LocalDateTime yearInWork) {
		this.salary = salary;
		this.yearInWork = yearInWork;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getYearInWork() {
		return yearInWork;
	}

	public void setYearInWork(LocalDateTime yearInWork) {
		this.yearInWork = yearInWork;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
