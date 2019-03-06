package com.ktpl.mainNavigationmenu;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ktpl.utitlityMethods.UtilityMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Maincategories extends BaseinitMainNavigationMenu {

	@BeforeTest
	public void MCcheckTestCaseExecution() {
		logs.info("System checking execution status  of mainmenu testcase.......");
		if (!UtilityMethods.checkTestCaseForExecution(mainnavigationmenu, this.getClass().getSimpleName(),
				"TestScript")) {
			throw new SkipException("Execution mode of the test case is set to NO");
		}
	}

	@Test
	public static void Categories() throws InterruptedException, IOException {
		UtilityMethods.getInstance();
		driver.get(storage.getProperty("userURL"));
		extent.startTest("Maincategories test");
		extent.log(LogStatus.INFO, "Verifying Maincategories");	
	/*	WebElement maincategoriesdiv = driver.findElement(By.xpath(".//*[@role='presentation']"));
		System.out.println(maincategoriesdiv.findElements(By.tagName("li")).size());
	*/	
		for (int count=1; count <= 6; count++) {
			//Thread.sleep(2000); //*[@id="ui-id-3"]/span //*[@id="ui-id-2"]/li[2]
			UtilityMethods.waitForElement(driver.findElement(By.xpath("//*[@id='ui-id-2']/li[" + count + "]")));
			driver.findElement(By.xpath("//*[@id='ui-id-2']/li["+count+"]")).click();
			String Maincategoryname = driver.findElement(By.xpath("//*[@id='ui-id-2']/li["+count+"]")).getText();
			System.out.println("category"+count+" page title is :" + driver.getTitle());			
			if (Maincategoryname.equalsIgnoreCase(driver.getTitle())) {
				extent.log(LogStatus.PASS, "Verifying"+" " +Maincategoryname+" "+"page title" , Maincategoryname+" "+"Category page title displaying properly");
			} else {
				extent.log(LogStatus.FAIL, "Verifying"+" " +Maincategoryname+" "+"page title" , Maincategoryname+" "+"Category page title displaying is not properly. Sceenshot taken");
				UtilityMethods.screenshot();
			}		
			UtilityMethods.Verifybreadcrumbs(Maincategoryname);
			
		}
	}
}