package com.qaautomation.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qaautomation.parabank.config.ConfigReader;

public class HomePage extends BasePage {

	private final By customerLoginHeading = By.xpath("//div[@id='leftPanel']//h2");

	private final By usernameField = By.xpath("//input[@name='username']");

	private final By passwordField = By.xpath("//input[@name='password']");

	private final By loginButton = By.xpath("//input[@type='submit' and @value='Log In']");

	private final By loginErrorMessage = By.xpath("//div[@id='rightPanel']//*[contains(@class,'error')]");

	private final By registerLink = By.xpath("//a[normalize-space()='Register']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage open() {
		String baseUrl = ConfigReader.get("base.url");

		driver.get(baseUrl + "/index.htm");

		wait.until(ExpectedConditions.visibilityOfElementLocated(customerLoginHeading));

		return this;
	}

	private void enterLoginCredentials(
	        String username,
	        String password) {

	    type(usernameField, username);
	    type(passwordField, password);
	    click(loginButton);
	}

	public AccountsOverviewPage loginSuccessfully(
	        String username,
	        String password) {

	    enterLoginCredentials(username, password);

	    return new AccountsOverviewPage(driver);
	}

	public HomePage attemptInvalidLogin(
	        String username,
	        String password) {

	    enterLoginCredentials(username, password);

	    return this;
	}

	public String getCustomerLoginHeading() {
		return getText(customerLoginHeading);
	}

	public String getLoginErrorMessage() {
		return getText(loginErrorMessage);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public RegistrationPage clickRegister() {
		click(registerLink);

		return new RegistrationPage(driver);
	}
	
	public boolean isLoginButtonDisplayed() {
	    return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    loginButton))
	            .isDisplayed();
	}
}