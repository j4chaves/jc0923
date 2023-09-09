package com.jc0923.toolrental;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jc0923.toolrental.services.HolidayService;

public class HolidayServiceTest {
	
	LocalDate startOfYear;
	LocalDate endOfYear;
	LocalDate startOfLeapYear;
	LocalDate endOfLeapYear;
	LocalDate startNotInRange;
	LocalDate endNotInRange;
	
	@BeforeEach
	void setup() {
		startOfYear = LocalDate.of(2023, 1, 1);
		endOfYear = LocalDate.of(2023, 12, 31);
		startOfLeapYear = LocalDate.of(2024, 1, 1);
		endOfLeapYear = LocalDate.of(2024, 12, 31);
		startNotInRange = LocalDate.of(2023, 8, 1);
		endNotInRange = LocalDate.of(2023, 8, 30);
	}

	@Test
	@DisplayName("Independence Day Date Range Test")
	public void isIndependenceDayInDateRangeTest() {
		assertTrue(HolidayService.isIndependenceDayInDateRange(startOfYear, endOfYear));
		assertTrue(HolidayService.isIndependenceDayInDateRange(startOfLeapYear, endOfLeapYear));
		assertTrue(HolidayService.isIndependenceDayInDateRange(startOfYear, endOfLeapYear));
		assertFalse(HolidayService.isIndependenceDayInDateRange(startNotInRange, endNotInRange));
	}
	
	@Test
	@DisplayName("Labor Day Date Range Test")
	public void isLaborDayInDateRangeTest() {
		assertTrue(HolidayService.isLaborDayInDateRange(startOfYear, endOfYear));
		assertTrue(HolidayService.isLaborDayInDateRange(startOfLeapYear, endOfLeapYear));
		assertTrue(HolidayService.isLaborDayInDateRange(startOfYear, endOfLeapYear));
		assertFalse(HolidayService.isLaborDayInDateRange(startNotInRange, endNotInRange));
	}
}

