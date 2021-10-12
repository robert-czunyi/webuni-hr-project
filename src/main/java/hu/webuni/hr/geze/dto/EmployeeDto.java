package hu.webuni.hr.geze.dto;

public class EmployeeDto {

	private long identifier;
	private String name;
	private String position;
	private int salary;
	private String yearInWork;
	
	public EmployeeDto() {
	}
	
	public EmployeeDto(long identifier, String name, String position, int salary, String yearInWork) {
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

	public String getYearInWork() {
		return yearInWork;
	}

	public void setYearInWork(String yearInWork) {
		this.yearInWork = yearInWork;
	}
}