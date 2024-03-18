package com.csgoempire.automation.cucumber.step_definitions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.csgoempire.automation.constants.TextConstants;
import com.csgoempire.automation.driver.DriverSingleton;
import com.csgoempire.automation.pages.RoulettePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RoulettePageSteps {
	
	WebDriver driver = DriverSingleton.getDriver();
	RoulettePage roulettePage = new RoulettePage(driver);

	@Given("I am on the Roulette Page")
	public void i_am_on_the_roulette_page() {
	    roulettePage.openPage();
	}
	
	@Then("Bet amount text field contains placeholder text {string}")
	public void bet_amount_text_field_displays_placeholder_text(String expectedPlaceholderBetText) {
	    String actualPlaceholderBetText = roulettePage.getRouletteBetTextFieldElement().getAttribute("placeholder");
	    Assert.assertEquals(actualPlaceholderBetText, expectedPlaceholderBetText);
	}
	
	@When("I click CLEAR button")
	public void i_click_clear_button() {
	    roulettePage.clearRouletteBetTextField();
	}
	
	@Then("Bet amount equals to {string}")
	public void bet_amount_equals_to(String expectedBetValue) {
	    String actualBetValue = roulettePage.getCurrentBetValue();
	    Assert.assertEquals(actualBetValue, expectedBetValue);
	}
	
	// Here I am sending a TAB key to quickly switch focus from the text field so that it would get updated.
	// This method works here especially well since we are dealing with the text field to which we are just sending another key.
	@When("I enter {string} as a bet")
	public void i_enter_as_a_bet(String betValue) {
	    roulettePage.enterRouletteBet(betValue);
	    roulettePage.getRouletteBetTextFieldElement().sendKeys(Keys.TAB);
	}
	
	@When("I click on {string} bet button")
	public void i_click_on_bet_button(String buttonLabel) {
	    roulettePage.clickIncreaseRouletteBetButton(buttonLabel);
	}

	@When("I click on the Race Rules button")
	public void i_click_on_the_race_rules_button() {
	    roulettePage.clickRaceRulesButton();
	}
	@Then("Race Rules window opens with all the text being according to the documentation")
	public void race_rules_window_opens_with_all_the_text_being_according_to_the_documentation() {
	    Assert.assertEquals(
	    		roulettePage.getRaceRulesHeadingElement().getText(),
	    		TextConstants.ROULETTE_RACE_RULES_HEADING);
	    Assert.assertEquals(
	    		roulettePage.getRaceRulesFirstTextElement().getText(),
	    		TextConstants.ROULETTE_RACE_RULES_FIRST_TEXT);
	    Assert.assertEquals(
	    		roulettePage.getRaceRulesSecondTextElement().getText(),
	    		TextConstants.ROULETTE_RACE_RULES_SECOND_TEST);
	}
	
}
