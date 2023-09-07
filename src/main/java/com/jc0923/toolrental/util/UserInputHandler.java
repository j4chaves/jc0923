package com.jc0923.toolrental.util;

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
		boolean isValidIntInput;
		
		try {
			Integer integer = Integer.parseInt(input);
			isValidIntInput = true;
		} catch (NumberFormatException e) {
			// TODO - Redo this.  try/catch should not be used for logic like this
			isValidIntInput = false;
		}
		
		return isValidIntInput;
	}
	
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
