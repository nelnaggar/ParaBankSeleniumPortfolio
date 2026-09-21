package com.qaautomation.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaautomation.parabank.base.BaseTest;
import com.qaautomation.parabank.model.Customer;
import com.qaautomation.parabank.pages.OpenNewAccountPage;
import com.qaautomation.parabank.pages.HomePage;

public class AccountTest extends BaseTest {

	@Test(groups = { "regression" })
	public void shouldOpenSavingsAccount() {
		Customer customer = Customer.createUniqueCustomer();

		OpenNewAccountPage openNewAccountPage = new HomePage(driver).open().clickRegister().registerCustomer(customer)
				.clickOpenNewAccount().openSavingsAccount();

		Assert.assertEquals(openNewAccountPage.getResultHeading(), "Account Opened!",
				"The account-opening confirmation was incorrect.");

		String accountId = openNewAccountPage.getNewAccountId();

		Assert.assertFalse(accountId.isBlank(), "The new account ID was not displayed.");

		Assert.assertTrue(accountId.matches("\\d+"), "The new account ID was not numeric: " + accountId);
	}
}