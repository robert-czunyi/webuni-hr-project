package hu.webuni.hr.geze.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import hu.webuni.hr.geze.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	@GetMapping
	public List<CompanyDto> getAll() {
		return companies.values().stream()
				.map(this::createCompanyWithoutEmployees)
				.collect(Collectors.toList());
	}
	
	@GetMapping(params = "full=false")
	public List<CompanyDto> getCompanies(@RequestParam boolean full) {
		return companies.values().stream()
				.map(this::createCompanyWithoutEmployees)
				.collect(Collectors.toList());
	}
	
	@GetMapping(params = "full=true")
	public List<CompanyDto> getCompaniesWithEmployees(@RequestParam boolean full) {
		return new ArrayList<>(companies.values());
	}
	
	private CompanyDto createCompanyWithoutEmployees(CompanyDto comp) {
		return new CompanyDto(comp.getId(), comp.getName(), comp.getAddress(), comp.getRegisterNumber(), null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		CompanyDto companyDto = companies.get(id);
		if(companyDto != null) {
			return ResponseEntity.ok((full == null || !full) ? createCompanyWithoutEmployees(companyDto) : companyDto);
		}
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}

	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto emp) {
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		companyDto.getEmployees().add(emp);
		return companyDto;
	}

	@DeleteMapping("/{companyId}/employees/{empId}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		for (EmployeeDto employee : companyDto.getEmployees()) {
			if (employee.getIdentifier() == empId) {
				companyDto.getEmployees().remove(employee);
			}
		}
		return companyDto;
	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto addNewEmployeeList(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployeeList) {
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		companyDto.setEmployees(newEmployeeList);
		return companyDto;
	}

	private CompanyDto getCompanyOrNotFound(long companyId) {
		CompanyDto companyDto = companies.get(companyId);
		if (companyDto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return companyDto;
	}
}
