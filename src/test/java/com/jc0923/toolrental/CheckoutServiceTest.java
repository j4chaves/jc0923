package com.jc0923.toolrental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jc0923.toolrental.domain.Checkout;
import com.jc0923.toolrental.domain.RentalAgreement;
import com.jc0923.toolrental.domain.Tool;
import com.jc0923.toolrental.services.CheckoutService;
import com.jc0923.toolrental.util.Inventory;

public class CheckoutServiceTest {
	
	Checkout checkout;
	RentalAgreement rentalAgreement;
	CheckoutService checkoutService;
	
	@BeforeEach
	void setup() {
		Inventory.loadInventory("src/test/resources/");
		checkout = new Checkout();
		rentalAgreement = new RentalAgreement();
		checkoutService = new CheckoutService();
	}
	
	@ParameterizedTest
	@DisplayName("Create Rental Agreement Test")
	@CsvFileSource(resources = "/specTestCases.csv", numLinesToSkip = 1)
	public void createRentalAgreementTest(String toolCode, String checkoutDate,
			int rentalDays, int discountPercentage) {
		checkout.setCheckoutDate(LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("M/d/yy")));
		checkout.setRentalDays(rentalDays);
		checkout.setDiscountPercentage(discountPercentage);
		
		Tool tool = null;
		for (Tool t : Inventory.toolsList) {
			if (t.getToolCode().equals(toolCode)) {
				tool = t;
				break;
			}
		}
		
		rentalAgreement = checkoutService.createRentalAgreement(checkout, tool);
		
		assertEquals(rentalAgreement.getCheckout().getCheckoutDate(), checkout.getCheckoutDate());
		assertEquals(rentalAgreement.getTool().getToolCode(), toolCode);
		assertEquals(rentalAgreement.getCheckout().getRentalDays(), rentalDays);
		assertEquals(rentalAgreement.getCheckout().getDiscountPercentage(), discountPercentage);
		
	}
	
	@ParameterizedTest
	@DisplayName("Create Rental Invalid Data Agreement Test")
	@CsvFileSource(resources = "/invalidDataTestCases.csv", numLinesToSkip = 1)
	public void createRentalAgreementInvalidDataTest(String toolCode, String checkoutDate,
			int rentalDays, int discountPercentage) {
		checkout.setCheckoutDate(LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("M/d/yy")));
		checkout.setRentalDays(rentalDays);
		checkout.setDiscountPercentage(discountPercentage);
		
		Tool tool = null;
		for (Tool t : Inventory.toolsList) {
			if (t.getToolCode().equals(toolCode)) {
				tool = t;
				break;
			}
		}
		
		RentalAgreement rentalAgreement = checkoutService.createRentalAgreement(checkout, tool);
		
		assert(rentalAgreement == null);
		
	}

}
