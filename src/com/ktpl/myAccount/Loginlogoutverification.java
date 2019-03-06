package com.ktpl.myAccount;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Loginlogoutverification extends BaseinitMyAccount {
		@BeforeTest
		public void LFcheckTestCaseExecution(){
			if(!UtilityMethods.checkTestCaseForExecution(myaccount, this.getClass().getSimpleName() , "TestScript")){				
				throw new SkipException("Execution mode of the test case is set to NO");   //TestNG will skip execution of the method
			}				
		}
	@Test
		public static void logintest() throws Exception{
			UtilityMethods.getInstance();
			extent.startTest("Login test");		
		
			driver.get(storage.getProperty("userURL"));
			Thread.sleep(1000);
			UtilityMethods.login();
			UtilityMethods.logout();
  }	
}