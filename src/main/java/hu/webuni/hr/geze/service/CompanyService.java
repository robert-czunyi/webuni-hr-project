package hu.webuni.hr.geze.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.model.Company;

@Service
public class CompanyService {

	private Map<Long, Company> companies = new HashMap<>();

	public List<Company> findAll() {
		return new ArrayList<>(companies.values());
	}

	public Company findById(long id) {
		return companies.get(id);
	}

	public Company save(Company company) {
		companies.put(company.getId(), company);
		return company;
	}

	public List<Company> delete(long id) {
		companies.remove(id);
		return new ArrayList<>(companies.values());
	}

	public Company modify(long id, Company company) {
		companies.put(id, company);
		return company;
	}
}
