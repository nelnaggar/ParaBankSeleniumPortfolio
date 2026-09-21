# Test Coverage

## Automated Scenarios

<table>
  <thead>
    <tr>
      <th>ID</th>
      <th>Scenario</th>
      <th>Test Class</th>
      <th>Type</th>
      <th>Priority</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>PB-001</td>
      <td>Open the ParaBank homepage</td>
      <td><code>OpenParaBankTest</code></td>
      <td>UI</td>
      <td>High</td>
    </tr>
    <tr>
      <td>PB-002</td>
      <td>Register a unique customer</td>
      <td><code>RegistrationTest</code></td>
      <td>UI</td>
      <td>Critical</td>
    </tr>
    <tr>
      <td>PB-003</td>
      <td>Log in with valid credentials</td>
      <td><code>LoginTest</code></td>
      <td>UI</td>
      <td>Critical</td>
    </tr>
    <tr>
      <td>PB-004</td>
      <td>Reject invalid credentials</td>
      <td><code>LoginTest</code></td>
      <td>UI</td>
      <td>High</td>
    </tr>
    <tr>
      <td>PB-005</td>
      <td>Prevent login without credentials</td>
      <td><code>LoginTest</code></td>
      <td>UI</td>
      <td>High</td>
    </tr>
    <tr>
      <td>PB-006</td>
      <td>Open a savings account</td>
      <td><code>AccountTest</code></td>
      <td>UI</td>
      <td>Critical</td>
    </tr>
    <tr>
      <td>PB-007</td>
      <td>Transfer funds between accounts</td>
      <td><code>TransferFundsTest</code></td>
      <td>UI</td>
      <td>Critical</td>
    </tr>
    <tr>
      <td>PB-008</td>
      <td>Verify API availability</td>
      <td><code>ApiHealthTest</code></td>
      <td>API</td>
      <td>High</td>
    </tr>
  </tbody>
</table>

## Coverage Summary

- **Total automated scenarios:** 8
- **UI scenarios:** 7
- **API scenarios:** 1
- **Critical-priority scenarios:** 4
- **High-priority scenarios:** 4

## Framework Coverage

- Page Object Model for reusable UI interactions
- Separate package for API tests
- Unique customer data for isolated execution
- Configurable browser, environment and headless mode
- Chrome, Firefox and Microsoft Edge support
- Failure screenshots and Maven reports
- Automated execution through GitHub Actions and Jenkins