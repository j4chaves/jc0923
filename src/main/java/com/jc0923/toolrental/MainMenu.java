package com.jc0923.toolrental;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainMenu {

	public MainMenu() {}
	
	public void displayHomeMenuToConsole() {
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
			displayAvailableTools();
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
	
	
	private void displayAvailableTools() {
		System.out.println("");
		System.out.println("Available Tools - to be implemented");
		
		try (FileReader fileReader = new FileReader("src/main/resources/ToolTypes.json")) {
			JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(fileReader);
			JSONArray toolTypesJSONArray = (JSONArray) object;
			
			List<ToolType> toolTypesList = new ArrayList<ToolType>();
			Iterator<JSONObject> iterator = toolTypesJSONArray.iterator();
			while (iterator.hasNext()) {
				JSONObject toolTypeJsonObject = (JSONObject)iterator.next();
				String toolTypeName = (String)toolTypeJsonObject.get("toolTypeName");
				Double dailyRentalCharge = (Double) toolTypeJsonObject.get("dailyRentalCharge");
				boolean hasWeekdayCharge = (boolean) toolTypeJsonObject.get("hasWeekdayCharge");
				boolean hasWeekendCharge = (boolean) toolTypeJsonObject.get("hasWeekendCharge");
				boolean hasHolidayCharge = (boolean) toolTypeJsonObject.get("hasHolidayCharge");
				
				ToolType toolType = new ToolType(toolTypeName, BigDecimal.valueOf(dailyRentalCharge), hasWeekdayCharge, hasWeekendCharge, hasHolidayCharge);
				toolTypesList.add(toolType);
			}
			
			System.out.println("Now printing tooltypes: ");
			toolTypesList.forEach(t -> System.out.println(t.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void displayCart() {
		System.out.println("Cart - to be implemented");
	}
	
	private void displayCheckout() {
		System.out.println("Checkout - to be implemented");
	}
}
