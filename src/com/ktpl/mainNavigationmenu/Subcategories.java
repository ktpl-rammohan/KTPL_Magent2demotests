package com.ktpl.mainNavigationmenu;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Subcategories extends BaseinitMainNavigationMenu {

	@BeforeTest
	public void SCcheckTestCaseExecution() {
		if (!UtilityMethods.checkTestCaseForExecution(mainnavigationmenu, this.getClass().getSimpleName(),
				"TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO");
		}
	}
	@Test
	public static void subcategories() throws InterruptedException, IOException {
		UtilityMethods.getInstance();
		driver.get(storage.getProperty("userURL"));
		extent.startTest("Subcategories test");
		extent.log(LogStatus.INFO, "Verifying Subcategories");

		for (int count = 1; count <= 6; count++) {
		    UtilityMethods.waitForElement(driver.findElement(By.xpath("//*[@id='ui-id-2']/li["+count+"]")));
			String maincategory = driver.findElement(By.xpath("//*[@id='ui-id-2']/li["+count+"]")).getText();
			UtilityMethods.mousehover(driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]")));			  
			try{
				//*[@id='ui-id-2']/li[2]/ul
				UtilityMethods.waitForElement(driver.findElement(By.xpath("//*[@id='ui-id-2']/li["+count+"]/ul")));
			    WebElement subcategorypanel = driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]/ul"));
				List<WebElement> subcatdivlinks = subcategorypanel.findElements(By.tagName("a"));
				System.out.println("Total number of subcategories" + " " + subcatdivlinks.size());
				extent.log(LogStatus.INFO, "Verifying subcategories of" + " " + maincategory);
				for (int count2 = 0; count2 <= subcatdivlinks.size() - 1; count2++) {
					String Subcategoryname = subcatdivlinks.get(count2).getText();
					System.out.println(Subcategoryname);
					subcatdivlinks.get(count2).click();
					System.out.println("subcategory title is:" + driver.getTitle());
					UtilityMethods.Verifybreadcrumbs(Subcategoryname);
					if (driver.getTitle().contains(Subcategoryname)) {
						extent.log(LogStatus.PASS, "Verifying " + Subcategoryname + " page title",
								Subcategoryname + " " + "category page title displaying is as expected");
					} else {
						extent.log(LogStatus.FAIL, "Verifying " + Subcategoryname + " page title", Subcategoryname + " "
								+ "category page title displaying is not proper. (Screenshot taken)");
						UtilityMethods.screenshot();
					}
					UtilityMethods.mousehover(driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]")));
					UtilityMethods.waitForElement(driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]/ul")));
					subcategorypanel = driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]/ul"));
					subcatdivlinks = subcategorypanel.findElements(By.tagName("a"));
				}
			/*}else{
				System.out.println(maincategory+" category did not have subcategories(ifloop)");
			}*/
			    
			} catch (Exception e) {
				System.out.println(maincategory+" category did not have subcategories");
				extent.log(LogStatus.INFO, maincategory + "  " + " did not have subcategories");
			}			

		}
	}
}