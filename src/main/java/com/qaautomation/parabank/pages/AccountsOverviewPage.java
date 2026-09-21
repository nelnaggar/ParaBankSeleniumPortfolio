package com.qaautomation.parabank.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountsOverviewPage extends BasePage {

	private final By welcomeHeading = By.xpath("//div[@id='rightPanel']//h1");

	private final By confirmationMessage = By.xpath("//div[@id='rightPanel']//p");

	private final By logoutLink = By.xpath("//a[normalize-space()='Log Out']");

	private final By openNewAccountLink = By.xpath("//a[normalize-space()='Open New Account']");

	private final By accountLinks = By.xpath("//table[@id='accountTable']//a");

	private final By accountsOverviewLink = By.xpath("//a[normalize-space()='Accounts Overview']");

	public AccountsOverviewPage(WebDriver driver) {
		super(driver);
	}

	public String getWelcomeHeading() {
		return getText(welcomeHeading);
	}

	public String getConfirmationMessage() {
		return getText(confirmationMessage);
	}

	public HomePage logout() {
		click(logoutLink);

		return new HomePage(driver);
	}

	public OpenNewAccountPage clickOpenNewAccount() {
		click(openNewAccountLink);

		return new OpenNewAccountPage(driver);
	}

	public List<String> getAccountIds() {
		return getTexts(accountLinks);
	}

	public AccountsOverviewPage openAccountsOverview() {
		click(accountsOverviewLink);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountLinks));

		return this;
	}
}