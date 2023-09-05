package com.jc0923.toolrental.domain;

import java.math.BigDecimal;

public class ToolType {
	
	private String toolTypeName;
	private BigDecimal dailyRentalCharge;
	private boolean hasWeekdayCharge;
	private boolean hasWeekendCharge;
	private boolean hasHolidayCharge;
	
	public ToolType() {}
	
	public ToolType(String toolTypeName, BigDecimal dailyRentalCharge, 
			boolean hasWeekdayCharge, boolean hasWeekendCharge, boolean hasHolidayCharge) {
		this.toolTypeName = toolTypeName;
		this.dailyRentalCharge = dailyRentalCharge;
		this.hasWeekdayCharge = hasWeekdayCharge;
		this.hasWeekendCharge = hasWeekendCharge;
		this.hasHolidayCharge = hasHolidayCharge;
	}

	public String getToolTypeName() {
		return toolTypeName;
	}

	public void setToolTypeName(String toolTypeName) {
		this.toolTypeName = toolTypeName;
	}

	public BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public boolean isHasWeekdayCharge() {
		return hasWeekdayCharge;
	}

	public void setHasWeekdayCharge(boolean hasWeekdayCharge) {
		this.hasWeekdayCharge = hasWeekdayCharge;
	}

	public boolean isHasWeekendCharge() {
		return hasWeekendCharge;
	}

	public void setHasWeekendCharge(boolean hasWeekendCharge) {
		this.hasWeekendCharge = hasWeekendCharge;
	}

	public boolean isHasHolidayCharge() {
		return hasHolidayCharge;
	}

	public void setHasHolidayCharge(boolean hasHolidayCharge) {
		this.hasHolidayCharge = hasHolidayCharge;
	}
	
}
