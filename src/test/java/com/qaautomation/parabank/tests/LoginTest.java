package com.qaautomation.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaautomation.parabank.base.BaseTest;
import com.qaautomation.parabank.pages.HomePage;

import com.qaautomation.parabank.model.Customer;
import com.qaautomation.parabank.pages.AccountsOverviewPage;

public class LoginTest extends BaseTest {

	@Test(groups = { "negative", "regression" })
	public void shouldNotAuthenticateWithoutCredentials() {
		HomePage homePage = new HomePage(driver);

		homePage.open().attemptInvalidLogin("", "");

		String actualError = homePage.getLoginErrorMessage();

		Assert.assertFalse(actualError.isBlank(), "No login error message was displayed.");

		Assert.assertTrue(homePage.isLoginButtonDisplayed(),
				"The login form disappeared, indicating possible authentication.");
	}

	@Test(groups = { "smoke", "regression" })
	public void shouldLoginWithValidCredentials() {
		Customer customer = Customer.createUniqueCustomer();

		AccountsOverviewPage accountsOverviewPage = new HomePage(driver).open().clickRegister()
				.registerCustomer(customer);

		HomePage homePage = accountsOverviewPage.logout();

		AccountsOverviewPage loggedInPage = homePage.loginSuccessfully(customer.username(), customer.password());

		Assert.assertEquals(loggedInPage.getWelcomeHeading(), "Accounts Overview",
				"The accounts overview page was not displayed.");
	}
}