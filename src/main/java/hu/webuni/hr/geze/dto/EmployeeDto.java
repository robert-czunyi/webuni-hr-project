package hu.webuni.hr.geze.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

public class EmployeeDto {


	private long identifier;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String position;
	
	@Min(1)
	private int salary;
	
	@Past
	private LocalDateTime yearInWork;
	
	public EmployeeDto() {
	}
	
	public EmployeeDto(long identifier, String name, String position, int salary, LocalDateTime yearInWork) {
		this.identifier = identifier;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.yearInWork = yearInWork;
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
}