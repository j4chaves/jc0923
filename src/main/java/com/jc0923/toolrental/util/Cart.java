package com.jc0923.toolrental.util;

import java.util.ArrayList;
import java.util.List;

import com.jc0923.toolrental.domain.Tool;

public class Cart {
	
	public static List<Tool> toolsInCart;

	private Cart() {}
	
	public static void initializeEmptyCart() {
		toolsInCart = new ArrayList<Tool>();
	}
	
	public static boolean addToolToCart(Tool tool) {
		if (!toolsInCart.contains(tool)) {
			toolsInCart.add(tool);
			return true;
		} else {
			return false;
		}
	}

	public static boolean removeToolFromCart(int index) {
		if (!toolsInCart.isEmpty() && index >= 0) {
			if (toolsInCart.size() == 1) {
				toolsInCart.remove(0);
			} else {
				toolsInCart.remove(index);
			}
			return true;
		} else {
			return false;
		}
	}
}
