package com.jc0923.toolrental.domain;

import java.time.LocalDate;

public class Checkout {
	
	private String toolCode;
	private int rentalDays;
	private int discountPercentageWholeNumber;
	private LocalDate checkoutDate;
	
	public Checkout() {}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public int getDiscountPercentageWholeNumber() {
		return discountPercentageWholeNumber;
	}

	public void setDiscountPercentageWholeNumber(int discountPercentageWholeNumber) {
		this.discountPercentageWholeNumber = discountPercentageWholeNumber;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

}
