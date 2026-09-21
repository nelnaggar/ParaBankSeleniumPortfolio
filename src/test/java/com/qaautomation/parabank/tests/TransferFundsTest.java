package com.qaautomation.parabank.tests;

import java.math.BigDecimal;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaautomation.parabank.base.BaseTest;
import com.qaautomation.parabank.model.Customer;
import com.qaautomation.parabank.pages.AccountsOverviewPage;
import com.qaautomation.parabank.pages.HomePage;
import com.qaautomation.parabank.pages.OpenNewAccountPage;
import com.qaautomation.parabank.pages.TransferFundsPage;

public class TransferFundsTest extends BaseTest {

	@Test(groups = { "critical", "regression" })
	public void shouldTransferFundsBetweenAccounts() {
		Customer customer = Customer.createUniqueCustomer();

		AccountsOverviewPage accountsOverviewPage = new HomePage(driver).open().clickRegister()
				.registerCustomer(customer).openAccountsOverview();

		List<String> originalAccounts = accountsOverviewPage.getAccountIds();

		String sourceAccountId = originalAccounts.get(0);

		OpenNewAccountPage openNewAccountPage = accountsOverviewPage.clickOpenNewAccount().openSavingsAccount();

		String destinationAccountId = openNewAccountPage.getNewAccountId();

		TransferFundsPage transferFundsPage = openNewAccountPage.clickTransferFunds().transferFunds("25.00",
				sourceAccountId, destinationAccountId);

		Assert.assertEquals(transferFundsPage.getResultHeading(), "Transfer Complete!",
				"The transfer confirmation was incorrect.");

		BigDecimal expectedAmount = new BigDecimal("25.00");

		String displayedAmount = transferFundsPage.getTransferredAmount();

		String numericAmount = displayedAmount.replace("$", "").trim();

		BigDecimal actualAmount = new BigDecimal(numericAmount);

		Assert.assertEquals(actualAmount.compareTo(expectedAmount), 0, "The transferred amount was incorrect.");

		Assert.assertEquals(transferFundsPage.getSourceAccountResult(), sourceAccountId,
				"The source account was incorrect.");

		Assert.assertEquals(transferFundsPage.getDestinationAccountResult(), destinationAccountId,
				"The destination account was incorrect.");
	}
}