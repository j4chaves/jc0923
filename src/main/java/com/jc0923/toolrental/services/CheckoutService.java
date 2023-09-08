package com.jc0923.toolrental.services;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.util.Cart;

public class CheckoutService {

	public String createRentalAgreement(Checkout checkout) {
		if (validateCheckout(checkout)) {
			LocalDate dueDate = calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDays());
			checkout.setDueDate(dueDate);
			
			BigDecimal dailyRentalCharge = calculateDailyRentalCharge(Cart.toolsInCart);
			checkout.setDailyRentalCharge(dailyRentalCharge);
			
//			int chargeableDays = calculateChargeableDays(checkout.getCheckoutDate(), checkout.getDueDate());
//			checkout.setChargeableDays(chargeableDays);
			
			//TODO retest preDiscountCharge after chargeableDays is working with holidays
			BigDecimal preDiscountCharge = calculatePreDiscountCharge(checkout.getChargeableDays(), checkout.getDailyRentalCharge());
			checkout.setPreDiscountCharge(preDiscountCharge);
			
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
			String dailyRentalChargeString = currencyFormat.format(checkout.getDailyRentalCharge().doubleValue());
			String preDiscountChargeString = currencyFormat.format(checkout.getPreDiscountCharge().doubleValue());
			
			StringBuilder rentalAgreement = new StringBuilder();
			rentalAgreement.append("Rental Agreement\n");
			rentalAgreement.append("================\n");
			rentalAgreement.append("\n");
			rentalAgreement.append("===Tools List===\n");
			for (Tool tool : Cart.toolsInCart) {
				rentalAgreement.append(tool.getToolCode() + "\t" 
						+ tool.getTooltype().getToolTypeName() + "\t"
						+ tool.getBrand() + "\n");
			}
			rentalAgreement.append("\n");
			rentalAgreement.append("=Rental Details=\n");
			rentalAgreement.append("Number of Rental Days: " + checkout.getRentalDays() + "\n");
			rentalAgreement.append("Checkout Date: " + checkout.getCheckoutDate() + "\n");
			rentalAgreement.append("Due Date: " + checkout.getDueDate() + "\n");
			rentalAgreement.append("Daily Rental Charge: " + dailyRentalChargeString + "\n");
//			rentalAgreement.append("Number of Chargeable Days: " + checkout.get() + "\n");
			rentalAgreement.append("Pre-Discount Charge: " + preDiscountChargeString + "\n");
			rentalAgreement.append("Discount Percentage: " + checkout.getDiscountPercentage() + "\n");
//			rentalAgreement.append("Discount Amount: " + checkout.get() + "\n");
//			rentalAgreement.append("Final Charge: " + checkout.get() + "\n");
			
			
			return rentalAgreement.toString();
		} else {
			return "You must enter values for Rental Days, Discount Percentage, and Checkout Date before creating a Rental Agreement";
		}
	}
	
	/**
	 * For a Checkout object to be valid for the creation of a rental agreement,
	 * rentalDays, discountPercentage, and checkoutDate must have values.
	 * @param checkout
	 * @return
	 */
	private boolean validateCheckout(Checkout checkout) {
		boolean isValid = false;
		
		if (checkout.getRentalDays() >= 1 &&
				checkout.getCheckoutDate() != null &&
				(checkout.getDiscountPercentage() >= 0 && 
				checkout.getDiscountPercentage() <= 100)) {
			isValid = true;
		}
		
		
		return isValid;
	}
	
	private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
		LocalDate dueDate = checkoutDate.plusDays(rentalDays);
		return dueDate;
	}
	
	private BigDecimal calculateDailyRentalCharge(List<Tool> tools) {
		BigDecimal dailyRentalCharge = BigDecimal.ZERO;
		for (Tool t : tools) {
			dailyRentalCharge = dailyRentalCharge.add(t.getTooltype().getDailyRentalCharge());
		}
		return dailyRentalCharge;
	}
	
	private BigDecimal calculatePreDiscountCharge(int chargeableDays, BigDecimal dailyRentalCharge) {
		BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeableDays));
		return preDiscountCharge;
	}
}
