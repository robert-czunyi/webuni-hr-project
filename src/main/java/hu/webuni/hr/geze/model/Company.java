package hu.webuni.hr.geze.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String address;
	private int registerNumber;

	@ManyToOne
	private Employee employ;

	public Company() {
	}

	public Company(long id, String name, String address, int registerNumber, Employee employ) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.registerNumber = registerNumber;
		this.employ = employ;
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

	public Employee getEmploy() {
		return employ;
	}

	public void setEmploy(Employee employ) {
		this.employ = employ;
	}
}
