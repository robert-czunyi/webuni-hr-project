package hu.webuni.hr.geze;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.geze.model.Employee;
import hu.webuni.hr.geze.service.InitDbService;
import hu.webuni.hr.geze.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		Employee kati = new Employee(12, "Kati", "dolgozo", 250000, LocalDateTime.of(2019, 4, 1, 8, 10, 0));
//		System.out.println(salaryService.getRaise(kati));
//		Employee bela = new Employee(1, "Bela", "igazgato", 2500000, LocalDateTime.of(1997, 10, 1, 14, 10, 0));
//		System.out.println(salaryService.getRaise(bela));
//		Employee geza = new Employee(19, "Géza", "főigazgato", 2600000, LocalDateTime.of(1995, 10, 1, 14, 10, 0));
//		System.out.println(salaryService.getRaise(geza));
//		Employee pista = new Employee(11, "István", "raktáros", 240000, LocalDateTime.of(1980, 12, 25, 14, 10, 0));
//		System.out.println(salaryService.getRaise(pista));
	
		//initDbService.clearDB();
		initDbService.insertTestData();
	}
}
