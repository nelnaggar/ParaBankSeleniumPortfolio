package com.qaautomation.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaautomation.parabank.base.BaseTest;
import com.qaautomation.parabank.model.Customer;
import com.qaautomation.parabank.pages.AccountsOverviewPage;
import com.qaautomation.parabank.pages.HomePage;

public class RegistrationTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void shouldRegisterNewCustomer() {
		Customer customer = Customer.createUniqueCustomer();

		AccountsOverviewPage accountsOverviewPage = new HomePage(driver).open().clickRegister()
				.registerCustomer(customer);

		Assert.assertEquals(accountsOverviewPage.getWelcomeHeading(), "Welcome " + customer.username(),
				"The welcome heading was incorrect.");

		Assert.assertTrue(accountsOverviewPage.getConfirmationMessage().contains("successfully"),
				"The registration confirmation was not displayed.");
	}
}