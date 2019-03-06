package com.ktpl.productlistingpage;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Productlisitngcountverification extends Baseinitproductlistingpage {

	//To check product count while lazy loading enabled
	@Test
	public void productcount() throws IOException, InterruptedException {
		startup();
		driver.get(storage.getProperty("ProductlisitngpageURL"));
		if (driver.findElement(By.xpath("//*[@class='products wrapper grid products-grid']")).isDisplayed()) {
			String productscounttext = driver.findElement(By.xpath(".//*[@id='toolbar-amount']/span[3]")).getText();
			int totalproducts = Integer.parseInt(productscounttext);
			System.out.println("Products count showing on pagination panel is:" + " " + totalproducts);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			for (int second = 0;; second++) {
				if (second >= 10) {
					break;
				}
				jse.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
			}
			WebElement productsdiv = driver.findElement(By.xpath("//*[@class='products wrapper grid products-grid']"));
			List<WebElement> productcount = productsdiv.findElements(By.tagName("li"));
			System.out.println(productcount.size());
			if (totalproducts == productcount.size()) {
				System.out.println("Products count is as expected");
			} else {
				System.out.println("products count is not as expected");
			}
		} else {
			System.out.println(driver.getTitle() + " " + "category has no products");
			System.out.println("system displaying alert message " + " "
					+ driver.findElement(By.xpath("//*[@class='message info empty']")).getText());
		}
		driver.close();
	}
}