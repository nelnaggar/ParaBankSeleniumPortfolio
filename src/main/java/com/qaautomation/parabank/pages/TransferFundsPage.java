package com.qaautomation.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {

    private final By amountField =
            By.id("amount");

    private final By sourceAccountDropdown =
            By.id("fromAccountId");

    private final By destinationAccountDropdown =
            By.id("toAccountId");

    private final By transferButton =
            By.xpath("//input[@value='Transfer']");

    private final By resultHeading =
            By.xpath(
                    "//h1[normalize-space()='Transfer Complete!']");

    private final By transferredAmount =
            By.id("amountResult");

    private final By sourceAccountResult =
            By.id("fromAccountIdResult");

    private final By destinationAccountResult =
            By.id("toAccountIdResult");

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public TransferFundsPage transferFunds(
            String amount,
            String sourceAccountId,
            String destinationAccountId) {

        waitUntilAccountIsAvailable(
                sourceAccountDropdown,
                sourceAccountId);

        waitUntilAccountIsAvailable(
                destinationAccountDropdown,
                destinationAccountId);

        type(amountField, amount);

        Select sourceAccounts = new Select(
                driver.findElement(sourceAccountDropdown));

        sourceAccounts.selectByValue(sourceAccountId);

        Select destinationAccounts = new Select(
                driver.findElement(destinationAccountDropdown));

        destinationAccounts.selectByValue(
                destinationAccountId);

        click(transferButton);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transferredAmount));

        return this;
    }

    private void waitUntilAccountIsAvailable(
            By dropdownLocator,
            String accountId) {

        wait.until(driverInstance -> {
            Select accounts = new Select(
                    driverInstance.findElement(
                            dropdownLocator));

            return accounts.getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getAttribute("value")
                                    .equals(accountId));
        });
    }

    public String getResultHeading() {
        return getText(resultHeading);
    }

    public String getTransferredAmount() {
        return getText(transferredAmount);
    }

    public String getSourceAccountResult() {
        return getText(sourceAccountResult);
    }

    public String getDestinationAccountResult() {
        return getText(destinationAccountResult);
    }
}