# Test Coverage

| ID | Area | Scenario | Automation Class | Type | Priority |
|---|---|---|---|---|---|
| PB-001 | Homepage | Open the ParaBank homepage | `OpenParaBankTest` | UI | High |
| PB-002 | Registration | Register a unique customer | `RegistrationTest` | UI | Critical |
| PB-003 | Authentication | Log in with valid credentials | `LoginTest` | UI | Critical |
| PB-004 | Authentication | Reject invalid credentials | `LoginTest` | UI | High |
| PB-005 | Authentication | Prevent login without credentials | `LoginTest` | UI | High |
| PB-006 | Accounts | Open a savings account | `AccountTest` | UI | Critical |
| PB-007 | Transfers | Transfer funds between accounts | `TransferFundsTest` | UI | Critical |
| PB-008 | API | Verify API availability | `ApiHealthTest` | API | High |

## Coverage Notes

- Critical customer journeys are automated.
- UI tests use the Page Object Model.
- API tests are maintained in a separate test package.
- Unique customer data reduces conflicts between executions.
- Browser and environment settings can be overridden through Maven properties.
- Failed UI tests generate screenshots for investigation.