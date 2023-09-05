package com.jc0923.toolrental;

import com.jc0923.toolrental.views.MainMenuView;

/**
 * Hello world!
 *
 */
public class ToolRentalMain 
{
    public static void main( String[] args )
    {
    	MainMenuView mainMenu = new MainMenuView();
    	while(true) {
    		mainMenu.display();
    	}
    }
    
    
}
