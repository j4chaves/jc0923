package com.jc0923.toolrental;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jc0923.toolrental.services.HolidayService;

public class HolidayServiceTest {
	
	@ParameterizedTest
	@DisplayName("Independence Day Valid Dates Test")
	@CsvFileSource(resources = "/independenceDayValidTestDates.csv", numLinesToSkip = 1)
	public void independenceDayValidDatesTest(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		
		assertTrue(HolidayService.isIndependenceDayInDateRange(start, end));
	}
	
	@ParameterizedTest
	@DisplayName("Labor Day Valid Dates Test")
	@CsvFileSource(resources = "/laborDayValidTestDates.csv", numLinesToSkip = 1)
	public void laborDayValidDatesTest(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		
		assertTrue(HolidayService.isLaborDayInDateRange(start, end));
	}
	
	@ParameterizedTest
	@DisplayName("Invalid Holiday Dates Test")
	@CsvFileSource(resources = "/invalidTestDates.csv", numLinesToSkip = 1)
	public void invalidDatesTest(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		
		assertFalse(HolidayService.isLaborDayInDateRange(start, end));
	}
}

