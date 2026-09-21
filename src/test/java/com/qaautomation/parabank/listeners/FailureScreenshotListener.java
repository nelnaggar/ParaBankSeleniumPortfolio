package com.qaautomation.parabank.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qaautomation.parabank.base.BaseTest;

public class FailureScreenshotListener implements ITestListener {

	private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

	@Override
	public void onTestFailure(ITestResult result) {
		Object testInstance = result.getInstance();

		if (!(testInstance instanceof BaseTest baseTest)) {
			return;
		}

		WebDriver driver = baseTest.getDriver();

		if (!(driver instanceof TakesScreenshot screenshotDriver)) {
			return;
		}

		File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

		String methodName = result.getMethod().getMethodName();

		String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);

		Path screenshotDirectory = Path.of("target", "screenshots");

		Path destination = screenshotDirectory.resolve(methodName + "-" + timestamp + ".png");

		try {
			Files.createDirectories(screenshotDirectory);

			Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Failure screenshot saved to: " + destination.toAbsolutePath());

		} catch (IOException exception) {
			System.err.println("Could not save failure screenshot: " + exception.getMessage());
		}
	}
}