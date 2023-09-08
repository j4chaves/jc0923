package com.jc0923.toolrental.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class HolidayService {

	public static boolean isIndependenceDayInDateRange(LocalDate startDate, LocalDate endDate) {
		boolean inDateRange = false;
		
		//Independence day is the 185th day of the year, 186th in leap years
		if (startDate.isLeapYear() && endDate.isLeapYear()) {
			if (startDate.getDayOfYear() <= 186 && endDate.getDayOfYear() >= 186) {
				inDateRange = true;
			}
		} else if (!startDate.isLeapYear() && !endDate.isLeapYear()) {
			if (startDate.getDayOfYear() <= 185 && endDate.getDayOfYear() >= 185) {
				inDateRange = true;
			}
		} else if (startDate.isLeapYear() && !endDate.isLeapYear()) {
			if (startDate.getDayOfYear() <= 186 && endDate.getDayOfYear() >= 185) {
				inDateRange = true;
			}
		} else { //(!startDate.isLeapYear() && endDate.isLeapYear()) {
			if (startDate.getDayOfYear() <= 185 && endDate.getDayOfYear() >= 186) {
				inDateRange = true;
			}
		}
		
		return inDateRange;
	}
	
	public static boolean isLaborDayInDateRange(LocalDate startDate, LocalDate endDate) {
		boolean inDateRange = false;

		if (startDate.getMonth().getValue() <= 9 && endDate.getMonth().getValue() >= 9) {
			LocalDate laborDay = LocalDate.of(startDate.getYear(), 9, 1);
			TemporalAdjuster laborDayAdjuster = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
			laborDay.with(laborDayAdjuster);	//Set variable to first Monday of September 
			
			if (laborDay.isAfter(startDate) && laborDay.isBefore(endDate)) {
				inDateRange = true;
			}
		} else {
			inDateRange = false;
		}
		
		return inDateRange;
	}
}
