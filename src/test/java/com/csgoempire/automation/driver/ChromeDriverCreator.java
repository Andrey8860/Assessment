package com.csgoempire.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Here I was going to use WebDriverManager free library to automatically take care of Chrome Driver creation, but with the 2023
// changes to both Chrome and Chrome Driver I was unable to make it work despite a lot of possible solutions which I've tried.
// Therefore, since for the assessment purpose it is not very important, I am using a basic way of driver creation here.
public class ChromeDriverCreator {
	public WebDriver createWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		return new ChromeDriver(options);
	}
}
