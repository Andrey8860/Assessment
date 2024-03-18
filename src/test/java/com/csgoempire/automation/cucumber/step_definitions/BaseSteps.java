package com.csgoempire.automation.cucumber.step_definitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import com.csgoempire.automation.utilities.ScreenshotTaker;
import com.csgoempire.automation.driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseSteps {
	
	private final static String PASSED_SCREENSHOTS_PATH = ".//target/screenshots/passed/";
	private final static String FAILED_SCREENSHOTS_PATH = ".//target/screenshots/failed/";
	
	// Here I provided an implicit wait for the whole website, though I am aware that in general this is a bad practice.
	// I used it here to reduce the amount testing I would need to do with my code, such as low network bandwidth scenario etc.
	@Before
	public void setUp() {
		DriverSingleton.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@After
	public void tearDown() {
		DriverSingleton.closeDriver();
	}
	
	// Here we are taking screenshots each time a test runs, including when it's successful.
	@After
	public void takeScreenshot(Scenario scenario) throws IOException{
	    File screenshot;
		
		if(scenario.isFailed()) {
	    	screenshot = ScreenshotTaker.saveScreenshot(FAILED_SCREENSHOTS_PATH);
	    } else {
	    	screenshot = ScreenshotTaker.saveScreenshot(PASSED_SCREENSHOTS_PATH);
	    }
		
		scenario.attach(Files.readAllBytes(screenshot.toPath()), "image/png", scenario.getName());
	}
}