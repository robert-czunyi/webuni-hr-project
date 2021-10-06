package hu.webuni.hr.geze.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {
	
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
		private double lowYear;
		private double middleYear;
		private double highYear;
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
	}
}
