package hu.webuni.hr.geze;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDateTime ldtKati = LocalDateTime.of(2019, 4, 1, 8, 10, 0);
		Employee kati = new Employee(12, "Kati", "dolgozo", 250000, ldtKati);
		System.out.println(salaryService.getRaise(kati));
		LocalDateTime ldtBela = LocalDateTime.of(1996, 10, 1, 14, 10, 0);
		Employee bela = new Employee(1, "Bela", "igazgato", 2500000, ldtBela);
		System.out.println(salaryService.getRaise(bela));
		
	}

}
