package hu.webuni.hr.geze.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.geze.config.EmployeeConfigProperties;
import hu.webuni.hr.geze.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	EmployeeConfigProperties config;

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
		int minuteInSeconds = 60;
		int hourInMinutes = 60;
		int dayInHours = 24;
		double yearInDay = 365;
		double durationInYear = duration.getSeconds() / (minuteInSeconds * hourInMinutes * dayInHours * yearInDay);

		if (durationInYear < config.getRaise().getSmart().getLowYear()) {
			percent = 0;
		} else if (durationInYear < config.getRaise().getSmart().getMiddleYear()) {
			percent = config.getRaise().getSmart().getLowPercent();
		} else if (durationInYear < config.getRaise().getSmart().getHighYear()) {
			percent = config.getRaise().getDef().getPercent();
		} else if (durationInYear < config.getRaise().getSmart().getHigherYear()) {
			percent = config.getRaise().getSmart().getHighPercent();
		} else if (durationInYear < config.getRaise().getSmart().getSuperYear()) {
			percent = config.getRaise().getSmart().getHigherPercent();
		} else {
			percent = config.getRaise().getSmart().getPlusPercent();
		}
		return percent;
	}

	@Override
	public List<Employee> findByDatesBetween(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByNameDetail(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByPosition(String position) {
		// TODO Auto-generated method stub
		return null;
	}
	}