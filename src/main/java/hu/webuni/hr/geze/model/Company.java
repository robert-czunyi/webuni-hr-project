package hu.webuni.hr.geze.model;

import java.util.ArrayList;
import java.util.List;

import hu.webuni.hr.geze.dto.EmployeeDto;

public class Company {

	private long id;
	private String name;
	private String address;
	private int registerNumber;
	List<EmployeeDto> employees = new ArrayList<>();

	public Company() {
	}

	public Company(long id, String name, String address, int registerNumber, List<EmployeeDto> employees) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.registerNumber = registerNumber;
		this.employees = employees;
	}

	public long getId() {
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

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

	
}
