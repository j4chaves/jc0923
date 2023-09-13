package com.jc0923.toolrental.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.domain.RentalAgreement;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.services.CheckoutService;
import com.jc0923.toolrental.util.Cart;
import com.jc0923.toolrental.util.UserInputHandler;

public class CheckoutView implements Displayable {
	
	private Checkout checkout = new Checkout();
	private boolean returnToPreviousView = false;

	@Override
	public void display() {
		
		while (!returnToPreviousView) {
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
	}

	@Override
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();
		
		if (!UserInputHandler.isValidIntInput(input)) {
			if (input.equalsIgnoreCase("Q")) {
				// Return to main menu
				UserInputHandler.clearConsole();
				returnToPreviousView = true;
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
				CheckoutService checkoutService = new CheckoutService();
				RentalAgreement rentalAgreement = checkoutService.createRentalAgreement(checkout, Cart.toolInCart);
				String rentalAgreementDisplay = checkoutService.generateRentalAgreementString(rentalAgreement);
				System.out.println(rentalAgreementDisplay);
				
				break;

			default:
				UserInputHandler.clearConsole();
				System.out.println("Invalid Menu Selection");
				System.out.println("\n\n");
				break;

			}
		}
	}

	private void rentalDaysInput() {
		boolean validInput = false;
		while (!validInput) {
			System.out.print("How many days will you be renting the tool: ");
			String input = UserInputHandler.getUserInput();
			
			if (UserInputHandler.isValidIntInput(input)) {
				int rentalDays = Integer.parseInt(input);
				if (rentalDays >= 1) {
					this.checkout.setRentalDays(rentalDays);
					UserInputHandler.clearConsole();
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
				int discountPercentage = Integer.parseInt(input);
				if (discountPercentage >= 0 && discountPercentage <= 100) {
					this.checkout.setDiscountPercentage(discountPercentage);
					UserInputHandler.clearConsole();
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
			System.out.print("What day will you be checking out the equipment (date format: mm/dd/yyyy): ");
			String input = UserInputHandler.getUserInput();
			
			if (UserInputHandler.isValidDateInput(input)) {
				LocalDate checkoutDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				this.checkout.setCheckoutDate(checkoutDate);
				UserInputHandler.clearConsole();
				break;
			}
			
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Date should be formatted as mm/dd/yyyy and should be present or future date");
			System.out.println("");
		}
	}

}
