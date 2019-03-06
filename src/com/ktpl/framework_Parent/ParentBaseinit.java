package com.ktpl.framework_Parent;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.unicodetechnologies.xlsConnectivity.XLSDatatable_Connectivity;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ParentBaseinit {

	// Initializing
	public static Properties storage = null;
	public static Logger logs = null;
	public static XLSDatatable_Connectivity footersection = null;
	public static XLSDatatable_Connectivity myaccount = null;
	public static XLSDatatable_Connectivity headersection = null;
	public static XLSDatatable_Connectivity mainnavigationmenu = null;
	public static XLSDatatable_Connectivity checkoutSection = null;
	public static XLSDatatable_Connectivity productlistingpage = null;
	public static XLSDatatable_Connectivity shoppingcart = null;
	public static XLSDatatable_Connectivity suite = null;
	public static ExtentReports extent = null;
	public static WebDriver driver = null;
	

	public  void startup() throws IOException {

		if (driver == null) {
			// logger logs = logger.
			logs = Logger.getLogger("devpinoyLogger");
			logs.info("Properties is now loading.......");
			storage = new Properties();
			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "//src//com//ktpl//myProperties//FrameworkStorage.properties");
			storage.load(fi);
			logs.info("properties file loaded successfully");
			logs.info("Lanching Browser");

			if (storage.getProperty("Browser").equalsIgnoreCase("firefox")) {
				logs.info("opening firefox");
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + "//src//com//ktpl//drivers//geckodriver.exe");

		
			} else if (storage.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				logs.info("Opening chrome browser");
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//com//ktpl//drivers//chromedriver.exe");

				 driver = new ChromeDriver();

			} else if (storage.getProperty("browser").equalsIgnoreCase("ie")) {
				logs.info("Opening IE browser");
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//src//com//ktpl//unicodeTech//Files//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			footersection = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//footerSection.xlsx");
			myaccount = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//myAccount.xlsx");
			headersection = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//headerSection.xlsx");
			mainnavigationmenu = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//mainNavigationmenu.xlsx");
			checkoutSection = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//checkoutSection.xlsx");
			productlistingpage = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//productlistingpage.xlsx");
			suite = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//TestSuite.xlsx");
			shoppingcart = new XLSDatatable_Connectivity(
					System.getProperty("user.dir") + "//src//com//ktpl//myXLSfiles//shoppingcart.xlsx");

		}

	}

	
	public static WebElement checkPropertiesData(String key) {
		if (key.contains("xpath")) {
			try {
				return driver.findElement(By.xpath(storage.getProperty(key)));
			} catch (Throwable t) {
				System.out.println("WebElement " + key + " xpath is not found in the properties file");
				return null;
			}
		} else if (key.contains("id")) {
			try {
				return driver.findElement(By.id(storage.getProperty(key)));
			} catch (Throwable t) {
				System.out.println("WebElemnt id is not found in the properties file");
				return null;
			}
		} else {
			System.out.println("WebElement name in properties file doesn't have xpath/id ");
		}

		return null;
	}
}