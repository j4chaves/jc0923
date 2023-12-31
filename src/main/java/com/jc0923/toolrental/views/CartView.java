package com.jc0923.toolrental.views;

import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.Cart;
import com.jc0923.toolrental.util.UserInputHandler;

public class CartView implements Displayable {

	@Override
	public void display() {
		if (Cart.toolInCart == null) {
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Your cart is empty");
			return;
		} else {
			System.out.println("Cart");
			System.out.println("====");
			System.out.print("Tool Code: " + Cart.toolInCart.getToolCode() + "\t\t");
			System.out.print("Tool Type: " + Cart.toolInCart.getTooltype().getToolTypeName() + "\t\t");
			System.out.print("Brand: " + Cart.toolInCart.getBrand());
			System.out.println("");
			System.out.println("====");
			System.out.println("");
			System.out.println("To remove an item from your cart, enter R");
			System.out.println("To return to the main menu, enter Q");
		}

		handleUserInput();

	}

	@Override
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();

		if (input.equalsIgnoreCase("Q")) {
			// Return to main menu
			UserInputHandler.clearConsole();
			return;
		} else if (input.equalsIgnoreCase("R")) {
			Cart.toolInCart = null;
			UserInputHandler.clearConsole();
			System.out.println("Cart has been emptied\n");
			display();
		} else {
			UserInputHandler.clearConsole();
			System.out.println("ERROR: Invalid Input\n");
			display();
		}
	}
}
