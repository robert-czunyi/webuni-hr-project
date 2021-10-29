package hu.webuni.hr.geze.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.geze.dto.EmployeeDto;
import hu.webuni.hr.geze.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);
}
