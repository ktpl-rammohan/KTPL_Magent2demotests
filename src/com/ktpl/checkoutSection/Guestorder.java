package com.ktpl.checkoutSection;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;

public class Guestorder	extends BaseinitCheckoutSection {
	//Add product to cart
	//Naviagate to cehck out
	//fill shipping address
	//select payment option 
	//place order
	//verify order sucess page


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
			extent.startTest("Guestuser placeorder test");
			UtilityMethods.Addsimpleproduct(1);
			
			Thread.sleep(1000);
			checkPropertiesData("Floatingcarticon_xpath").click();
			checkPropertiesData("FloatingcartCheckoutbutton_xpath").click();
		
			Thread.sleep(2000);
		
			//Shipping Step
			checkPropertiesData("EECheckoutEmail_xpath").sendKeys("test@test39.com");
			driver.findElement(By.name("firstname")).sendKeys("unit1");
			driver.findElement(By.name("lastname")).sendKeys("QA");
			driver.findElement(By.name("street[0]")).sendKeys("Streettest");
			driver.findElement(By.name("city")).sendKeys("Test");
			WebElement statedropdown = driver.findElement(By.name("region_id"));
			Select StateDropdown = new Select(statedropdown);
			StateDropdown.selectByVisibleText("Texas");
			driver.findElement(By.name("postcode")).sendKeys("10010");
			WebElement Countrydropdown = driver.findElement(By.name("country_id"));
			Select CountryDropdown = new Select(Countrydropdown);
			CountryDropdown.selectByVisibleText("United States");
			driver.findElement(By.name("telephone")).sendKeys("123123123");
			checkPropertiesData("EEShippingmethodRadiobutton_xpath").click();
			checkPropertiesData("EEShippingstepNextbutton_xpath").click();

			// Payment step
			Thread.sleep(4000);
			checkPropertiesData("EEPaymentstepBillingaddress_xpath").click();	
			checkPropertiesData("paymentmethodradiobutton_xpath").click();
			checkPropertiesData("EEPaymentstepplaceorderbutton_xapth").click();
			
			// Verify order confirmation page
			if (driver.getTitle().equalsIgnoreCase("order confirmation")) {
				String orderID = checkPropertiesData("EEOrderconfimationID_xpath").getText();
				System.out.println("order id is:" + orderID);
			} else {
				System.out.println("Guest user unable to place an order");
				UtilityMethods.screenshot();
			}
		}
	}
	
