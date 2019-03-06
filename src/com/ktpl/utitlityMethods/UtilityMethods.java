package com.ktpl.utitlityMethods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ktpl.framework_Parent.ParentBaseinit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.unicodetechnologies.xlsConnectivity.XLSDatatable_Connectivity;

public class UtilityMethods extends ParentBaseinit {

	// TO Check Test suite Execution mode
	public static boolean checkTestSuiteForExecution(XLSDatatable_Connectivity suite, String testSuiteName,
			String sheetName) {
		int rows = suite.totalRow(sheetName);
		for (int row = 2; row <= rows; row++) {
			if (suite.getData(sheetName, "TestSuiteID", row).equalsIgnoreCase(testSuiteName)) {
				if (suite.getData(sheetName, "Execution", row).equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	// To Check Test case execution mode
	public static boolean checkTestCaseForExecution(XLSDatatable_Connectivity testCase, String testCaseName,
			String sheetName) {
		int rows = testCase.totalRow(sheetName);
		for (int row = 2; row <= rows; row++) {
			if (testCase.getData(sheetName, "TestScriptID", row).equalsIgnoreCase(testCaseName)) {
				if (testCase.getData(sheetName, "Execution", row).equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	public static Object[][] getTestDataFromXLS(XLSDatatable_Connectivity data, String sheetName) {
		int cols = data.totalColumn(sheetName);
		int rows = data.totalRow(sheetName);
		Object testData[][] = new Object[rows - 1][cols];
		for (int row = 2; row <= rows; row++) {
			for (int col = 0; col < cols; col++) {
				testData[row - 2][col] = data.getData(sheetName, col, row);
			}
		}
		return testData;
	}
	// Login method
	public static void login() throws InterruptedException, IOException {
		try {
			waitForElement(checkPropertiesData("Loginlink_xpath"));
			checkPropertiesData("Loginlink_xpath").click();
			//waitForElement(driver.findElement(By.linkText("Sign In")));
			//driver.findElement(By.linkText("Sign In")).click();
			waitForElement(driver.findElement(By.id("email")));
			driver.findElement(By.id("email")).sendKeys("rammohan@krishtechnolabs.com");
			driver.findElement(By.id("pass")).sendKeys("magento@123");
			checkPropertiesData("Signinbutton_xpath").click();
			Thread.sleep(3000);
            waitForElement(checkPropertiesData("MyAccountdropdownArrow_xpath"));
			checkPropertiesData("MyAccountdropdownArrow_xpath").click();
			checkPropertiesData("MyAccountlink_xpath").click();

			if(driver.getTitle().equalsIgnoreCase("my account")){
				System.out.println("Logged in sucessfully");
				UtilityMethods.screenshot();
			}else{
				System.out.println("Login Failed");
			}
		} catch (Exception e) {
			System.out.println(e);
			extent.log(LogStatus.FAIL, "Login step", "user unable to login. (screen shot taken)");
			screenshot();
		}
	}
	// Logout method
		public static void logout() throws InterruptedException, IOException {
			try {
				Thread.sleep(1000);
				waitForElement(checkPropertiesData("MyAccountdropdownArrow_xpath"));
				checkPropertiesData("MyAccountdropdownArrow_xpath").click();
				waitForElement(driver.findElement(By.linkText("Sign Out")));
				driver.findElement(By.linkText("Sign Out")).click();
				//verify
				if(checkPropertiesData("Loginlink_xpath").isDisplayed()){
					System.out.println("logout passed");
					UtilityMethods.screenshot();
				}else{
					System.out.println("logout failed");
				}
			} catch (Exception e) {
				extent.log(LogStatus.FAIL, "Logout step", "user unable to logout. (screen shot taken)");
				screenshot();
			}
		}	
	// To verify Breadcrumbs
	public static void Verifybreadcrumbs(String Verificationtext) throws InterruptedException, IOException {
		try {
			waitForElement(driver.findElement(By.className("breadcrumbs")));
			String Breadcrumbstext = driver.findElement(By.className("breadcrumbs")).getText();
			
			if (Breadcrumbstext.toLowerCase().contains("home")) {
			   if(Breadcrumbstext.toLowerCase().contains(Verificationtext.toLowerCase())){
					System.out.println("Bread crumbs displaying is as same as expected");
					logs.info("Bread crumbs displaying are as same as expected");
					extent.log(LogStatus.PASS, Verificationtext+" "+"page BreadCrumbs verification",
							"Bread crumbs displaying are as same as expected");
			   }else{
					System.out.println(Breadcrumbstext);
					System.out.println("Test Failed: Product Breadcrumbs/Pagetitle displaying is not as expected");
					logs.info("Test Failed: Product Breadcrumbs/Pagetitle displaying is not as expected");
					extent.log(LogStatus.FAIL,  Verificationtext+" "+"page BreadCrumbs verification",
							"Bread crumbs displaying are not as same as expected");
					screenshot();
				}				
			} else {
				System.out.println(Breadcrumbstext);
				System.out.println("Test Failed: Product Breadcrumbs/Pagetitle displaying is not as expected");
				logs.info("Test Failed: Product Breadcrumbs/Pagetitle displaying is not as expected");
				extent.log(LogStatus.FAIL,  Verificationtext+" "+"page BreadCrumbs verification",
						"Bread crumbs displaying are not as same as expected");
				screenshot();
			}
		} catch (Exception e) {
			System.out.println("There are no Bread crumbs");
			extent.log(LogStatus.FAIL,  Verificationtext+" "+"page BreadCrumbs verification", Verificationtext+" "+"page did not have bread crumbs");
			screenshot();
		}
	}
	// Extent Reports method to generate reports
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports();
			extent.init(System.getProperty("user.dir") + "\\src\\com\\ktpl\\extentreports\\Testreport.html", true);
		}
		return extent;
	}
	// To Take Screen shot
	public static void screenshot() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotpath = System.getProperty("user.dir")+"\\src\\com\\ktpl\\screenshots\\" +System.currentTimeMillis() + ".png";
		FileUtils.copyFile(srcFile, new File(screenshotpath));
		getInstance();
		extent.attachScreenshot(screenshotpath);
	}
	// Mouse hover
	public static void mousehover(String webelemt) {
		// TODO Auto-generated method stub
		Actions act = new Actions(driver);
		act.moveToElement(checkPropertiesData(webelemt)).build().perform();
	}
	public static void mousehover(WebElement locator) {
		// TODO Auto-generated method stub
		Actions act = new Actions(driver);
		act.moveToElement(locator).build().perform();
	}
	// Add simple product
	public static void Addsimpleproduct(int numberofproducts) throws InterruptedException, IOException {

		for (int i = 1; i <= numberofproducts; i++) {
			try {
				driver.get("http://qa.solution.magentoprojects.net/gear/bags.html");
				UtilityMethods.mousehover(checkPropertiesData("plpsimpleproductname_xpath"));
				String simpleproductname = checkPropertiesData("plpsimpleproductname_xpath").getText();
				Thread.sleep(4000);
				checkPropertiesData("productaddtocartbutton_xpath").click();
				Thread.sleep(2000);
				verifyalert("added");
			
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unable to add product to cart");
				extent.log(LogStatus.FAIL, "Adding simple product", "unable to add product to cart");
				screenshot();
			}
		}
	}
	// Add configurable product
	public static void Addconfigproduct() throws InterruptedException, IOException {
		driver.get("http://magento2.nublue.co.uk/lando-gym-jacket.html");
		try {
			checkPropertiesData("coloroption").click();
			checkPropertiesData("sizeoption").click();
			checkPropertiesData("configaddtocart").click();
			Thread.sleep(3000);
			verifyalert("added");
		} catch (Exception e) {
			System.out.println("unable to add product");
			extent.log(LogStatus.FAIL, "Adding configuarable product", "unable to add configurable product");
			screenshot();
		}
	}

	public static void waitForElement(WebElement element) throws InterruptedException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOf(element));
	}		
 /*public static void waitForElement(WebElement element){
		 
		 WebDriverWait wait = new WebDriverWait(driver, 10);
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	 	}*/

	public static void verifyalert(String expectedtext) throws IOException {
		try {
			waitForElement(checkPropertiesData("alertxpath_xpath"));
			if (checkPropertiesData("alertxpath_xpath").getText().contains(expectedtext)) {
				System.out.println("Alert message is as expected");
				extent.log(LogStatus.PASS, "Alert verification", "Alert message displaying as expected");

			} else {
				extent.log(LogStatus.FAIL, "Alert meesage", "Alert message is not as expected");
				screenshot();
			}
		} catch (Exception e) {
			
			extent.log(LogStatus.FAIL, "Alert message", "unable to verify alert");
			System.out.println("Alert not displaying");
			screenshot();
			
		}
	}
	public static void verifypageTitle(String verificationtext){
	 if(driver.getTitle().toLowerCase().contains(verificationtext)){
		 System.out.println(verificationtext+" Title displaying as expected");		 
	 }else{
		 System.out.println(verificationtext+" page Title displaying is not as expected");		 
	 }
		
	}

}
