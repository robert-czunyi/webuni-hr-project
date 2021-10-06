package hu.webuni.hr.geze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.geze.service.EmployeeService;
import hu.webuni.hr.geze.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
}
