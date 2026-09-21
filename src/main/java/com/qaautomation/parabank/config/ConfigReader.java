package com.qaautomation.parabank.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

	private static final Properties PROPERTIES = new Properties();

	static {
		try (InputStream input = ConfigReader.class.getResourceAsStream("/config.properties")) {

			if (input == null) {
				throw new IllegalStateException("The config.properties file could not be found.");
			}

			PROPERTIES.load(input);

		} catch (IOException exception) {
			throw new IllegalStateException("The configuration could not be loaded.", exception);
		}
	}

	public static String get(String key) {
		String systemValue = System.getProperty(key);

		if (systemValue != null && !systemValue.isBlank()) {
			return systemValue;
		}

		String fileValue = PROPERTIES.getProperty(key);

		if (fileValue == null || fileValue.isBlank()) {
			throw new IllegalArgumentException("Missing configuration value: " + key);
		}

		return fileValue;
	}
}