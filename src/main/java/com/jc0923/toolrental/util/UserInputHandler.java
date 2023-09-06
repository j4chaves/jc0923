package com.jc0923.toolrental.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInputHandler {

	public static Integer getUserInput() {
		int input = 0;
		System.out.print("Input: ");
		Scanner scanner = new Scanner(System.in);

		try {
			input = scanner.nextInt();
		} catch (NoSuchElementException e) {
			input = 999;
		}
		return input;
	}
	
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
