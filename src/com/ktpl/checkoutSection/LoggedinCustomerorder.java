package com.ktpl.checkoutSection;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ktpl.utitlityMethods.UtilityMethods;

public class LoggedinCustomerorder extends BaseinitCheckoutSection{

	//Login
	
	//Add product
	//NAvigate to check out
	//place order
	//verify order sucess page.
	
	@BeforeTest
	public void checkTestCaseExecution() {

		if (!UtilityMethods.checkTestCaseForExecution(checkoutSection, this.getClass().getSimpleName(),
				"TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO"); // TestNG																						// method
		}
	}
	@Test
	public void GuestEndtoEndScenario() throws InterruptedException, IOException {

		UtilityMethods.getInstance();
		driver.get(storage.getProperty("userURL"));
		extent.startTest("Loggedinuser placeorder test");
		UtilityMethods.login();
		UtilityMethods.Addsimpleproduct(1);
		
		Thread.sleep(1000);
		checkPropertiesData("Floatingcarticon_xpath").click();
		checkPropertiesData("FloatingcartCheckoutbutton_xpath").click();
	
		Thread.sleep(2000);
	
		//Shipping Step
		checkPropertiesData("Checkoutshipherebutton_xpath").click();
		checkPropertiesData("EEShippingmethodRadiobutton_xpath").click();
		checkPropertiesData("EEShippingstepNextbutton_xpath").click();

		// Payment step
		Thread.sleep(2000);
		checkPropertiesData("EEPaymentstepBillingaddress_xpath").click();	
		//checkPropertiesData("paymentmethodradiobutton-").click();
		checkPropertiesData("EEPaymentstepplaceorderbutton_xpath").click();
		
		// Verify order confirmation page
		if (driver.getTitle().equalsIgnoreCase("order confirmation")) {
			String orderID = checkPropertiesData("EEOrderconfimationID").getText();
			System.out.println("order id is:" + orderID);
		} else {
			System.out.println("Guest user unable to place an order");
			UtilityMethods.screenshot();
		}
	}
}
