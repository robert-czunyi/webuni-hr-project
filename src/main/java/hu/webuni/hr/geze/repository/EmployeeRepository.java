package hu.webuni.hr.geze.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.geze.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String nameDetail);
	
	List<Employee> findByYearInWorkBetween(LocalDateTime startDate, LocalDateTime endDate);
}
