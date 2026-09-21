# ParaBank Selenium Automation Framework

[![Maven Test Suite](https://github.com/nelnaggar/ParaBankSeleniumPortfolio/actions/workflows/maven-tests.yml/badge.svg)](https://github.com/nelnaggar/ParaBankSeleniumPortfolio/actions/workflows/maven-tests.yml)

A Java-based test automation framework for the ParaBank demonstration application.

The project demonstrates UI automation, API testing, cross-browser execution, configuration management, failure screenshots, test grouping, and CI integration.

## Technology Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- REST Assured
- Jenkins
- Git

## Framework Design

Reusable framework components are stored under `src/main/java`:

- Page Objects
- WebDriver factory
- Configuration reader
- Data models
- API clients

Test classes and test-specific utilities are stored under `src/test/java`.

## Project Structure

```text
src
├── main
│   ├── java/com/qaautomation/parabank
│   │   ├── api/client
│   │   ├── config
│   │   ├── driver
│   │   ├── model
│   │   └── pages
│   └── resources
│       └── config.properties
└── test
    └── java/com/qaautomation/parabank
        ├── api/tests
        ├── base
        ├── listeners
        └── tests