package com.qaautomation.parabank.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qaautomation.parabank.config.ConfigReader;

public final class ExtentReportManager {

	private static ExtentReports extentReports;

	private ExtentReportManager() {
	}

	public static synchronized ExtentReports getInstance() {
		if (extentReports == null) {
			createReport();
		}

		return extentReports;
	}

	private static void createReport() {
		Path reportDirectory = Paths.get("target", "extent-reports");

		try {
			Files.createDirectories(reportDirectory);
		} catch (IOException exception) {
			throw new IllegalStateException("Could not create the ExtentReports directory.", exception);
		}

		Path reportPath = reportDirectory.resolve("ExtentReport.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());

		sparkReporter.config().setDocumentTitle("ParaBank Test Automation Report");
		sparkReporter.config().setReportName("Automated Test Execution Results");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setEncoding("UTF-8");

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Browser", getConfigurationValue("browser", "chrome"));
		extentReports.setSystemInfo("Environment", getConfigurationValue("environment", "test"));
		extentReports.setSystemInfo("Headless", getConfigurationValue("headless", "false"));
		extentReports.setSystemInfo("Java", System.getProperty("java.version"));
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
	}

	private static String getConfigurationValue(String propertyName, String fallbackValue) {

		String systemValue = System.getProperty(propertyName);

		if (systemValue != null && !systemValue.isBlank()) {
			return systemValue;
		}

		String configurationValue = ConfigReader.getOptional(propertyName);

		if (configurationValue != null && !configurationValue.isBlank()) {
			return configurationValue;
		}

		return fallbackValue;
	}
}