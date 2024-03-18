package com.csgoempire.automation.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/com/csgoempire/automation/cucumber/features/Roulette.feature",
		glue = "com.csgoempire.automation.cucumber.step_definitions")
public class Roulette extends AbstractTestNGCucumberTests {
	
}
