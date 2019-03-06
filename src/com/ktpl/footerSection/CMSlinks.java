package com.ktpl.footerSection;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;

public class CMSlinks extends BaseinitFootersection {

	@BeforeTest
	public void CMScheckTestCaseExecution() {
		logs.info("System checking execution status  of CMS links Testcase.......");

		if (!UtilityMethods.checkTestCaseForExecution(footersection, this.getClass().getSimpleName(), "TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO"); 
		}
	}

	@Test
	public static void CMS() throws Exception {

		UtilityMethods.getInstance();
		logs.info("CMSlinks verification Testcase execution started.......");
		extent.startTest("CMS links Test");
		extent.log(LogStatus.INFO, "Verifying CMS links");
		driver.get(storage.getProperty("userURL"));																																																																														
		WebElement links = driver.findElement(By.xpath("/html/body/div[1]/footer/div"));
		//	WebElement links = checkPropertiesData("CMS_Div2");
		List<WebElement> linklist = links.findElements(By.tagName("a"));		
		System.out.println("Total numer of CMS Links:" + linklist.size());
		System.out.println(links.getText());
		System.out.println();
		System.out.println("  ");		
		for (int count = 0; count < linklist.size(); count++) {
			System.out.println(count);
			String CMSlinkname = linklist.get(count).getText();
			System.out.println("CMS link text:" + CMSlinkname);
			linklist.get(count).click();
			System.out.println("CMS page title:" + driver.getTitle());
			UtilityMethods.Verifybreadcrumbs(CMSlinkname);
			// Screen shot
			if (!CMSlinkname.equalsIgnoreCase(driver.getTitle())) {
				extent.log(LogStatus.FAIL,
						CMSlinkname + " " + "Page title is not proper or redirected to error page, screenshot taken");
				System.out.println("Page title is not proper or redirected to error page, screenshot taken");
				UtilityMethods.screenshot();
			} else {
				extent.log(LogStatus.PASS, CMSlinkname + " " + "Page title is  as expected");
			}		
			driver.navigate().back();			
			links = driver.findElement(By.xpath("/html/body/div[1]/footer/div"));
	//		links = checkPropertiesData("CMS_Div2");
			linklist = links.findElements(By.tagName("a"));
			System.out.println();
		}
	}
}