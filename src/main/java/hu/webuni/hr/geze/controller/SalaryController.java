package hu.webuni.hr.geze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.geze.service.SalaryService;

@RequestMapping("/api/salary")
@RestController
public class SalaryController {

	@Autowired
	SalaryService salaryService;
	
	@PutMapping("/{positionName}/raiseMin/{minSalary}/{companyId}")
	public void raiseMinSalary(@PathVariable String positionName, @PathVariable int minSalary, @PathVariable long companyId) {
		salaryService.raiseMinSalary(positionName, minSalary, companyId);
	}
}

