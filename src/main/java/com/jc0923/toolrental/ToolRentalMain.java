package com.jc0923.toolrental;

import com.jc0923.toolrental.util.Inventory;
import com.jc0923.toolrental.util.UserInputHandler;
import com.jc0923.toolrental.views.MainMenuView;

public class ToolRentalMain 
{
    public static void main( String[] args )
    {
    	Inventory.loadInventory("src/main/resources/");
//    	Cart.initializeEmptyCart();
    	UserInputHandler.clearConsole();
    	
    	MainMenuView mainMenu = new MainMenuView();
    	while(true) {
    		mainMenu.display();
    	}
    }
    
    
}
