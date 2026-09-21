package com.qaautomation.parabank.api.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class CustomerApiClient {

    private final String baseUrl;

    public CustomerApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getCustomer(int customerId) {
        return given()
                .baseUri(baseUrl)
                .pathParam("customerId", customerId)
        .when()
                .get("/services/bank/customers/{customerId}");
    }
}