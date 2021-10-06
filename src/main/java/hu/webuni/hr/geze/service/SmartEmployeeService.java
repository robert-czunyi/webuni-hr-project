package hu.webuni.hr.geze.service;

import java.time.Duration;
import java.time.LocalDateTime;

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
		LocalDateTime fromDateTime = employee.getYearInWork();
		LocalDateTime toDateTime = LocalDateTime.now();
		Duration duration = Duration.between(fromDateTime, toDateTime);
		
		if ((duration.getSeconds() / 31536000.0) < config.getRaise().getSmart().getLowYear()) {
			percent = 0;
		}else if ((duration.getSeconds() / 31536000.0) < config.getRaise().getSmart().getMiddleYear()) {
			percent = config.getRaise().getSmart().getLowPercent();
		}else if ((duration.getSeconds() / 31536000.0) < config.getRaise().getSmart().getHighYear()) {
			percent = config.getRaise().getDef().getPercent();
		}else {
			percent = config.getRaise().getSmart().getHighPercent();
		}
		return percent;
	}

}
