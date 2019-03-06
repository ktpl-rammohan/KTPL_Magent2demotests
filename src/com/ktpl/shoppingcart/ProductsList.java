package com.ktpl.shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

//Add  products to cart and verify the preferred products are added to cart or not
// page title and bread crumbs
// product name, price and quantity
// update product quantity and verify sub total
// product redirects, edit and delete

public class ProductsList extends BaseinitShoppingcart {

	@BeforeTest
	public void checkTestCaseExecution() {

		logs.info("System checking execution status  of shopping cart product list Testcase.......");
		if (!UtilityMethods.checkTestCaseForExecution(shoppingcart, this.getClass().getSimpleName(), "TestScript")) {
			// TestNG will // skip execution of method
			throw new SkipException("Execution mode of the test case is set to NO"); 																						
		}
	}

	@Test
	public void productlisttest() throws Exception {

		UtilityMethods.getInstance();
		extent.startTest("Shopping cart test");
		// Adding two simple products to cart
		UtilityMethods.Addsimpleproduct(2);
		// Adding one configurable product to cart
		UtilityMethods.Addconfigproduct();
		/*UtilityMethods.mousehover("simplelistingproduct2");	
		 * act = new Actions(driver);
		 * act.moveToElement(checkPropertiesData("simplelistingproduct2")).build
		 * ().perform();
		 
		checkPropertiesData("simplelistingaddtocart2").click();*/
		Thread.sleep(2000);

		checkPropertiesData("EEFloatingcarticon").click();
		checkPropertiesData("EEFloatingViewcartlink").click();

		Thread.sleep(2000);

		// verifying products count in shopping cart page
		WebElement productlistdiv = checkPropertiesData("shoppingcartproductlistdiv");
		int productscount = productlistdiv.findElements(By.tagName("tbody")).size();

		System.out.println(productscount);
		String Flcount = checkPropertiesData("Floatingcarticount").getText();
		System.out.println("floating count is" + Flcount);
		int floatcount = Integer.parseInt(Flcount);
		if (floatcount == productscount) {
			System.out.println("Number of products displaying in shopping cart are as expected");
			extent.log(LogStatus.PASS, "Verifying shopping cart products count",
					"All the products added to cart are displaying in shopping cart page");
		} else {
			System.out.println("Number of products displaying in shopping cart are not as expected");
			extent.log(LogStatus.FAIL, "Verifying shopping cart products count",
					"All the products added to cart are not displaying in shopping cart page.");
		}
		// Verifying page title and bread crumbs
		if (driver.getTitle()
				.contains(driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/h1/span")).getText())) {
			extent.log(LogStatus.PASS, "Verifying shopping cart page title", "Shopping cart page title is as expected");
			System.out.println("Shopping cart title is as expected");
		} else {
			System.out.println("Shopping cart title is not as expected");
			extent.log(LogStatus.FAIL, "Verifying shopping cart page title", "Shopping cart page title is as expected");
		}
		Thread.sleep(4000);
		// Update quantity
		Actions action = new Actions(driver);
		action.moveToElement(checkPropertiesData("quantitybox")).doubleClick().sendKeys("2").perform();
		// checkPropertiesData("quantitybox").click();
		// checkPropertiesData("quantitybox").sendKeys(Keys.DELETE);
		// checkPropertiesData("quantitybox").sendKeys("2");
		checkPropertiesData("updateshoppingcart").click();
		Thread.sleep(2000);
		checkPropertiesData("quantitybox").click();
		// action.moveToElement(checkPropertiesData("quantitybox")).doubleClick().perform();

		// String imagewidth =
		// driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[1]/a/span/span/img")).getCssValue("width");

		// System.out.println("Imagewidth is "+imagewidth);

		// String updatedqty =
		// driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[3]/div/div")).getAttribute("value");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String updatedqty = js
				.executeScript(
						"return angular.element(document.getElementById('cart-66043-qty')).scope().user.st_first_name;")
				.toString();

		// String updatedqty = (String)(js.executeScript("return
		// angular.element(arguments[0]).scope().user.st_first_n‌​ame",
		// driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody[1]/tr[1]/td[3]/div/div"))));

		System.out.println("updated quantity is " + updatedqty);
		/*
		 * int updatedproductqty = Integer.parseInt(updatedqty);
		 * if(updatedproductqty==2){ extent.log(LogStatus.PASS,
		 * "Updated product quatity verification",
		 * "Product quantity updated successfully"); System.out.println(
		 * "product quantity is updated succesfully"); } else{
		 * extent.log(LogStatus.FAIL, "Updated product quatity verification",
		 * "Product quantity is not updated"); System.out.println(
		 * "product quantity is not as expected"); }
		 */
		/*
		 * //Verify updated product sub total
		 * checkPropertiesData("Floatingcartproduct").click();
		 * checkPropertiesData("Floatingcartproduct").click();
		 * 
		 * 
		 * //Click on Edit link and update the configurable product
		 * checkPropertiesData("Floatingcartproduct").click();
		 * checkPropertiesData("Floatingcartproduct").click();
		 * checkPropertiesData("Floatingcartproduct").click();
		 * checkPropertiesData("Floatingcartproduct").click();
		 * 
		 * //Delete the product
		 * checkPropertiesData("Productdeleteicon").click();
		 */

		//
	}
}
