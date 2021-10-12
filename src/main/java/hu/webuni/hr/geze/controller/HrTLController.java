package hu.webuni.hr.geze.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.geze.model.Employee;

@Controller
public class HrTLController {

	private List<Employee> allEmployee = new ArrayList<>();
	
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
}
