package com.csgoempire.automation.driver;

import org.openqa.selenium.WebDriver;

// This class utilizes a Singleton pattern, with which we can only have one 
// active driver at a time and we make sure that it is closed when not needed
public class DriverSingleton {
	
	private static WebDriver driver;

	private DriverSingleton() {

	}
	
	// In this method, if we would use environment varibles, we could pull a specific browser which we want to run our tests in
	// This environment variable could be handled in this one specific method, enabling encapsulation
	
	public static WebDriver getDriver() {
		if (driver == null) {
				driver = new ChromeDriverCreator().createWebDriver();
			}
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void closeDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
