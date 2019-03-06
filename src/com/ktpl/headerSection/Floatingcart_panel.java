package com.ktpl.headerSection;

import org.openqa.selenium.By;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Floatingcart_panel extends BaseinitHeadersection {

	@BeforeTest
	public void FCcheckTestCaseExecution() {
		if (!UtilityMethods.checkTestCaseForExecution(headersection, this.getClass().getSimpleName(), "TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO"); // TestNG will skip execution of the method
		}
	}
	@Test
	public static void floatingcart() throws Exception {
		UtilityMethods.getInstance();
		//verify empty cart panel text
		driver.get(storage.getProperty("userURL"));
		Thread.sleep(2000);
		extent.startTest("Floating cart  links test");
		extent.log(LogStatus.INFO, "Verifying Floating cart panel");
		UtilityMethods.waitForElement(checkPropertiesData("Floatingcarticon_xpath"));
		Thread.sleep(2000);
		checkPropertiesData("Floatingcarticon_xpath").click();
		System.out.println(checkPropertiesData("EmptyFloatingcartpanel_xpath").getText().toLowerCase());
		if (checkPropertiesData("EmptyFloatingcartpanel_xpath").getText().toLowerCase()
				.contains("you have no items in your shopping cart.")) {
			System.out.println("Empty floating cart pannel text displaying is proper");
			extent.log(LogStatus.PASS, "Empty Floating cart panel Test",
					"Empty Floating cart panel displaying alert message as expected");
		} else {
			System.out.println("Empty floating cart pannel text displaying is not proper");
			extent.log(LogStatus.FAIL, "Empty Floating cart panel Test",
					"Empty Floating cart panel displaying alert message is not as expected");
			// Screen shot
			UtilityMethods.screenshot();
		}
		UtilityMethods.Addsimpleproduct(1);
		Thread.sleep(2000);
		// verify number count of products
		String Flcount = driver.findElement(By.xpath("//span[@class='counter-number']")).getText();
		System.out.println("Products adeded to cart successfully, floating cart count  :" + Flcount);
		int floatcount = Integer.parseInt(Flcount);
		if (floatcount == 1) {
			extent.log(LogStatus.PASS, "Verifying Floating cart count", "Floating cart count displaying is proper");
		} else {
			extent.log(LogStatus.FAIL, "Verifying Floating cart count", "Floating cart count displaying is not proper");
			UtilityMethods.screenshot();
			
		}
		Thread.sleep(2000);
		// click on product link in floating cart
		checkPropertiesData("Floatingcarticon_xpath").click();
		String productname = checkPropertiesData("Floatingcartproduct_xpath").getText();
		checkPropertiesData("Floatingcartproduct_xpath").click();
		Thread.sleep(2000);
		if (driver.getTitle().contains(checkPropertiesData("Floatingcartproduct_xpath").getText())) {
			extent.log(LogStatus.PASS, "Verifying Floating cart product link",
					"System redirected to preferred product page");
		} else {
			extent.log(LogStatus.FAIL, "Verifying Floating cart product link",
					"System is not redirected to preferred product page");
			UtilityMethods.screenshot();
		}
		Thread.sleep(2000);
		// click on Edit link of the product
		checkPropertiesData("Floatingcarticon_xpath").click();
		checkPropertiesData("FloatingcartEditicon_xpath").click();
		if (checkPropertiesData("Updatecartbutton_xpath").isDisplayed()) {
			extent.log(LogStatus.PASS, "Verifying Floating cart product Edit link",
					"System redirected to update product page");
		} else {
			extent.log(LogStatus.FAIL, "Verifying Floating cart product Edit link",
					"System is not redirected to update product page");
			UtilityMethods.screenshot();
		}
		Thread.sleep(1000);
		// Click on view shopping cart link
		checkPropertiesData("Floatingcarticon_xpath").click();
		checkPropertiesData("FloatingcartViewshoppingcartbtn_xpath").click();
		if (driver.getTitle().contains("Shopping cart")) {
			extent.log(LogStatus.PASS, "Verifying Floating cart (View shopping cart) link",
					"Floating cart (View shopping cart) link is working as expected, System redirected to Shopping cart page");
		} else {
			extent.log(LogStatus.FAIL, "Verifying Floating cart (View shopping cart) link",
					"Floating cart (View shopping cart) link is not working");
			UtilityMethods.screenshot();
		}
		Thread.sleep(2000);

		// Click on checkout button
		checkPropertiesData("Floatingcarticon_xpath").click();
		checkPropertiesData("FloatingcartCheckoutbutton_xpath").click();
		if (driver.getTitle().contains("Checkout")) {
			extent.log(LogStatus.PASS, "Verifying Floating cart (Checkout) button",
					"Floating cart (Checkout) button is working as expected, System redirected to Checkout page");
		} else {
			extent.log(LogStatus.FAIL, "Verifying Floating cart (Checkout) button",
					"Floating cart (Checkout) button is not working");
			UtilityMethods.screenshot();
		}
	
	  driver.navigate().back();
		// Remove Item
				checkPropertiesData("Floatingcarticon_xpath").click();
				checkPropertiesData("FloatingcartRemoveicon_xpath").click();
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("//span[contains(text(),'OK')]")).click();		
				Thread.sleep(1000);
				if (checkPropertiesData("EmptyFloatingcartpanel_xpath").getText().toLowerCase()
						.contains("you have no items in your shopping cart.")) {	   
					   System.out.println("Sucessfully Removed Product from floating cart panel");		   
					   extent.log(LogStatus.PASS, "Verifying Removed product in Floating cart panel ","Product removed from cart");		   
				 }else {		   
					   System.out.println("Added Product is not displaying in floating cart panel");
						extent.log(LogStatus.FAIL, "Verifying product in Floating cart panel Test","Added Product is not displaying in floating cart panel");		   
				   // Screen shot
					UtilityMethods.screenshot();	      
				 }
	
	
	
	
	
	}
}