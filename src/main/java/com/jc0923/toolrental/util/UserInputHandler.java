package com.jc0923.toolrental.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
		boolean isValid;
		
		try {
			Integer integer = Integer.parseInt(input);
			isValid = true;
		} catch (NumberFormatException e) {
			// TODO - Redo this.  try/catch should not be used for logic like this
			isValid = false;
		}
		
		return isValid;
	}
	
	public static boolean isValidDateInput(String input) {
		boolean isValid;
		
		try {
			LocalDate date = LocalDate.parse(input);
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
