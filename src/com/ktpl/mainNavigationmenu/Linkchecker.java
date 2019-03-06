package com.ktpl.mainNavigationmenu;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.*;

public class Linkchecker extends BaseinitMainNavigationMenu {
  public static List<WebElement> findAllLinks(WebDriver driver)
  {
	 // List<WebElement> elementList = new ArrayList<WebElement>();
	  List<WebElement>  elementList = driver.findElements(By.tagName("a"));
	  elementList.addAll(driver.findElements(By.tagName("img")));
	  List<WebElement> finalList = new ArrayList<WebElement>();

	  for (WebElement element : elementList)
	  {
		  if(element.getAttribute("href") != null){
			  finalList.add(element);
		  }		  
	  }	
	  return finalList;
  }
	public static String isLinkBroken(URL url) throws Exception
	{
		url = new URL(storage.getProperty("userURL"));
		String response = "";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try
		{
		    connection.connect();
		    response = connection.getResponseMessage();	        
		    connection.disconnect();
		    return response;
		}
		catch(Exception exp)
		{
			return exp.getMessage();
		}  				
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UtilityMethods.getInstance();
		extent.startTest("Checking links");
		System.setProperty("webdriver.chrome.driver",
		System.getProperty("user.dir") + "//src//com//ktpl//unicodeTech//Files//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://magento2-demo.nexcess.net");
			//ff.get("http://www.yahoo.com/");		    

			List<WebElement>  alllinks = findAllLinks(driver);    

		    System.out.println("Total number of elements found " + alllinks.size());
		    extent.log(LogStatus.INFO,"Total number of elements found " + alllinks.size() );

		    for( WebElement element : alllinks){
		    	try
		    	{
			        System.out.println("URL: " + element.getAttribute("href")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));

		    		//System.out.println("URL: " + element.getAttribute("outerhtml")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));
			        extent.log(LogStatus.PASS,"URL: " + element.getAttribute("href")+ " returned " + isLinkBroken(new URL(element.getAttribute("href"))));		        

		    	}
		    	catch(Exception exp)
		    	{
		    		System.out.println("At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());	    		
                    extent.log(LogStatus.FAIL, "At " + element.getAttribute("innerHTML") + " Exception occured -&gt; " + exp.getMessage());
                  //  UtilityMethods.screenshot();
		    	}

		    }

	    }

	}