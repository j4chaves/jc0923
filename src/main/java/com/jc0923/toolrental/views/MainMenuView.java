package com.jc0923.toolrental.views;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.domain.ToolType;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.JSONFileReader;

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
		
		
		/*
		 * Load in tooltypes.json data
		 */
		JSONArray toolTypesJSONArray = JSONFileReader.readJsonFile("src/main/resources/ToolTypes.json");
		Map<String, ToolType> toolTypesMap = new HashMap<String, ToolType>();
		Iterator<JSONObject> toolTypeIterator = toolTypesJSONArray.iterator();
		while (toolTypeIterator.hasNext()) {
			JSONObject toolTypeJsonObject = (JSONObject) toolTypeIterator.next();
			String toolTypeName = (String) toolTypeJsonObject.get("toolTypeName");
			Double dailyRentalCharge = (Double) toolTypeJsonObject.get("dailyRentalCharge");
			boolean hasWeekdayCharge = (boolean) toolTypeJsonObject.get("hasWeekdayCharge");
			boolean hasWeekendCharge = (boolean) toolTypeJsonObject.get("hasWeekendCharge");
			boolean hasHolidayCharge = (boolean) toolTypeJsonObject.get("hasHolidayCharge");

			ToolType toolType = new ToolType(toolTypeName, BigDecimal.valueOf(dailyRentalCharge), hasWeekdayCharge,
					hasWeekendCharge, hasHolidayCharge);
			toolTypesMap.put(toolType.getToolTypeName(), toolType);
		}
		toolTypesMap.forEach((key, value) -> System.out.println(key + " " + value.toString()));

		/*
		 * Load in tools.json data
		 */
		JSONArray toolsJSONArray = JSONFileReader.readJsonFile("src/main/resources/Tools.json");
		List<Tool> toolsList = new ArrayList<Tool>();
		Iterator<JSONObject> toolIterator = toolsJSONArray.iterator();
		;
		while (toolIterator.hasNext()) {
			JSONObject toolJsonObject = (JSONObject) toolIterator.next();
			String toolCode = (String) toolJsonObject.get("toolCode");
			String toolTypeString = (String) toolJsonObject.get("toolType");
			String brand = (String) toolJsonObject.get("brand");

			ToolType toolType = toolTypesMap.get(toolTypeString);

			Tool tool = new Tool(toolCode, toolType, brand);
			toolsList.add(tool);
		}

		toolsList.forEach(t -> System.out.println(t.toString()));
		
	}
	
	private void displayCart() {
		System.out.println("Cart - to be implemented");
	}
	
	private void displayCheckout() {
		System.out.println("Checkout - to be implemented");
	}
}
