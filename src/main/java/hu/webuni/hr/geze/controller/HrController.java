package hu.webuni.hr.geze.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.geze.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class HrController {

	private Map<Long, EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L,  new EmployeeDto(1, "Feri", "futár", 180000, "2014.10.15. 12:15:51"));
		employees.put(2L,  new EmployeeDto(2, "Fecó", "takarító", 220000, "2017.08.16. 09:51:00"));
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employees.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		EmployeeDto employeeDto = employees.get(id);
		if(employeeDto != null)
			return ResponseEntity.ok(employeeDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		employees.put(employeeDto.getIdentifier(), employeeDto);
		return employeeDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyAirport(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if(!employees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		employeeDto.setIdentifier(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employees.remove(id);
	}
	
	@GetMapping("/query/{query}")
	public List<EmployeeDto> getSalaryMoreThan(@PathVariable int query) {
		List<EmployeeDto> employs = new ArrayList<>();
		for(EmployeeDto employeeDto : employees.values()) {
			if (employeeDto.getSalary() > query) {
				employs.add(employeeDto);
			}
		}
		return employs;
		}
}
