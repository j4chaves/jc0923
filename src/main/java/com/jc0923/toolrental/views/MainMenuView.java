package com.jc0923.toolrental.views;

import com.jc0923.toolrental.interfaces.Displayable;
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
        System.out.println("4. Exit");
        System.out.println("");
        
        handleUserInput();
    }
	
	public void handleUserInput() {
		int menuSelection = UserInputHandler.getUserInput();
    	
		switch (menuSelection) {
		case 1:
			UserInputHandler.clearConsole();
			new AvailableToolsView().display();
			break;

		case 2:
			displayCart();
			break;

		case 3:
			displayCheckout();
			break;

		case 4:
			System.out.println("Thank you for choosing JC Tool Rental!");
			System.out.println("Have a great day!");
			System.exit(1);

		default:
			UserInputHandler.clearConsole();
			System.out.println("Invalid Menu Selection");
			System.out.println("\n\n");
			break;

		}
	}
	
	private void displayCart() {
		System.out.println("Cart - to be implemented");
	}
	
	private void displayCheckout() {
		System.out.println("Checkout - to be implemented");
	}
}
