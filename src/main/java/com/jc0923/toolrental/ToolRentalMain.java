package com.jc0923.toolrental;

import com.jc0923.toolrental.util.Inventory;
import com.jc0923.toolrental.util.UserInputHandler;
import com.jc0923.toolrental.views.MainMenuView;

/**
 * Hello world!
 *
 */
public class ToolRentalMain 
{
    public static void main( String[] args )
    {
    	Inventory.loadInventory();
    	UserInputHandler.clearConsole();
    	
    	MainMenuView mainMenu = new MainMenuView();
    	while(true) {
    		mainMenu.display();
    	}
    }
    
    
}
