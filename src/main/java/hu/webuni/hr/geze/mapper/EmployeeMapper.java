package hu.webuni.hr.geze.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.geze.dto.CompanyDto;
import hu.webuni.hr.geze.dto.EmployeeDto;
import hu.webuni.hr.geze.model.Company;
import hu.webuni.hr.geze.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	@Mapping(target = "position", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	
	@Mapping(target = "employees", ignore = true)
	CompanyDto companyToDto(Company c);

	@Mapping(target = "position.name", source = "position")
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
}
