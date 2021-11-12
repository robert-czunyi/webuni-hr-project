package hu.webuni.hr.geze.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.geze.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByPosition(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String nameDetail);
	
	List<Employee> findByYearInWorkBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	List<Employee> findBySalaryGreaterThan(Integer minSalary);
	
Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable page);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN(   "
			+ "SELECT e2.id FROM Employee e2 "
			+ "WHERE e2.position.name = :positionName "
			+ "AND e2.company.id=:companyId "
			+ "AND e2.salary < :minSalary)")
	void updateSalaries(String positionName, int minSalary, long companyId);
}
