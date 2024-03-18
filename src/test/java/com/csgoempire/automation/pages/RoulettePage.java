package com.csgoempire.automation.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.csgoempire.automation.utilities.DriverWaits;
import com.csgoempire.automation.utilities.JavaScriptExecutors;

public class RoulettePage extends BasePage {
	
	// Here is a list of constants which are needed throughout the script. Notice that
	// Some locators are here AND in the Page Factory Find methods - this is because we actually
	// will need some of them later on when we execute Java Script code
	private static final String PAGE_URL = "https://csgoempire.com/roulette";
	private static final String ROULETTE_CLEAR_BET_BUTTON_LOCATOR = "//button[text()='Clear']";
	private static final String ROULETTE_BET_BUTTONS_CSS_SELECTOR = ".bet-input__controls-inner button";
	private static final String ROULETTE_BET_TEXT_FIELD_CSS_SELECTOR = ".bet-input__field input";
	private static final String RACE_RULES_HEADING_CSS_SELECTOR = ".vfm__content h2";
	private static final String RACE_RULES_FIRST_TEXT_CSS_SELECTOR = ".vfm__content p:nth-child(2)";
	private static final String RACE_RULES_SECOND_TEXT_CSS_SELECTOR = ".vfm__content p:nth-child(3)";
	
	@FindBy(css=ROULETTE_BET_BUTTONS_CSS_SELECTOR)
	private List<WebElement> rouletteBetButtons;
	
	// Here we are separately fetching only the clear button, since this one might be used in different scenarios -
	// we therefore avoid having to fetch all the buttons if it is not needed
	@FindBy(xpath=ROULETTE_CLEAR_BET_BUTTON_LOCATOR)
	private WebElement rouletteClearBetButton;
	
	@FindBy(css=ROULETTE_BET_TEXT_FIELD_CSS_SELECTOR)
	private WebElement rouletteBetTextField;
	
	@FindBy(xpath="//button/p[text()='Race rules']")
	private WebElement raceRulesButton;
	
	JavaScriptExecutors js = new JavaScriptExecutors(driver);
	
	public RoulettePage(WebDriver driver) {
		super(driver);
	}
	
	public RoulettePage openPage() {
		driver.get(PAGE_URL);
		logger.info("Opened the Roulette page at URL: " + PAGE_URL);
		return this;
	}
	
	public RoulettePage enterRouletteBet(String betAmount) {
		rouletteBetTextField.sendKeys(betAmount);
		logger.info("Entered a bet amount of " + betAmount);
		return this;
	}
	
	// Here we need to use Java Script code to make the element visible in DOM.
	// Otherwise this button always has an attribute "hidden", even though it is actually
	// visible to the user. If we do not do this - Selenium cannot interact with it.
	public RoulettePage clearRouletteBetTextField() {
		js.makeHiddenElementVisibleByXPath(ROULETTE_CLEAR_BET_BUTTON_LOCATOR);
		rouletteClearBetButton.click();
		logger.info("Clicked on the Clear bet button");
		return this;
	}
	
	// Since the buttons for increasing the bet do not have their own unique ids or other
	// unique attributes - we are relying on the button labels. However, they are not hardcoded in the code -
	// instead we specify them in the Cucumber scenario to lower the maintenance costs once something changes.
	public RoulettePage clickIncreaseRouletteBetButton(String buttonLabel) {
		js.makeAllHiddenElementsVisibleByCssLocator(ROULETTE_BET_BUTTONS_CSS_SELECTOR);
		WebElement selectedButton = rouletteBetButtons.stream()
			.filter(button -> {
				String buttonText = button.getText();
				return buttonText.equals(buttonLabel);
			})
			.findFirst()
			.orElse(null);
		
		if(selectedButton == null) {
			logger.error("Button with label " + buttonLabel + " was not found on the page.");
		}
		else {
			logger.info("Clicked on the " + buttonLabel + " Roulette Bet button");
		}
		
		selectedButton.click();
		return this;
	}
	
	public RoulettePage clickRaceRulesButton() {
		raceRulesButton.click();
		logger.info("Clicked on the Race Rules button");
		return this;
	}
	
	// Since the text field does not have its value as an actual HTML text value -
	// the only reliable way of fetching it no matter what is again through JS
	public String getCurrentBetValue() {
		return js.getValueOfAnElementByCssLocator(ROULETTE_BET_TEXT_FIELD_CSS_SELECTOR);
	}
	
	public WebElement getRouletteBetTextFieldElement() {
		return rouletteBetTextField;
	}
	
	public WebElement getRaceRulesHeadingElement() {
		return DriverWaits.waitSecondsForVisibilityOfAnElement(driver, 5, By.cssSelector(RACE_RULES_HEADING_CSS_SELECTOR));
	}
	
	public WebElement getRaceRulesFirstTextElement() {
		return DriverWaits.waitSecondsForVisibilityOfAnElement(driver, 5, By.cssSelector(RACE_RULES_FIRST_TEXT_CSS_SELECTOR));
	}
	
	public WebElement getRaceRulesSecondTextElement() {
		return DriverWaits.waitSecondsForVisibilityOfAnElement(driver, 5, By.cssSelector(RACE_RULES_SECOND_TEXT_CSS_SELECTOR));
	}
	
}
