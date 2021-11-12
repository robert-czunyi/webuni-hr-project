package hu.webuni.hr.geze.controller;

import java.util.List;
import java.util.NoSuchElementException;

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
import hu.webuni.hr.geze.mapper.CompanyMapper;
import hu.webuni.hr.geze.mapper.EmployeeMapper;
import hu.webuni.hr.geze.model.AverageSalaryByPosition;
import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.repository.CompanyRepository;
import hu.webuni.hr.geze.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private CompanyMapper companyMapper;
	private CompanyService companyService;
	private EmployeeMapper employeeMapper;
	private CompanyRepository companyRepository;
	
	public CompanyController(CompanyMapper companyMapper, CompanyService companyService, EmployeeMapper employeeMapper,
			CompanyRepository companyRepository) {
		super();
		this.companyMapper = companyMapper;
		this.companyService = companyService;
		this.employeeMapper = employeeMapper;
		this.companyRepository = companyRepository;
	}

	@GetMapping
	public List<CompanyDto> getAll(@RequestParam (required = false) Boolean full) {
		List<Company> companies = companyService.findAll();
		return full != null && full ? 
				companyMapper.companiesToDtos(companies) 
				: companyMapper.companysToDtosWithoutEmployeees(companies);
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
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return full != null && full ? companyMapper.companyToDto(company) 
				: companyMapper.companyToDtoWithoutEmployeees(company);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		companyDto.setId(id);
		Company updatedCompany = companyService.modify(companyMapper.dtoToCompany(companyDto));
		if (updatedCompany == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}

	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto emp) {
		try {
			return companyMapper
					.companyToDto(companyService.addEmployee(companyId, employeeMapper.dtoToEmployee(emp)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{companyId}/employees/{empId}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
		try {
			return companyMapper.companyToDto(companyService.deleteEmployee(companyId, empId));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{companyId}/employees")
	public CompanyDto addNewEmployeeList(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployeeList) {
		try {
			return companyMapper
					.companyToDto(companyService.replaceEmployees(companyId, employeeMapper.dtosToEmployees(newEmployeeList)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

//	private CompanyDto getCompanyOrNotFound(long companyId) {
//		CompanyDto companyDto = companies.get(companyId);
//		if (companyDto == null)
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		return companyDto;
//	}
	
	@GetMapping(params = "aboveSalary")
	public List<CompanyDto> getCompaniesAboveASalary(@RequestParam int aboveSalary,
			@RequestParam(required = false) Boolean full) {
		List<Company> allCompanies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
		return mapCompanies(allCompanies, full);
	}

	private List<CompanyDto> mapCompanies(List<Company> allCompanies, Boolean full) {
		if (full == null || !full) {
			return companyMapper.companysToDtosWithoutEmployeees(allCompanies);
		} else
			return companyMapper.companiesToDtos(allCompanies);
	}

	@GetMapping(params = "aboveEmployeeNumber")
	public List<CompanyDto> getCompaniesAboveEmployeeNumber(@RequestParam int aboveEmployeeNumber,
			@RequestParam(required = false) Boolean full) {
		List<Company> filteredCompanies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeNumber);
		return mapCompanies(filteredCompanies, full);
	}
	
	@GetMapping("/{id}/salaryStats")
	public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		return companyRepository.findAverageSalariesByPosition(id);
	}
}
