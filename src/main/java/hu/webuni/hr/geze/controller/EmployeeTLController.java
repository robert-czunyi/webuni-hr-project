package hu.webuni.hr.geze.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.geze.model.Employee;

@Controller
public class EmployeeTLController {

	private List<Employee> allEmployee = new ArrayList<>();

	{
		allEmployee.add(new Employee(1, "Teri", "futár", 180000, LocalDateTime.of(2014, 10, 15, 12, 15, 51)));
		allEmployee.add(new Employee(2, "Gizi", "takarító", 220000, LocalDateTime.of(2017, 8, 16, 9, 51, 0)));
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/listEmployee")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", allEmployee);
		model.put("newEmployee", new Employee());
		return "listEmployee";
	}

	@PostMapping("/listEmployee")
	public String addEmployee(Employee employee) {
		allEmployee.add(employee);
		return "redirect:listEmployee";
	}

	@GetMapping("/update")
	public String updateEmployee(Model oneEmployee, long id) {
		for (Employee employee : allEmployee) {
			if (employee.getIdentifier() == id) {
				oneEmployee.addAttribute(employee);
			}
		}
		return "employeeUpdate";
	}

	@PostMapping("/newUpdateEmployee")
	public String newUpdateEmployee(Employee updateEmployee) {
		for (Employee emp : allEmployee) {
			if (emp.getIdentifier() == updateEmployee.getIdentifier()) {
				emp.setName(updateEmployee.getName());
				emp.setPosition(updateEmployee.getPosition());
				emp.setSalary(updateEmployee.getSalary());
				emp.setYearInWork(updateEmployee.getYearInWork());
			}
		}
		return "redirect:listEmployee";
	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(long mid) {
		int removeNumber = 0;
		for (Employee emp : allEmployee) {
			if (emp.getIdentifier() == mid) {
				removeNumber = allEmployee.indexOf(emp);
			}
		}
		allEmployee.remove(removeNumber);
		return "redirect:listEmployee";
	}
}
