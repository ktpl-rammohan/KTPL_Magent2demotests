package com.ktpl.headerSection;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ktpl.utitlityMethods.UtilityMethods;

public class Guestuser_Accountpanel extends BaseinitHeadersection {

@BeforeTest
public void GAcheckTestCaseExecution(){
	
	if(!UtilityMethods.checkTestCaseForExecution(headersection, this.getClass().getSimpleName() , "TestScript")){
		
		throw new SkipException("Execution mode of the test case is set to NO");   //TestNG will skip execution of the method
	}	
	
}

@Test
public static void GuestAccountpanel() throws InterruptedException, IOException{
	
	driver.get(storage.getProperty("userURL"));
	
	try{
	UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
	checkPropertiesData("HeaderAccounticon_xpath").click();
	}catch(Exception e){
		System.out.println("HeaderAccounticon is not displaying/ unable to click");
	}
	
	//*[@class='customer-menu']
	try{
		UtilityMethods.waitForElement(checkPropertiesData("Accounticondropdown_xpath"));
		checkPropertiesData("Accounticondropdown_xpath");
		}catch(Exception e){
			System.out.println("Accounticondropdown is not displaying");
		}
	try{
		UtilityMethods.waitForElement(checkPropertiesData("Accountddmyaccount_xpath"));
		//String Accountlinkname = checkPropertiesData("Accountddmyaccount_xpath").getText().toLowerCase();
		checkPropertiesData("Accountddmyaccount_xpath").click();
		UtilityMethods.verifypageTitle("customer login");
		}catch(Exception e){
			System.out.println("Accounticondropdown my account link is not displaying");
		}
	try{
		UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
		checkPropertiesData("HeaderAccounticon_xpath").click();
		UtilityMethods.waitForElement(checkPropertiesData("Accountddwishlist_xpath"));
		checkPropertiesData("Accountddwishlist_xpath").click();
		UtilityMethods.verifypageTitle("customer login");
		}catch(Exception e){
			System.out.println("Account dropdown panel wish list is not displaying");
		}
	try{
		UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
		checkPropertiesData("HeaderAccounticon_xpath").click();
		UtilityMethods.waitForElement(checkPropertiesData("Accountddcreateaccount_xpath"));
		checkPropertiesData("Accountddcreateaccount_xpath").click();
		UtilityMethods.verifypageTitle("create new customer account");
		}catch(Exception e){
			System.out.println("Account dropdown panel createaccount link is not displaying");
		}
	
	try{
		UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
		checkPropertiesData("HeaderAccounticon_xpath").click();
		UtilityMethods.waitForElement(checkPropertiesData("Accountddsignin_xpath"));
		checkPropertiesData("Accountddsignin_xpath").click();
		UtilityMethods.verifypageTitle("customer login");
		}catch(Exception e){
			System.out.println("Account dropdown panel sign-in link  is not displaying");
		}
	
	driver.close();
}

}