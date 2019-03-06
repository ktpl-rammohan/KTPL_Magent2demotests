package com.ktpl.myAccount;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Accountdashboard extends BaseinitMyAccount {
	
	@BeforeTest
	public void CMScheckTestCaseExecution() {
		logs.info("System checking execution status  of CMS links Testcase.......");
		if (!UtilityMethods.checkTestCaseForExecution(myaccount, this.getClass().getSimpleName(), "TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO"); // TestNG will skip execution of method																					
		}
	}
	@Test
	public void myaccount() throws InterruptedException, IOException {
		UtilityMethods.getInstance();
        extent.startTest("Testing My Account menu");	
		driver.get(storage.getProperty("userURL"));
		UtilityMethods.login();
		Thread.sleep(2000);
		UtilityMethods.waitForElement(checkPropertiesData("MyAccountmenu_xpath"));
		WebElement Accountmenu = checkPropertiesData("MyAccountmenu_xpath");
		List<WebElement> accountmenulist = Accountmenu.findElements(By.tagName("a"));
		System.out.println(accountmenulist.size());
		int Myaccountcatcount = accountmenulist.size()-1;
		for (int i = 0; i <=Myaccountcatcount; i++) {			
			String Accountmenupage = accountmenulist.get(i).getText();
			System.out.println(Accountmenupage);
			accountmenulist.get(i).click();
			Thread.sleep(2000);
			if (checkPropertiesData("MYAccountblocktitle_xpath").getText().contains(Accountmenupage)) {
				extent.log(LogStatus.PASS, "Verifying" + Accountmenupage,
						Accountmenupage + " " + "block title is displaying as expected");
			} else {
				extent.log(LogStatus.FAIL, "Verifying" + Accountmenupage,
						Accountmenupage + " " + "block title is displaying is not as expected. (Screenshot taken)");
				UtilityMethods.screenshot();
			}
			System.out.println(checkPropertiesData("MYAccountblocktitle_xpath").getText());
			Accountmenu = checkPropertiesData("MyAccountmenu_xpath");
			accountmenulist = Accountmenu.findElements(By.tagName("a"));					
		}
	}
}
