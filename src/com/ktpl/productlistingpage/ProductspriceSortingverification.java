package com.ktpl.productlistingpage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductspriceSortingverification extends Baseinitproductlistingpage {
	@Test
	  public void lowToHigh() throws Throwable
	  {         
		driver.get(storage.getProperty("ProductlisitngpageURL"));	        
		     driver.findElement(By.xpath("//*[@id='sorter']")).click();		     
		     Thread.sleep(2000);
		     driver.findElement(By.xpath("//*[@id='sorter']/option[3]")).click();	        
		     Thread.sleep(2000);
	        java.util.List<WebElement> price = driver.findElements(By.xpath("//*[@data-price-type='finalPrice']/span"));
	        System.out.println(price.size());
	        //List ourAl = new ArrayList<>();
	        for (int i = 0; i<price.size(); i=i+1) {
	        System.out.println(price.get(i).getText());          
	        }           	
	ArrayList<Float> priceList = new ArrayList<Float>();
    for (int i = 0; i<price.size(); i=i+1) { 			
        String price2 = price.get(i).getText();		
		//To replace Currency symbol
		String price3 = price2.replace('$', ' ');		
		System.err.println("Currency symbol replaced"+price2 );		
		//To remove spaces
		String price4 = price3.trim();		
		System.err.println("spaces removed"+price4);  						
		priceList.add(Float.parseFloat(price4)); 
    }  
    if(!ascendingCheck(priceList)){
        Assert.fail("Not is ascending order");
    }}
 //To validate the order,
     Boolean ascendingCheck(ArrayList<Float> data){         
        for (int i = 0; i < data.size()-1; i++) {
            if (data.get(i) > data.get(i+1)) {
                return false;
            }       
         }
         return true;
     }
}
