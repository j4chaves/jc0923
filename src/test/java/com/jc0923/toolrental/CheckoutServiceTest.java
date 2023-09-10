package com.jc0923.toolrental;

import static org.junit.Assert.assertNull;
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
	
	@BeforeEach
	void setup() {
		Inventory.loadInventory("src/test/resources/");
	}
	
	@ParameterizedTest
	@DisplayName("CheckoutService Valid Data Test")
	@CsvFileSource(resources = "/specTestCases.csv", numLinesToSkip = 1)
	public void checkoutServiceValidDataTest(String toolCode, String checkoutDate,
			int rentalDays, int discountPercentage) {
		Checkout checkout = new Checkout();
		RentalAgreement rentalAgreement = new RentalAgreement();
		CheckoutService checkoutService = new CheckoutService();
		
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
		
		assertNotNull(checkoutService.generateRentalAgreementString(rentalAgreement));
		
	}
	
	@ParameterizedTest
	@DisplayName("Create Rental Invalid Data Agreement Test")
	@CsvFileSource(resources = "/invalidDataTestCases.csv", numLinesToSkip = 1)
	public void createRentalAgreementInvalidDataTest(String toolCode, String checkoutDate,
			int rentalDays, int discountPercentage) {
		Checkout checkout = new Checkout();
		RentalAgreement rentalAgreement = new RentalAgreement();
		CheckoutService checkoutService = new CheckoutService();
		
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
		
		assert(rentalAgreement == null);
		
	}
	
	@Test
	public void testNulls() {
		CheckoutService checkoutService = new CheckoutService();
		Checkout checkout = new Checkout();
		checkout.setCheckoutDate(LocalDate.now());
		checkout.setDiscountPercentage(10);
		checkout.setRentalDays(10);
		checkout.setToolCode("LADR");
		assertNull(checkoutService.createRentalAgreement(checkout, null));
		assertNull(checkoutService.createRentalAgreement(null, null));
		assertNotNull(checkoutService.generateRentalAgreementString(null));
	}

}
