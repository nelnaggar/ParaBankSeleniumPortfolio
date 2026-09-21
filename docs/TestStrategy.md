# ParaBank Test Strategy

## Objective

Validate the critical customer journeys of the ParaBank demonstration application through maintainable UI and API automation.

## Scope

### In Scope

- Homepage availability
- Customer registration
- Valid and invalid authentication
- Account creation
- Account overview
- Fund transfers
- API availability
- Cross-browser execution
- Headless execution
- Failure screenshots
- CI execution

### Out of Scope

- Performance and load testing
- Accessibility certification
- Production security testing
- Mobile-native applications
- Third-party system validation

## Test Levels

| Level | Coverage |
|---|---|
| UI | Customer journeys through Selenium WebDriver |
| API | Service availability through REST Assured |
| Integration | Interaction between authentication, accounts and transfers |
| CI | Automated headless execution after repository changes |

## Browser Coverage

- Google Chrome
- Mozilla Firefox
- Microsoft Edge

Chrome is used for the default CI execution. Firefox and Edge can be selected through Maven properties.

## Test Data

Registration tests generate unique customer data to reduce collisions between executions. Test data must not contain real personal or financial information.

## Entry Criteria

- Application is reachable
- Java 21 and Maven are available
- A supported browser is installed
- Required dependencies can be downloaded

## Exit Criteria

- Critical customer journeys have executed
- No unresolved critical automation failures remain
- Test reports have been generated
- Failure screenshots are available when applicable

## Risks

- The public demonstration environment may be unavailable or unstable
- Application data may persist between executions
- Browser updates can temporarily affect driver compatibility
- UI changes can invalidate locators

## Reporting

Maven Surefire generates execution reports under:

```text
target/surefire-reports