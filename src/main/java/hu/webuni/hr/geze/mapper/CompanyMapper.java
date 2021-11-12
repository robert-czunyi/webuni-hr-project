package hu.webuni.hr.geze.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.geze.dto.CompanyDto;
import hu.webuni.hr.geze.dto.EmployeeDto;
import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);
	
	List<Company> dtosToCompanies(List<CompanyDto> companies);
	
	@Mapping(target = "position", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	
	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoWithoutEmployeees(Company company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companysToDtosWithoutEmployeees(List<Company> companys);
}
