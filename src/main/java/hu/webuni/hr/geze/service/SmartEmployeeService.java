package hu.webuni.hr.geze.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.config.HrConfigProperties;
import hu.webuni.hr.geze.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{

	@Autowired
	HrConfigProperties config;
	
//	@Value("${hr.raise.smart.lowpercent}")
//	private int lowPercent;
//	
//	@Value("${hr.raise.default.percent}")
//	private int defaultPercent;
//	
//	@Value("${hr.raise.smart.highpercent}")
//	private int highPercent;
//	
//	@Value("${hr.raise.smart.lowyear}")
//	private double lowYear;
//	
//	@Value("${hr.raise.smart.middleyear}")
//	private int middleYear;
//	
//	@Value("${hr.raise.smart.highyear}")
//	private int highYear;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		int percent;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fromDateTime = LocalDateTime.parse(employee.getYearInWork(), formatter);
		LocalDateTime toDateTime = LocalDateTime.now();
		Duration duration = Duration.between(fromDateTime, toDateTime);
		int minuteInSeconds = 60;
		int hourInMinutes = 60;
		int dayInHours = 24;
		double yearInDay = 365;
		double durationInYear = duration.getSeconds() / (minuteInSeconds * hourInMinutes * dayInHours * yearInDay);
		
		if (durationInYear < config.getRaise().getSmart().getLowYear()) {
			percent = 0;
		}else if (durationInYear < config.getRaise().getSmart().getMiddleYear()) {
			percent = config.getRaise().getSmart().getLowPercent();
		}else if (durationInYear < config.getRaise().getSmart().getHighYear()) {
			percent = config.getRaise().getDef().getPercent();
		}else if (durationInYear < config.getRaise().getSmart().getHigherYear()) {
			percent = config.getRaise().getSmart().getHighPercent();
		}else if (durationInYear < config.getRaise().getSmart().getSuperYear()) {
			percent = config.getRaise().getSmart().getHigherPercent();
		}else {
			percent = config.getRaise().getSmart().getPlusPercent();
		}
		return percent;
	}

}
