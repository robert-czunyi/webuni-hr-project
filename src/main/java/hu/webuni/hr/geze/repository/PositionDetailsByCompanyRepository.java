package hu.webuni.hr.geze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.geze.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long>{

	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);
}
