package com.ktpl.shoppingcart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Promocode extends BaseinitShoppingcart {
	@Test
	 public void BreadLinks() throws InterruptedException{
	  
	  driver.get("http://qa.solution.magentoprojects.net/");
	  //it will print the list of bread
	  String bread= driver.findElement(By.id("nav")).getText();
	  System.out.println(bread);
	  
	  //UtilityMethods.getInstance();
	  
	  
	  for (int main=1; main<=6;main++){
	   Thread.sleep(2000);
	   Actions Action= new Actions(driver);
	   Action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/ol/li["+main+"]/a"))).build().perform();
	 
	   WebElement subcategorypanerl= driver.findElement(By.xpath("//*[@id='nav']/ol/li["+main+"]/ul"));
	   List<WebElement> subcatlist =subcategorypanerl.findElements(By.tagName("a"));
	   System.out.println("Total number of subcategories "+ subcatlist.size());
	   int subcatcount =  subcatlist.size();
	  
	   
	   
	     
	   
	   
	    for(int sub=0;sub<=subcatcount-1;sub++){
	    String subcategoryname = subcatlist.get(sub).getText();
	    System.out.println("subcategory list name:--->"+subcategoryname);
	    subcatlist.get(sub).click();

	    Thread.sleep(2000);
	    Action.moveToElement(driver.findElement(By.xpath("//*[@id='nav']/ol/li["+main+"]/a"))).build().perform();
	     subcategorypanerl= driver.findElement(By.xpath("//*[@id='nav']/ol/li["+main+"]/ul"));
		 subcatlist =subcategorypanerl.findElements(By.tagName("a"));
	    
	    }
	   }
}
}
