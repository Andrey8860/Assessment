package com.csgoempire.automation.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

// A class which contains JS methods inside so that we avoid hardcoding any JS code
// inside other classes. What these methods do, themselves, is very easy - two of them
// make "hidden" elements visible and accessible by Selenium, and the third one just fetches
// a value from the text field of the bet amount. Difference in code is simply due to different
// ways of fetching the elements - through XCode or CSS.
public class JavaScriptExecutors {

	private WebDriver driver;
	private JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;

	public JavaScriptExecutors(WebDriver driver) {
		this.driver = driver;
		this.javaScriptExecutor = (JavascriptExecutor) driver;
	}

	public void makeHiddenElementVisibleByXPath(String xpath) {
		javaScriptExecutor.executeScript("document.evaluate(\"" + xpath + "\", "
				+ "document, "
				+ "null, "
				+ "XPathResult.FIRST_ORDERED_NODE_TYPE, "
				+ "null)"
				+ ".singleNodeValue.style.display = 'block'");
	}
	
	public void makeAllHiddenElementsVisibleByCssLocator(String cssLocator) {
		String script = "let elements = document.querySelectorAll('" + cssLocator + "');"
				+ "for (let i = 0; i < elements.length; i++) {"
				+ "elements[i].style.display = 'block';}";
		javaScriptExecutor.executeScript(script);
	}

	public String getValueOfAnElementByCssLocator(String cssLocator) {
		return (String) javaScriptExecutor.executeScript("return document.querySelector('" + cssLocator + "').value");
	}
}
