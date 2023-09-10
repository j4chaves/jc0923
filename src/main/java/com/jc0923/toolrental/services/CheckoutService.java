package com.jc0923.toolrental.services;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.domain.RentalAgreement;
import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.domain.ToolType;

public class CheckoutService {

	public RentalAgreement createRentalAgreement(Checkout checkout, Tool toolToBeRented) {
		RentalAgreement rentalAgreement = null;
		
		if (validateCheckout(checkout) && toolToBeRented != null) {
			rentalAgreement = new RentalAgreement();
			rentalAgreement.setTool(toolToBeRented);
			rentalAgreement.setCheckout(checkout);
			rentalAgreement.setDailyRentalCharge(toolToBeRented.getTooltype().getDailyRentalCharge());
			
			LocalDate dueDate = calculateDueDate(checkout.getCheckoutDate(), checkout.getRentalDays());
			rentalAgreement.setDueDate(dueDate);
			
			int chargeDays = calculateChargeableDays(checkout.getCheckoutDate(), dueDate, toolToBeRented);
			rentalAgreement.setChargeDays(chargeDays);
			
			BigDecimal preDiscountCharge = calculatePreDiscountCharge(chargeDays, rentalAgreement.getDailyRentalCharge());
			rentalAgreement.setPreDiscountCharge(preDiscountCharge);
			
			BigDecimal discountAmount = calculateDiscountAmount(checkout.getDiscountPercentage(), preDiscountCharge);
			rentalAgreement.setDiscountAmount(discountAmount);
			
			BigDecimal finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);
			rentalAgreement.setFinalCharge(finalCharge);
		}
		
		return rentalAgreement;
	}
	
	public String generateRentalAgreementString(RentalAgreement rentalAgreement) {
		if (rentalAgreement != null) {
			
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
			String dailyRentalChargeString = currencyFormat.format(rentalAgreement.getDailyRentalCharge().doubleValue());
			String preDiscountChargeString = currencyFormat.format(rentalAgreement.getPreDiscountCharge().doubleValue());
			String discountAmountString = currencyFormat.format(rentalAgreement.getDiscountAmount().doubleValue());
			String finalChargeString = currencyFormat.format(rentalAgreement.getFinalCharge().doubleValue());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
			String checkoutDateDisplay = formatter.format(rentalAgreement.getCheckout().getCheckoutDate());
			String dueDateDispay = formatter.format(rentalAgreement.getDueDate());
			
			StringBuilder agreement = new StringBuilder();
			agreement.append("Rental Agreement\n");
			agreement.append("================\n");
			agreement.append("Tool code: " + rentalAgreement.getTool().getToolCode() + "\n");
			agreement.append("Tool type: " + rentalAgreement.getTool().getTooltype().getToolTypeName() + "\n");
			agreement.append("Brand: " + rentalAgreement.getTool().getBrand() + "\n");
			agreement.append("Rental Days: " + rentalAgreement.getCheckout().getRentalDays() + "\n");
			agreement.append("Checkout Date: " + checkoutDateDisplay + "\n");
			agreement.append("Due Date: " + dueDateDispay + "\n");
			agreement.append("Daily Rental Charge: " + dailyRentalChargeString + "\n");
			agreement.append("Charge Days: " + rentalAgreement.getChargeDays() + "\n");
			agreement.append("Pre-Discount Charge: " + preDiscountChargeString + "\n");
			agreement.append("Discount Percentage: " + rentalAgreement.getCheckout().getDiscountPercentage() + "%\n");
			agreement.append("Discount Amount: " + discountAmountString + "\n");
			agreement.append("Final Charge: " + finalChargeString + "\n");
			
			return agreement.toString();
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
		
		if (checkout != null && checkout.getRentalDays() >= 1 && checkout.getCheckoutDate() != null &&
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
		
		if (HolidayService.isIndependenceDayInDateRange(checkoutDate, dueDate) && !tool.getTooltype().isHasHolidayCharge()) {
			numberOfChargeableDays -= 1;
		}
		
		if (HolidayService.isLaborDayInDateRange(checkoutDate, dueDate) && !tool.getTooltype().isHasHolidayCharge()) {
			numberOfChargeableDays -= 1;
		}
		
		return numberOfChargeableDays;
	}
	
	private BigDecimal calculatePreDiscountCharge(int chargeableDays, BigDecimal dailyRentalCharge) {
		BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeableDays));
		return preDiscountCharge;
	}
	
	private BigDecimal calculateDiscountAmount(int discountPercentage, BigDecimal preDiscountCharge) {
		double discount = discountPercentage;
		discount = discount / 100;
		BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discount));
		return discountAmount;
	}
	
	private BigDecimal calculateFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
		BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
		return finalCharge;
	}
}
