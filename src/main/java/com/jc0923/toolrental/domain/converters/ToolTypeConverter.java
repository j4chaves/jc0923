package com.jc0923.toolrental.domain.converters;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

import com.jc0923.toolrental.domain.ToolType;

public class ToolTypeConverter {

	public static ToolType fromFile(JSONObject data) {
		ToolType toolType = new ToolType();
		
		if (data != null && !data.isEmpty()) {
			String toolTypeName = (String) data.get("toolTypeName");
			Double dailyRentalCharge = (Double) data.get("dailyRentalCharge");
			boolean hasWeekdayCharge = (boolean) data.get("hasWeekdayCharge");
			boolean hasWeekendCharge = (boolean) data.get("hasWeekendCharge");
			boolean hasHolidayCharge = (boolean) data.get("hasHolidayCharge");
			
			toolType.setToolTypeName(toolTypeName);;
			toolType.setDailyRentalCharge(new BigDecimal(dailyRentalCharge));;
			toolType.setHasWeekdayCharge(hasWeekdayCharge);
			toolType.setHasWeekendCharge(hasWeekendCharge);
			toolType.setHasHolidayCharge(hasHolidayCharge);
		}
		
		return toolType;
	}
}
