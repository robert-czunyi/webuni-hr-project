package hu.webuni.hr.geze.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.geze.dto.CompanyDto;
import hu.webuni.hr.geze.mapper.CompanyMapper;
import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	CompanyService comServ;

	@Autowired
	CompanyMapper companyMapper;

	@GetMapping
	public List<CompanyDto> getAll() {
		return companyMapper.companiesToDtos(comServ.findAll());
	}

//	@GetMapping(params = "full=false")
//	public List<CompanyDto> getCompanies(@RequestParam boolean full) {
//		return companies.values().stream()
//				.map(this::createCompanyWithoutEmployees)
//				.collect(Collectors.toList());
//	}
//	
//	@GetMapping(params = "full=true")
//	public List<CompanyDto> getCompaniesWithEmployees(@RequestParam boolean full) {
//		return new ArrayList<>(companies.values());
//	}

//	private CompanyDto createCompanyWithoutEmployees(CompanyDto comp) {
//		return new CompanyDto(comp.getId(), comp.getName(), comp.getAddress(), comp.getRegisterNumber(), null);
//	}

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		Company company = comServ.findById(id);
		if (company != null)
			return companyMapper.companyToDto(company);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = comServ.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		Company company = comServ.modify(id, companyMapper.dtoToCompany(companyDto));
		if (company != null)
			return companyMapper.companyToDto(company);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public List<Company> deleteCompany(@PathVariable long id) {
		companyMapper.companiesToDtos(comServ.delete(id));
		return new ArrayList<>(comServ.findAll());
	}

//	@PostMapping("/{companyId}/employees")
//	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto emp) {
//		CompanyDto companyDto = getCompanyOrNotFound(companyId);
//		companyDto.getEmployees().add(emp);
//		return companyDto;
//	}
//
//	@DeleteMapping("/{companyId}/employees/{empId}")
//	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
//		CompanyDto companyDto = getCompanyOrNotFound(companyId);
//		for (EmployeeDto employee : companyDto.getEmployees()) {
//			if (employee.getIdentifier() == empId) {
//				companyDto.getEmployees().remove(employee);
//			}
//		}
//		return companyDto;
//	}

//	@PutMapping("/{companyId}/employees")
//	public CompanyDto addNewEmployeeList(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployeeList) {
//		CompanyDto companyDto = getCompanyOrNotFound(companyId);
//		companyDto.setEmployees(newEmployeeList);
//		return companyDto;
//	}
//
//	private CompanyDto getCompanyOrNotFound(long companyId) {
//		CompanyDto companyDto = companies.get(companyId);
//		if (companyDto == null)
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		return companyDto;
//	}
}
