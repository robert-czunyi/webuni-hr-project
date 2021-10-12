package hu.webuni.hr.geze;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.of(2019, 4, 1, 8, 10, 0);
		Employee kati = new Employee(12, "Kati", "dolgozo", 250000, ldt.format(formatter));
		System.out.println(salaryService.getRaise(kati));
		ldt = LocalDateTime.of(1997, 10, 1, 14, 10, 0);
		Employee bela = new Employee(1, "Bela", "igazgato", 2500000, ldt.format(formatter));
		System.out.println(salaryService.getRaise(bela));
		ldt = LocalDateTime.of(1995, 10, 1, 14, 10, 0);
		Employee geza = new Employee(19, "Géza", "főigazgato", 2600000, ldt.format(formatter));
		System.out.println(salaryService.getRaise(geza));
		ldt = LocalDateTime.of(1980, 12, 25, 14, 10, 0);
		Employee pista = new Employee(11, "István", "raktáros", 240000, ldt.format(formatter));
		System.out.println(salaryService.getRaise(pista));
	}
}
