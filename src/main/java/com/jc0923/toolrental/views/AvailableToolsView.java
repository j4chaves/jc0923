package com.jc0923.toolrental.views;

import java.util.ArrayList;

import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.Cart;
import com.jc0923.toolrental.util.Inventory;
import com.jc0923.toolrental.util.UserInputHandler;

public class AvailableToolsView implements Displayable {
	
	private int numberOfAvailableTools;

	@Override
	public void display() {
		System.out.println("Available Tools To Rent");

		ArrayList<Tool> tools = new ArrayList<Tool>(Inventory.toolsList);
		numberOfAvailableTools = Inventory.toolsList.size();
		for (int i = 0; i < numberOfAvailableTools; i++) {
			System.out.print((i + 1) + ".\t");
			System.out.print("Tool Code: " + tools.get(i).getToolCode() + "\t\t");
			System.out.print("Tool Type: " + tools.get(i).getTooltype().getToolTypeName() + "\t\t");
			System.out.print("Brand: " + tools.get(i).getBrand());
			System.out.println("");
		}
		
		System.out.println("\n");
		System.out.println("Enter corresponding number of tool you wish to rent. Enter 9 to return to the main menu");
		
		handleUserInput();

	}

	@Override
	public void handleUserInput() {
		String input = UserInputHandler.getUserInput();

		if (!UserInputHandler.isValidIntInput(input)) {
			if (input.equalsIgnoreCase("Q")) {
				UserInputHandler.clearConsole();
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

			if (menuSelection > numberOfAvailableTools) {
				UserInputHandler.clearConsole();
				System.out.println("Invalid Menu Selection");
				System.out.println("\n\n");
				display();
			} else {
				boolean canAddToCart = Cart.addToolToCart(Inventory.toolsList.get(menuSelection - 1));
				UserInputHandler.clearConsole();

				if (canAddToCart) {
					System.out.println("Successfully added to cart!");
				} else {
					System.out.println("The selected tool is already in your cart");
					display();
				}
			}
		}
		
	}

}
