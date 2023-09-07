package com.jc0923.toolrental.views;

import java.util.ArrayList;

import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.Cart;
import com.jc0923.toolrental.util.UserInputHandler;

public class CartView implements Displayable{

	@Override
	public void display() {
		
		ArrayList<Tool> cart = new ArrayList<Tool>(Cart.toolsInCart);
		
		if (cart.isEmpty()) {
			UserInputHandler.clearConsole();
			System.out.println("Your cart is empty");
			return;
		} else {
			System.out.println("Cart");
			System.out.println("====");
			for (int i = 0; i < cart.size(); i++) {
				System.out.print((i + 1) + ".\t");
				System.out.print("Tool Code: " + cart.get(i).getToolCode() + "\t\t");
				System.out.print("Tool Type: " + cart.get(i).getTooltype().getToolTypeName() + "\t\t");
				System.out.print("Brand: " + cart.get(i).getBrand());
				System.out.println("");
			}
			System.out.println("====");
			System.out.println("");
			System.out.println("To remove an item from your cart, enter the item's corresponding number.");
			System.out.println("To return to the main menu, enter Q ");
		}
		
		handleUserInput();

	}

	@Override
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();
		
		if (!UserInputHandler.isValidIntInput(input)) {
			if (input.equalsIgnoreCase("Q")) {
				// Return to main menu
				return;
			} else {
				UserInputHandler.clearConsole();
				System.out.println("Invalid Input");
				System.out.println("\n\n");
				display();
			}
		} else {

			int menuSelection = UserInputHandler.isValidIntInput(input) ? Integer.parseInt(input) : 999;	// 999 is will not be a menu option and trigger default case

			if (menuSelection == 9) {

			} else if (menuSelection > Cart.toolsInCart.size()) {
				UserInputHandler.clearConsole();
				System.out.println("Invalid Menu Selection");
				System.out.println("\n\n");
				display();
			} else {
				boolean canRemoveFromCart = Cart.removeToolFromCart(menuSelection - 1);
				UserInputHandler.clearConsole();

				if (canRemoveFromCart) {
					display();
				} else {
					System.out.println("Error removing tool from cart");
					display();
				}
			}
		}
	}

}
