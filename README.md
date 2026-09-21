# ParaBank Selenium Automation Framework

[![Maven Test Suite](https://github.com/nelnaggar/ParaBankSeleniumPortfolio/actions/workflows/maven-tests.yml/badge.svg)](https://github.com/nelnaggar/ParaBankSeleniumPortfolio/actions/workflows/maven-tests.yml)

A Java-based test automation framework for the ParaBank demonstration application.

The project demonstrates UI automation, API testing, Page Object Model design, cross-browser execution, configuration management, failure evidence and continuous integration.

## Technology Stack

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- REST Assured
- GitHub Actions
- Jenkins
- Git

## Framework Design

Reusable automation components are stored under `src/main/java`:

- Page Objects
- WebDriver factory
- Configuration reader
- Data models
- API clients

Test classes and test-specific components are stored under `src/test/java`.

The framework uses explicit waits and keeps element locators inside Page Objects. Element IDs are preferred when available, with relative XPath used as a fallback.

## Project Structure

```text
ParaBankSeleniumPortfolio
├── .github
│   └── workflows
│       └── maven-tests.yml
├── docs
│   ├── TestCoverage.md
│   └── TestStrategy.md
├── src
│   ├── main
│   │   ├── java/com/qaautomation/parabank
│   │   │   ├── api/client
│   │   │   ├── config
│   │   │   ├── driver
│   │   │   ├── model
│   │   │   └── pages
│   │   └── resources
│   │       └── config.properties
│   └── test
│       └── java/com/qaautomation/parabank
│           ├── api/tests
│           ├── base
│           ├── listeners
│           └── tests
├── .gitignore
├── Jenkinsfile
├── pom.xml
└── README.md
```

## Automated Scenarios

- Open the ParaBank homepage
- Register a unique customer
- Log in with valid credentials
- Reject invalid credentials
- Prevent authentication without credentials
- Open a savings account
- Transfer funds between accounts
- Verify API availability

## Supported Browsers

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

Chrome is the default browser. The browser can be overridden through the `browser` Maven property.

## Prerequisites

Before running the project, install:

- Java 21
- Maven 3.9 or later
- At least one supported browser

Verify the installations:

```bash
java --version
mvn --version
```

## Configuration

Default settings are stored in:

```text
src/main/resources/config.properties
```

Configuration values can be overridden from the command line using Maven system properties.

## Running the Tests

### Complete Test Suite

```bash
mvn clean test
```

### Headless Execution

```bash
mvn clean test -Dheadless=true
```

### Select a Browser

Chrome:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Firefox:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true
```

Microsoft Edge:

```bash
mvn clean test -Dbrowser=edge -Dheadless=true
```

### Override the Environment

```bash
mvn clean test -Dbase.url=https://parabank.parasoft.com/parabank/
```

Browser and environment overrides can be combined:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=true -Dbase.url=https://parabank.parasoft.com/parabank/
```

### Run a Specific Test Class

```bash
mvn test -Dtest=RegistrationTest
```

### Run a Specific Test Method

```bash
mvn test -Dtest=LoginTest#shouldLoginWithValidCredentials
```

### Run a TestNG Group

```bash
mvn test -Dgroups=smoke
mvn test -Dgroups=regression
mvn test -Dgroups=negative
mvn test -Dgroups=critical
mvn test -Dgroups=api
```

## Microsoft Edge Binary

Selenium Manager normally discovers the installed browser automatically.

If Microsoft Edge cannot be located, provide its executable path through the optional `edge.binary` property:

```cmd
mvn clean test -Dbrowser=edge -Dheadless=true "-Dedge.binary=C:\path\to\msedge.exe"
```

The Edge executable path is configurable because versioned installation directories may change after browser updates.

## Test Data

Registration scenarios generate unique customer information to reduce collisions between executions.

The project does not require real personal or financial information.

## Failure Evidence

When a UI test fails, the TestNG listener captures a screenshot under:

```text
target/screenshots
```

Maven Surefire reports are generated under:

```text
target/surefire-reports
```

The `target` directory is excluded from version control.

## Documentation

- [Test Strategy](docs/TestStrategy.md)
- [Test Coverage Matrix](docs/TestCoverage.md)

## Continuous Integration

### GitHub Actions

The GitHub Actions workflow runs the complete Maven test suite using:

- Java 21
- Google Chrome
- Headless execution
- Maven dependency caching

The workflow runs after pushes and pull requests targeting the `main` branch. It can also be started manually from the GitHub **Actions** tab.

Surefire reports are published as workflow artefacts. Failure screenshots are uploaded when test execution fails.

### Jenkins

The included `Jenkinsfile` supports parameterised execution by:

- Browser
- Test group
- Headless mode

Test results are published after execution, and failure screenshots are archived as build artefacts.

## Known Constraints

- ParaBank is a public demonstration environment and may occasionally be unavailable or unstable.
- Persistent application data can affect repeated executions.
- Browser updates may temporarily affect driver compatibility.
- UI changes may require Page Object locator updates.

## Repository

GitHub:

[github.com/nelnaggar/ParaBankSeleniumPortfolio](https://github.com/nelnaggar/ParaBankSeleniumPortfolio)