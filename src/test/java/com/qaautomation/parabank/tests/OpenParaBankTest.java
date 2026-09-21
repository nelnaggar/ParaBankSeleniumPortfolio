package com.qaautomation.parabank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaautomation.parabank.base.BaseTest;
import com.qaautomation.parabank.pages.HomePage;

public class OpenParaBankTest extends BaseTest {

	@Test(groups = { "smoke", "regression" })
	public void shouldOpenParaBankHomepage() {
		HomePage homePage = new HomePage(driver);

		homePage.open();

		Assert.assertTrue(homePage.getPageTitle().contains("ParaBank"), "The page title did not contain ParaBank.");

		Assert.assertEquals(homePage.getCustomerLoginHeading(), "Customer Login",
				"The customer login heading was incorrect.");
	}
}