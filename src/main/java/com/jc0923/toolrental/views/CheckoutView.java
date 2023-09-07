package com.jc0923.toolrental.views;

import java.time.LocalDate;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.services.CheckoutService;
import com.jc0923.toolrental.util.UserInputHandler;

public class CheckoutView implements Displayable {
	
	private Checkout checkout = new Checkout();

	@Override
	public void display() {
		System.out.println("Checkout");
		System.out.println("========");
		System.out.println("");
		System.out.println("Menu:");
        System.out.println("1. Enter Number of Rental Days");
        System.out.println("2. Enter Discount Percentage");
        System.out.println("3. Enter Checkout Date");
        System.out.println("4. Generate Rental Agreement");
        System.out.println("Q. Return to Main Menu - WARNING: Doing so will reset previously entered Checkout information");
		
		handleUserInput();
	}

	@Override
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();
		
		if (!UserInputHandler.isValidIntInput(input)) {
			if (input.equalsIgnoreCase("Q")) {
				// Return to main menu
				UserInputHandler.clearConsole();
				return;
			} else {
				UserInputHandler.clearConsole();
				System.out.println("Invalid Input");
				System.out.println("\n\n");
				display();
			}
		}
		
		if (UserInputHandler.isValidIntInput(input)) {
			int menuSelection = UserInputHandler.isValidIntInput(input) ? Integer.parseInt(input) : 999;	// 999 is will not be a menu option and trigger default case
			
			switch (menuSelection) {
			case 1:
				UserInputHandler.clearConsole();
				rentalDaysInput();
				break;

			case 2:
				UserInputHandler.clearConsole();
				discountPercentageInput();
				break;

			case 3:
				UserInputHandler.clearConsole();
				checkoutDateInput();
				break;
				
			case 4:
				UserInputHandler.clearConsole();
				// TODO - Generate Rental Agreement.  Will need to validate Checkout data
				new CheckoutService().createRentalAgreement(checkout);
				break;

			default:
				UserInputHandler.clearConsole();
				System.out.println("Invalid Menu Selection");
				System.out.println("\n\n");
				break;

			}
		} else {
			
		}
	}

	private void rentalDaysInput() {
		boolean validInput = false;
		while (!validInput) {
			System.out.print("How many days will you be renting the tool(s): ");
			String input = UserInputHandler.getUserInput();
			
			if (UserInputHandler.isValidIntInput(input)) {
				int rentalDays = Integer.parseInt(input);
				if (rentalDays >= 1) {
					this.checkout.setRentalDays(rentalDays);
					break;
				}
			}
			
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Number of rental days must be a whole number greater than 0");
			System.out.println("");
		}
	}
	
	// TODO - Could rewrite this and rentalDaysInput to just be a single int input method with the console messages passed in
	private void discountPercentageInput() {
		boolean validInput = false;
		while (!validInput) {
			System.out.print("What is the discount percentage: ");
			String input = UserInputHandler.getUserInput();
			
			if (UserInputHandler.isValidIntInput(input)) {
				int rentalDays = Integer.parseInt(input);
				if (rentalDays >= 1) {
					this.checkout.setDiscountPercentage(rentalDays);
					break;
				}
			}
			
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Discount percentage must be a whole number between 0 and 100");
			System.out.println("");
		}
	}
	
	private void checkoutDateInput() {
		boolean validInput = false;
		while (!validInput) {
			System.out.print("What day will you be checking out the equipment (date format: yyyy-mm-dd): ");
			String input = UserInputHandler.getUserInput();
			
			if (UserInputHandler.isValidDateInput(input)) {
				LocalDate checkoutDate = LocalDate.parse(input);
				this.checkout.setCheckoutDate(checkoutDate);
				break;
			}
			
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Date should be formatted as yyyy-mm-dd and should be present or future date");
			System.out.println("");
		}
	}

}
