package hu.webuni.hr.geze.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.geze.service.DefaultEmployeeService;
import hu.webuni.hr.geze.service.EmployeeServiceInterface;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

	@Bean
	public EmployeeServiceInterface employeeService() {
		return new DefaultEmployeeService();
	}
}
