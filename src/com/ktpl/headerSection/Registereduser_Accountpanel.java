package com.ktpl.headerSection;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ktpl.utitlityMethods.UtilityMethods;

public class Registereduser_Accountpanel extends BaseinitHeadersection {
	@BeforeTest
	public void RAcheckTestCaseExecution(){
		
		if(!UtilityMethods.checkTestCaseForExecution(headersection, this.getClass().getSimpleName() , "TestScript")){			
			throw new SkipException("Execution mode of the test case is set to NO");   //TestNG will skip execution of the method
		}	
		
	}

	@Test
	public static void RegisteredAccountpanel() throws InterruptedException, IOException{
		
		driver.get(storage.getProperty("userURL"));
		UtilityMethods.login();
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
			String Accountlinkname = checkPropertiesData("Accountddmyaccount_xpath").getText().toLowerCase();
			checkPropertiesData("Accountddmyaccount_xpath").click();
			UtilityMethods.verifypageTitle(Accountlinkname);
			}catch(Exception e){
				System.out.println("Accounticondropdown my account link is not displaying");
			}
		try{
			UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
			checkPropertiesData("HeaderAccounticon_xpath").click();
			UtilityMethods.waitForElement(checkPropertiesData("Accountddwishlist_xpath"));
			String wishlistlinkname = checkPropertiesData("Accountddwishlist_xpath").getText().toLowerCase();
			checkPropertiesData("Accountddwishlist_xpath").click();
			UtilityMethods.verifypageTitle(wishlistlinkname);
			}catch(Exception e){
				System.out.println("Account dropdown panel wish list is not displaying");
			}
		
		
		try{
			UtilityMethods.waitForElement(checkPropertiesData("HeaderAccounticon_xpath"));
			checkPropertiesData("HeaderAccounticon_xpath").click();
			UtilityMethods.waitForElement(checkPropertiesData("Accountddsignout_xpath"));
			checkPropertiesData("Accountddsignout_xpath").click();
			UtilityMethods.waitForElement(checkPropertiesData("logoutscreentext_xpath"));
			if(checkPropertiesData("logoutscreentext_xpath").getText().toLowerCase().contains("you are signed out"))
			{
				System.out.println("User looged out from account successfully and logout page text displaying as expected");
			}else{
				System.out.println("logout is not successfull");
			}
			}catch(Exception e){
				System.out.println("Account dropdown panel sign-in link  is not displaying");
			}
		
		driver.close();		
		
	}

	}