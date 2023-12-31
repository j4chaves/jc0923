 package com.jc0923.toolrental.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class HolidayService {

	public static boolean isIndependenceDayInDateRange(LocalDate startDate, LocalDate endDate) {
		boolean inDateRange = false;
		
		LocalDate holidayStartDateYear = LocalDate.of(startDate.getYear(), 7, 4);
		
		/*
		 * If Independence Day falls on a weekend, adjust the date to be the closest weekday.
		 * Saturday holiday is observed on Friday, Sunday holiday is observed on Monday.
		 */
		TemporalAdjuster moveToFriday = TemporalAdjusters.previous(DayOfWeek.FRIDAY);
		TemporalAdjuster moveToMonday = TemporalAdjusters.next(DayOfWeek.MONDAY);
		
		if (holidayStartDateYear.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			holidayStartDateYear = holidayStartDateYear.with(moveToFriday);
		} else if (holidayStartDateYear.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			holidayStartDateYear = holidayStartDateYear.with(moveToMonday);
		}
		
		if (startDate.getYear() == endDate.getYear()) {
				if (startDate.isBefore(holidayStartDateYear) && endDate.isAfter(holidayStartDateYear)) {
					inDateRange = true;
				}
		} else {
			LocalDate holidayEndDateYear = LocalDate.of(endDate.getYear(), 7, 4);
			
			if (holidayEndDateYear.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				holidayEndDateYear = holidayEndDateYear.with(moveToFriday);
			} else if (holidayEndDateYear.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				holidayEndDateYear = holidayEndDateYear.with(moveToMonday);
			}
			
			if (startDate.isBefore(holidayStartDateYear) || endDate.isAfter(holidayEndDateYear)) {
				inDateRange = true;
			}
		}
		
		return inDateRange;
	}
	
	public static boolean isLaborDayInDateRange(LocalDate startDate, LocalDate endDate) {
		boolean inDateRange = false;
		
		LocalDate laborDayStartDateYear = LocalDate.of(startDate.getYear(), 9, 1);
		TemporalAdjuster laborDayAdjuster = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
		laborDayStartDateYear = laborDayStartDateYear.with(laborDayAdjuster);	//Set variable to first Monday of September
		
		if (startDate.getYear() == endDate.getYear() &&
				(startDate.isBefore(laborDayStartDateYear) && endDate.isAfter(laborDayStartDateYear))) {
			inDateRange = true;
		} else {
			LocalDate laborDayEndDateYear = LocalDate.of(endDate.getYear(), 9, 1);
			laborDayEndDateYear = laborDayEndDateYear.with(laborDayEndDateYear);
			if (startDate.isBefore(laborDayStartDateYear) && endDate.isAfter(laborDayStartDateYear) ||
					startDate.isBefore(laborDayEndDateYear) && endDate.isAfter(laborDayEndDateYear)) {
				inDateRange = true;
			}
		}
		
		return inDateRange;
	}
}
