package com.qaautomation.parabank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public abstract class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	protected void type(By locator, String value) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		element.clear();
		element.sendKeys(value);
	}

	protected void click(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	protected String getText(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	protected List<String> getTexts(By locator) {
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		return elements.stream().map(WebElement::getText).toList();
	}
}