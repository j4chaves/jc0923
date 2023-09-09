package com.jc0923.toolrental.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
	
	private Tool tool;
	private Checkout checkout;
	private LocalDate dueDate;
	private BigDecimal dailyRentalCharge;
	private int chargeDays;
	private BigDecimal preDiscountCharge;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;
	
	public RentalAgreement() {}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Checkout getCheckout() {
		return checkout;
	}

	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(BigDecimal finalCharge) {
		this.finalCharge = finalCharge;
	}

}
