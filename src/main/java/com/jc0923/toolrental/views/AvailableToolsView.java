package com.jc0923.toolrental.views;

import java.util.ArrayList;

import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.interfaces.Displayable;
import com.jc0923.toolrental.util.Inventory;

public class AvailableToolsView implements Displayable {

	@Override
	public void display() {
		System.out.println("Available Tools To Rent");

		ArrayList<Tool> tools = new ArrayList<Tool>(Inventory.toolsList);
		for (int i = 0; i < Inventory.toolsList.size(); i++) {
			System.out.print((i + 1) + ".\t");
			System.out.print("Tool Code: " + tools.get(i).getToolCode() + "\t\t");
			System.out.print("Tool Type: " + tools.get(i).getTooltype().getToolTypeName() + "\t\t");
			System.out.print("Brand: " + tools.get(i).getBrand());
			System.out.println("");
		}

	}

}
