package com.qaautomation.parabank.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Map;

import com.qaautomation.parabank.config.ConfigReader;

public final class DriverFactory {

	private DriverFactory() {
	}

	public static WebDriver createDriver() {
		String browser = ConfigReader.get("browser").toLowerCase();

		boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

		return switch (browser) {
		case "chrome" -> createChromeDriver(headless);
		case "firefox" -> createFirefoxDriver(headless);
		case "edge" -> createEdgeDriver(headless);

		default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
		};
	}

	private static WebDriver createChromeDriver(boolean headless) {

		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", Map.of("autofill.profile_enabled", false, "autofill.address_enabled",
				false, "credentials_enable_service", false, "profile.password_manager_enabled", false));

		if (headless) {
			options.addArguments("--headless=new");
		}

		return new ChromeDriver(options);
	}

	private static WebDriver createFirefoxDriver(boolean headless) {

		FirefoxOptions options = new FirefoxOptions();

		if (headless) {
			options.addArguments("-headless");
		}

		return new FirefoxDriver(options);
	}

	private static WebDriver createEdgeDriver(boolean headless) {

		EdgeOptions options = new EdgeOptions();
		
		options.setBinary("C:\\Program Files (x86)\\Microsoft\\EdgeCore\\153.0.4234.32\\msedge.exe");

		options.setExperimentalOption("prefs", Map.of("autofill.profile_enabled", false, "autofill.address_enabled",
				false, "credentials_enable_service", false, "profile.password_manager_enabled", false));
		
		if (headless) {
			options.addArguments("--headless=new");
		}

		return new EdgeDriver(options);
	}
}