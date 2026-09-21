package com.qaautomation.parabank.api.tests;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.Test;

import com.qaautomation.parabank.api.client.CustomerApiClient;
import com.qaautomation.parabank.config.ConfigReader;

import io.restassured.response.Response;

public class ApiHealthTest {

	@Test(groups = { "api", "smoke", "regression" })
	public void shouldReturnCustomerFromApi() {
		CustomerApiClient customerApiClient = new CustomerApiClient(ConfigReader.get("base.url"));

		Response response = customerApiClient.getCustomer(12212);

		response.then().statusCode(200).contentType(containsString("xml")).body("customer.id", equalTo("12212"))
				.body("customer.firstName", not(blankOrNullString()))
				.body("customer.lastName", not(blankOrNullString()));
	}
}