package com.jc0923.toolrental.util;

import com.jc0923.toolrental.domain.Tool;

public class Cart {
	
	public static Tool toolInCart;

	private Cart() {}
	
	public static void updateCart(Tool tool) {
		toolInCart = tool;
	}
	
//	public static void initializeEmptyCart() {
//		toolInCart = new ArrayList<Tool>();
//	}
//	
//	public static boolean addToolToCart(Tool tool) {
//		if (!toolInCart.contains(tool)) {
//			toolInCart.add(tool);
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public static boolean removeToolFromCart(int index) {
//		if (!toolInCart.isEmpty() && index >= 0) {
//			if (toolInCart.size() == 1) {
//				toolInCart.remove(0);
//			} else {
//				toolInCart.remove(index);
//			}
//			return true;
//		} else {
//			return false;
//		}
//	}
}
