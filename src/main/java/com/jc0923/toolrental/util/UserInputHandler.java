package com.jc0923.toolrental.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class UserInputHandler {

	public static String getUserInput() {
		String input = new String();
		System.out.print("Input: ");
		Scanner scanner = new Scanner(System.in);

		try {
			input = scanner.next();
		} catch (NoSuchElementException e) {
			System.out.println("Error reading input");
		}
		return input;
	}
	
	public static boolean isValidIntInput(String input) {
		return StringUtils.isNumeric(input);
	}
	
	public static boolean isValidDateInput(String input) {
		boolean isValid;
		
		try {
			LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			LocalDate today = LocalDate.now();
			if (date.isBefore(today)) {
				isValid = false;
			} else {
				isValid = true;
			}
		} catch (DateTimeParseException e) {
			isValid = false;
		}
		
		return isValid;
	}
	
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
