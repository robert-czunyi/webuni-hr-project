package hu.webuni.hr.geze.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private long identifier;

	@NotEmpty
	private String name;

	@NotEmpty
	private String position;

	@Min(1)
	private int salary;

	@Past
	private LocalDateTime yearInWork;
	
	@ManyToOne
	private Company workPlace;

	public Employee() {
	}

	public Employee(long identifier, String name, String position, int salary, LocalDateTime yearInWork, Company workPlace) {
		this.identifier = identifier;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.yearInWork = yearInWork;
		this.workPlace = workPlace;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
	
	public Company getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(Company workPlace) {
		this.workPlace = workPlace;
	}
}
