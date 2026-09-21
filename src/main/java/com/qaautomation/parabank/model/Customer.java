package com.qaautomation.parabank.model;

public record Customer(String firstName, String lastName, String address, String city, String state, String zipCode,
		String phoneNumber, String ssn, String username, String password) {

	public static Customer createUniqueCustomer() {
		String uniqueNumber = String.valueOf(System.currentTimeMillis());

		return new Customer("Test", "Customer", "1 Automation Street", "Zurich", "ZH", "8000", "0440000000",
				uniqueNumber, "user" + uniqueNumber, "SecurePassword123!");
	}
}