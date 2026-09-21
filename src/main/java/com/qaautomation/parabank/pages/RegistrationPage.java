package com.qaautomation.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qaautomation.parabank.model.Customer;

public class RegistrationPage extends BasePage {

	private final By firstNameField = By.id("customer.firstName");

	private final By lastNameField = By.id("customer.lastName");

	private final By addressField = By.id("customer.address.street");

	private final By cityField = By.id("customer.address.city");

	private final By stateField = By.id("customer.address.state");

	private final By zipCodeField = By.id("customer.address.zipCode");

	private final By phoneNumberField = By.id("customer.phoneNumber");

	private final By ssnField = By.id("customer.ssn");

	private final By usernameField = By.id("customer.username");

	private final By passwordField = By.id("customer.password");

	private final By confirmPasswordField = By.id("repeatedPassword");

	private final By registerButton = By.xpath("//input[@type='submit' and @value='Register']");

	private final By successfulRegistrationHeading = By
			.xpath("//div[@id='rightPanel']//h1" + "[starts-with(normalize-space(),'Welcome ')]");

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public AccountsOverviewPage registerCustomer(Customer customer) {
		type(firstNameField, customer.firstName());
		type(lastNameField, customer.lastName());
		type(addressField, customer.address());
		type(cityField, customer.city());
		type(stateField, customer.state());
		type(zipCodeField, customer.zipCode());
		type(phoneNumberField, customer.phoneNumber());
		type(ssnField, customer.ssn());
		type(usernameField, customer.username());
		type(passwordField, customer.password());
		type(confirmPasswordField, customer.password());

		click(registerButton);

		wait.until(ExpectedConditions.visibilityOfElementLocated(successfulRegistrationHeading));

		return new AccountsOverviewPage(driver);
	}
}