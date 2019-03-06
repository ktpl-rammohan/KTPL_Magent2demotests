package com.ktpl.mainNavigationmenu;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Brokenlinkchecker extends BaseinitMainNavigationMenu {

	@Test
	public static void Linkchecker() throws IOException {

		driver.get("https://Qa.solution.magentoprojects.net/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		for(int i=0;i<links.size();i++){
			WebElement element = links.get(i);
			String url = element.getAttribute("href");	
			verifylink(url);
		}
	}
	
	public static void verifylink(String linkurl) throws IOException{
	try{
		URL url = new URL(linkurl);
		HttpURLConnection httpconection = (HttpURLConnection)url.openConnection();
		httpconection.setConnectTimeout(3000);		
		httpconection.connect();
		if(httpconection.getResponseCode()==200){
			System.out.println(linkurl+"link working"+httpconection.getResponseMessage() );
		}else{
			System.out.println(linkurl+"link broken"+httpconection.getResponseMessage());
		}	
	}catch(Exception e){
		System.out.println(linkurl+e.getMessage());
	}
}
}