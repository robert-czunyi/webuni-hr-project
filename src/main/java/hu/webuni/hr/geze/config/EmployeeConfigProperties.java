package hu.webuni.hr.geze.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class EmployeeConfigProperties {
	
	private Raise raise = new Raise();

	public Raise getRaise() {
		return raise;
	}

	public void setRaise(Raise raise) {
		this.raise = raise;
	}

	public static class Raise{
		private Default def = new Default();
		private Smart smart = new Smart();
		public Default getDef() {
			return def;
		}
		public void setDef(Default def) {
			this.def = def;
		}
		public Smart getSmart() {
			return smart;
		}
		public void setSmart(Smart smart) {
			this.smart = smart;
		}
	}
	
	public static class Default{
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}
	
	public static class Smart{
		private int lowPercent;
		private int highPercent;
		private int higherPercent;
		private int plusPercent;
		private double lowYear;
		private double middleYear;
		private double highYear;
		private double higherYear;
		private double superYear;
		
		public int getLowPercent() {
			return lowPercent;
		}
		public void setLowPercent(int lowPercent) {
			this.lowPercent = lowPercent;
		}
		public int getHighPercent() {
			return highPercent;
		}
		public void setHighPercent(int highPercent) {
			this.highPercent = highPercent;
		}
		public int getHigherPercent() {
			return higherPercent;
		}
		public void setHigherPercent(int higherPercent) {
			this.higherPercent = higherPercent;
		}
		public int getPlusPercent() {
			return plusPercent;
		}
		public void setPlusPercent(int plusPercent) {
			this.plusPercent = plusPercent;
		}
		public double getLowYear() {
			return lowYear;
		}
		public void setLowYear(double lowYear) {
			this.lowYear = lowYear;
		}
		public double getMiddleYear() {
			return middleYear;
		}
		public void setMiddleYear(double middleYear) {
			this.middleYear = middleYear;
		}
		public double getHighYear() {
			return highYear;
		}
		public void setHighYear(double highYear) {
			this.highYear = highYear;
		}
		public double getHigherYear() {
			return higherYear;
		}
		public void setHigherYear(double higherYear) {
			this.higherYear = higherYear;
		}
		public double getSuperYear() {
			return superYear;
		}
		public void setSuperYear(double superYear) {
			this.superYear = superYear;
		}
	}
}
