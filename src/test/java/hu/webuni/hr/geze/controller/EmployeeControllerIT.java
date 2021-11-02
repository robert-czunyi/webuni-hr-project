package hu.webuni.hr.geze.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.geze.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

	private static final String BASE_URI = "/api/employees";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	EmployeeController employeeController;

	@Test
	void createEmployeeWithOkData() throws Exception {
		LocalDateTime startWork = LocalDateTime.of(2019, 10, 25, 8, 0, 0);
		EmployeeDto newEmployee = new EmployeeDto(9L, "Mancika", "üzemorvos", 120000, startWork);
		webTestClient.post().uri(BASE_URI).bodyValue(newEmployee).exchange().expectStatus().isOk();

		assertThat(newEmployee.getName()).isNotEmpty();
		assertThat(newEmployee.getPosition()).isNotEmpty();
		assertThat(newEmployee.getSalary()).isGreaterThan(1);
		assertThat(newEmployee.getYearInWork()).isBefore(LocalDateTime.now());
	}

	@Test
	void createEmployeeWithNOkData() throws Exception {
		LocalDateTime startWork = LocalDateTime.of(2022, 05, 15, 8, 0, 0);
		EmployeeDto newEmployee = new EmployeeDto(6L, "Marcika", "aszisztens", 140000, startWork);
		webTestClient.post().uri(BASE_URI).bodyValue(newEmployee).exchange().expectStatus().isOk();

		assertThat(newEmployee.getName()).isNotEmpty();
		assertThat(newEmployee.getPosition()).isNotEmpty();
		assertThat(newEmployee.getSalary()).isGreaterThan(1);
		assertThat(newEmployee.getYearInWork()).isBefore(LocalDateTime.now());
	}

	@Test
	void modifyEmployeeWithOkData() throws Exception {
		LocalDateTime startWork = LocalDateTime.of(2019, 10, 25, 8, 0, 0);
		EmployeeDto newEmployee = new EmployeeDto(1L, "Mancika", "üzemorvos", 120000, startWork);
		employeeController.modifyEmployee(newEmployee.getIdentifier(), newEmployee);
		for (EmployeeDto emp : employeeController.getAll()) {
			if (emp.getIdentifier() == newEmployee.getIdentifier()) {
				assertThat(newEmployee.getName()).isEqualTo(emp.getName());
				assertThat(newEmployee.getPosition()).isEqualTo(emp.getPosition());
				assertThat(newEmployee.getSalary()).isEqualTo(emp.getSalary());
				assertThat(newEmployee.getYearInWork()).isEqualTo(emp.getYearInWork());
			}
		}
	}
	
	@Test
	void modifyEmployeeWithNOkData() throws Exception {
		LocalDateTime startWork = LocalDateTime.of(2031, 10, 25, 8, 0, 0);
		EmployeeDto newEmployee = new EmployeeDto(1L, "Marcika", "üzemorvos", 140000, startWork);
		employeeController.modifyEmployee(newEmployee.getIdentifier(), newEmployee);
		for (EmployeeDto emp : employeeController.getAll()) {
			if (emp.getIdentifier() == newEmployee.getIdentifier()) {
				assertThat(newEmployee.getName()).isEqualTo(emp.getName());
				assertThat(newEmployee.getPosition()).isEqualTo(emp.getPosition());
				assertThat(newEmployee.getSalary()).isEqualTo(emp.getSalary());
				assertThat(newEmployee.getYearInWork()).isEqualTo(emp.getYearInWork());
			}
		}
	}
}
