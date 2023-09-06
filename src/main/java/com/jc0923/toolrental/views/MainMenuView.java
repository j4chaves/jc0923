package com.jc0923.toolrental.views;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.jc0923.toolrental.interfaces.Displayable;

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
        
        int menuSelection = getUserMenuInput();
        	
		switch (menuSelection) {
		case 1:
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
			System.out.println("Invalid Menu Selection");
			System.out.println("");
			break;

		}
    }

	private int getUserMenuInput() {
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
	
	private void displayCart() {
		System.out.println("Cart - to be implemented");
	}
	
	private void displayCheckout() {
		System.out.println("Checkout - to be implemented");
	}
}
