package com.qaautomation.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage extends BasePage {

	private final By accountTypeDropdown = By.id("type");

	private final By sourceAccountDropdown = By.id("fromAccountId");

	private final By openAccountButton = By.xpath("//input[@value='Open New Account']");

	private final By resultHeading = By.xpath("//div[@id='openAccountResult']//h1");

	private final By newAccountId = By.id("newAccountId");

	private final By transferFundsLink = By.xpath("//a[normalize-space()='Transfer Funds']");

	public OpenNewAccountPage(WebDriver driver) {
		super(driver);
	}

	public OpenNewAccountPage openSavingsAccount() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));

		Select accountTypes = new Select(driver.findElement(accountTypeDropdown));

		accountTypes.selectByVisibleText("SAVINGS");

		wait.until(driverInstance -> {
			Select sourceAccounts = new Select(driverInstance.findElement(sourceAccountDropdown));

			return !sourceAccounts.getOptions().isEmpty();
		});

		click(openAccountButton);

		wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountId));

		return this;
	}

	public String getResultHeading() {
		return getText(resultHeading);
	}

	public String getNewAccountId() {
		return getText(newAccountId);
	}
	
	public TransferFundsPage clickTransferFunds() {
	    click(transferFundsLink);

	    return new TransferFundsPage(driver);
	}
}