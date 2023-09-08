package com.jc0923.toolrental.services;

import java.time.LocalDate;

public class HolidayService {
	//TODO Write unit tests for this service
	//Need to take into account the day the holiday is observed for other logic, not necessarily in this file

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
	
//	public static boolean isLaborDayInDateRange(LocalDate startDate, LocalDate endDate) {
//		LocalDate laborDay = LocalDate.of 
//				DayOfWeek.
//	}
}
