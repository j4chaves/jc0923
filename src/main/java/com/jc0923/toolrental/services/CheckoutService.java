package com.jc0923.toolrental.services;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.domain.ToolType;
import com.jc0923.toolrental.util.Cart;

public class CheckoutService {

	public String createRentalAgreement(Checkout checkout) {
		if (validateCheckout(checkout)) {
			Tool toolToBeRented = Cart.toolInCart;
			
			LocalDate dueDate = calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDays());
			checkout.setDueDate(dueDate);
			
			checkout.setDailyRentalCharge(toolToBeRented.getTooltype().getDailyRentalCharge());
			
			int chargeableDays = calculateChargeableDays(checkout.getCheckoutDate(), checkout.getDueDate(), toolToBeRented);
			BigDecimal individualToolCharge = calculatePreDiscountCharge(chargeableDays, toolToBeRented.getTooltype().getDailyRentalCharge());
			
			checkout.setChargeableDays(chargeableDays);
			
			//TODO retest preDiscountCharge after chargeableDays is working with holidays
			BigDecimal preDiscountCharge = calculatePreDiscountCharge(checkout.getChargeableDays(), checkout.getDailyRentalCharge());
			checkout.setPreDiscountCharge(preDiscountCharge);
			
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
			String dailyRentalChargeString = currencyFormat.format(checkout.getDailyRentalCharge().doubleValue());
			String preDiscountChargeString = currencyFormat.format(checkout.getPreDiscountCharge().doubleValue());
			
			StringBuilder rentalAgreement = new StringBuilder();
			rentalAgreement.append("Rental Agreement\n");
			rentalAgreement.append("================\n");
			rentalAgreement.append("Tool code: " + toolToBeRented.getToolCode() + "\n");
			rentalAgreement.append("Tool type: " + toolToBeRented.getTooltype().getToolTypeName() + "\n");
			rentalAgreement.append("Brand: " + toolToBeRented.getBrand() + "\n");
			rentalAgreement.append("Rental Days: " + checkout.getRentalDays() + "\n");
			rentalAgreement.append("Checkout Date: " + checkout.getCheckoutDate() + "\n");
			rentalAgreement.append("Due Date: " + checkout.getDueDate() + "\n");
			rentalAgreement.append("Daily Rental Charge: " + dailyRentalChargeString + "\n");
			rentalAgreement.append("Charge Days: " + checkout.getChargeableDays() + "\n");
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
		
		if (checkout.getRentalDays() >= 1 && checkout.getCheckoutDate() != null &&
				(checkout.getDiscountPercentage() >= 0 && checkout.getDiscountPercentage() <= 100)) {
			isValid = true;
		}
		return isValid;
	}
	
	private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
		LocalDate dueDate = checkoutDate.plusDays(rentalDays);
		return dueDate;
	}
	
	private int calculateChargeableDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
		//TODO implement Holidays and holiday logic here to calculate
		int numberOfChargeableDays = 0;
		ToolType toolType = tool.getTooltype();
		
		
		ArrayList<DayOfWeek> chargeableDays = new ArrayList<>();

		if (toolType.isHasWeekdayCharge()) {
			chargeableDays.add(DayOfWeek.MONDAY);
			chargeableDays.add(DayOfWeek.TUESDAY);
			chargeableDays.add(DayOfWeek.WEDNESDAY);
			chargeableDays.add(DayOfWeek.THURSDAY);
			chargeableDays.add(DayOfWeek.FRIDAY);

		}

		if (toolType.isHasWeekendCharge()) {
			chargeableDays.add(DayOfWeek.SATURDAY);
			chargeableDays.add(DayOfWeek.SUNDAY);
		}
		
		for (LocalDate d = checkoutDate; d.isBefore(dueDate); d = d.plusDays(1)) {
			if (chargeableDays.contains(d.getDayOfWeek())) {
				numberOfChargeableDays += 1;
			}
		}
		
//		checkoutDate.datesUntil(dueDate).forEach(d -> {
//			if (days.contains(d.getDayOfWeek())) {
//				chargeableDays += 1;
//			}
//		});
		
		
		//TODO revisit holiday logic
		// If there are both weekday and weekend charges for the tool, holiday logic can be ignored as they will be charged regardless
//		if (chargeableDays.size() != 7) {
//			if (toolType.isHasHolidayCharge()) {
//				if (HolidayService.isIndependenceDayInDateRange(checkoutDate, dueDate)) {
//					chargeableDays += 1;
//				}
//
//				if (HolidayService.isLaborDayInDateRange(checkoutDate, dueDate) && toolType.isHasWeekdayCharge()) {
//					chargeableDays += 1;
//				}
//			}
//		}
		
		return numberOfChargeableDays;
	}
	
	private BigDecimal calculatePreDiscountCharge(int chargeableDays, BigDecimal dailyRentalCharge) {
		BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeableDays));
		return preDiscountCharge;
	}
}
