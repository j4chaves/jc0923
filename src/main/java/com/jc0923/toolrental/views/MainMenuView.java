package com.jc0923.toolrental.views;

import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.Cart;
import com.jc0923.toolrental.util.UserInputHandler;

public class MainMenuView implements Displayable {

	public MainMenuView() {}
	
	public void display() {
		System.out.println("");
    	System.out.println("JC Tool Rental");
        System.out.println("");
        System.out.println("Menu:");
        System.out.println("1. View available tools");
        System.out.println("2. View cart");
        System.out.println("3. Checkout");
        System.out.println("Q. Exit");
        System.out.println("");
        
        handleUserInput();
    }
	
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();
		
		if (input.equalsIgnoreCase("Q")) {
			System.out.println("Thank you for choosing JC Tool Rental!");
			System.out.println("Have a great day!");
			System.exit(1);
		}
		
		int menuSelection = UserInputHandler.isValidIntInput(input) ? Integer.parseInt(input) : 999;	// 999 is will not be a menu option and trigger default case
    	
		switch (menuSelection) {
		case 1:
			UserInputHandler.clearConsole();
			new AvailableToolsView().display();
			break;

		case 2:
			UserInputHandler.clearConsole();
			new CartView().display();
			break;

		case 3:
			UserInputHandler.clearConsole();
//			if (!Cart.toolInCart.isEmpty()) {
			if (Cart.toolInCart != null) {
				new CheckoutView().display();;
			} else {
				System.out.println("You cannot proceed to checkout with an empty cart");
			}
			break;

		default:
			UserInputHandler.clearConsole();
			System.out.println("Invalid Menu Selection");
			break;

		}
	}
}
