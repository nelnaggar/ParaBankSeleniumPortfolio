package com.qaautomation.parabank.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.qaautomation.parabank.driver.DriverFactory;
import com.qaautomation.parabank.listeners.FailureScreenshotListener;


@Listeners(FailureScreenshotListener.class)
public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = DriverFactory.createDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public WebDriver getDriver() {
	    return driver;
	}
}