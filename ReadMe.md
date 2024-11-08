
---

# FNB PTAF - Playwright Test Automation Framework

## Overview

The **FNB PTAF** (Playwright Test Automation Framework) is an advanced test automation framework designed for automated end-to-end testing of web applications. It leverages [Playwright](https://playwright.dev/) for browser automation, [Cucumber](https://cucumber.io/) for implementing Behavior-Driven Development (BDD), and [TestNG](https://testng.org/) or [JUnit](https://junit.org/junit5/) for test execution. The framework is built with flexibility, maintainability, and scalability in mind, allowing tests to run in multiple environments (e.g., QA, staging, development) across different browsers (e.g., Chrome, Firefox, WebKit). Detailed reporting and easy-to-understand Gherkin scenarios allow non-technical team members to participate in the testing process.

The key aspects of **FNB PTAF** are:
- **Cross-browser testing** to ensure your application works across all major browsers.
- **Parallel execution** to speed up test execution, allowing tests to run concurrently in different environments or on different browsers.
- **Extensive reporting** using ExtentReports for comprehensive HTML reports that include screenshots, execution timelines, and pass/fail status for each test.
- **Environment-based configuration**, allowing easy switching between different URLs (e.g., staging, production) without modifying the core test logic.
- **Reusable utilities** for common actions (e.g., click, type, select) and assertions, making test development faster and easier to maintain.

---

## Key Features

### 1. Cross-Browser Support
The framework supports multiple browsers (Chrome, Firefox, and WebKit) using Playwright. Tests can be configured to run on different browsers depending on the needs of the testing phase.

### 2. Parallel Execution
By leveraging TestNG and JUnit, the framework allows tests to run in parallel, significantly reducing the time required to execute large test suites.

### 3. Behavior-Driven Development (BDD)
Cucumber is used to write test scenarios in Gherkin syntax, which makes the tests easier to understand for non-technical team members. Each scenario is linked to a step definition in Java, which implements the test logic using Playwright.

### 4. Environment-Based Configuration
Using YAML configuration files, the framework allows easy switching between environments (e.g., `rnd_url`, `qa_url`, `prod_url`) without altering the test code. This allows tests to be run in multiple environments, from development to production.

### 5. Detailed Reporting
The framework generates detailed HTML reports using ExtentReports, providing:
- A summary of passed, failed, and skipped tests.
- Screenshots of failures or specific steps.
- Execution timelines to visualize test progress and parallel execution.

---

## Project Structure

The **FNB PTAF** project follows a well-organized directory structure that separates test logic from configuration and reports. Here’s a breakdown of the structure:

```plaintext
FNB-PTAF/
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── com/
│       │   │   └── ptaf/
│       │   │       ├── hooks/               # Hooks for pre- and post-test actions
│       │   │       ├── pages/               # Page objects and utility methods
│       │   │       ├── runners/             # Test runners (TestNG/JUnit)
│       │   │       └── stepdefinitions/     # Cucumber step definitions
│       │   └── resources/
│       │       └── features/                # Cucumber feature files
│       │       └── elements/                # YAML files for locators and configurations
│
├── target/                                   # Output directory for reports and logs
│   └── screenshots/                          # Captured screenshots of test failures
│
└── pom.xml                                   # Maven configuration file
```

---

### Directory Breakdown

#### 1. `src/test/java/com/ptaf/hooks/`
This directory contains the **Hooks** used in Cucumber to set up and tear down the test environment. Hooks are used to define actions that need to be performed before or after each scenario, such as:
- Initializing the browser using Playwright.
- Taking screenshots when a scenario fails.
- Closing the browser after the test completes.

**Key File:**
- **`Hooks.java`**:
    - Defines the `@Before` hook that sets up the browser before each test starts.
    - Defines the `@After` hook that takes a screenshot if a test fails and closes the browser after the test finishes.

#### 2. `src/test/java/com/ptaf/pages/`
The **Pages** directory contains utility classes that abstract common actions and assertions. These classes provide reusable methods that make it easier to interact with web elements and assert conditions, such as checking if an element is visible or clicking a button.

**Key Files:**
- **`CommonMethods.java`**:
    - Provides methods for performing common actions on the web page, such as clicking buttons, filling out forms, and selecting dropdown options.
- **`AssertionUtil.java`**:
    - Contains custom assertion methods for verifying page states, such as checking if an element contains the correct text or if a button is visible.
- **`ElementHandle.java`**:
    - Manages specific interactions with Playwright’s `ElementHandle` for more complex or low-level element interactions.

#### 3. `src/test/java/com/ptaf/runners/`
The **Runners** directory contains the entry point for test execution. These classes are responsible for configuring how the tests are run and which feature files are executed. The framework supports both TestNG and JUnit runners.

**Key Files:**
- **`ParallelRun.java`**:
    - A TestNG-based runner that allows tests to be executed in parallel. Parallel execution speeds up test execution by running multiple tests concurrently.
- **`TestRunner.java`**:
    - A JUnit-based runner that runs tests sequentially, executing the scenarios defined in the Cucumber feature files.

#### 4. `src/test/java/com/ptaf/stepdefinitions/`
This directory contains the **step definitions**, which link Cucumber steps in the feature files to the corresponding code. Each step in a Gherkin scenario (e.g., "Given the user is on the login page") is implemented in these files.

**Key File:**
- **`CommonSteps.java`**:
    - Implements common step definitions for actions like navigating to URLs, filling out forms, clicking buttons, and verifying page content.

#### 5. `src/test/resources/features/`
The **features** directory contains Cucumber feature files. These files describe the application behavior in Gherkin syntax, which is easy for non-developers to understand.

Example:
```gherkin
Feature: User Login

  Scenario: Successful login
    Given the user navigates to "qa_url"
    When the user enters "username" in the "login" field
    And the user enters "password" in the "password" field
    And the user clicks the "submit" button
    Then the user should be redirected to the homepage
```

#### 6. `src/test/resources/elements/`
The **elements** directory contains YAML files that define the locators for elements on various pages, as well as environment-specific configurations like URLs. Storing these values in YAML files allows the framework to be more maintainable and flexible.

**Key Files:**
- **`elements.yml`**: Defines locators for various elements on the pages.
- **`config.yml`**: Stores environment-specific configurations, such as URLs for different environments (e.g., QA, production).

---

## Test Execution Flow

1. **Define Behavior in Feature Files:**
    - Test cases are written in Gherkin syntax within `.feature` files located in `src/test/resources/features/`. These feature files describe the application's expected behavior in a readable format.

   Example:
   ```gherkin
   Scenario: User navigates to login page
     Given we navigate to "qa_url"
     Then we should see the login form
   ```

2. **Step Definitions:**
    - Each Gherkin step in the feature file maps to a method in the `stepdefinitions/` folder. For example, the step "we navigate to 'qa_url'" is implemented in `CommonSteps.java` as follows:
      ```java
      @Given("we navigate to {string} url")
      public void weNavigateToURL(String url) {
          page.navigate(ConfigurationProperties.getBaseUrl(url));
      }
      ```

3. **Run the Tests:**
    - The tests can be executed using either TestNG or JUnit, depending on the runner specified in the `runners/` folder.
    - Use Maven to run tests:
      ```bash
      mvn clean test
      ```

4. **View Reports:**
    - After the test execution completes, detailed HTML reports are generated in the `target/` directory. ExtentReports provides interactive reports with execution details, screenshots of failed tests, and a timeline of the test execution.

---

## Environment-Specific Configuration

The framework supports environment-based configurations through YAML files. This enables easy switching between different environments (e.g., development, QA, production).

Example configuration in

`config.yml`:
```yaml
rnd_url: "https://pdscawebdev01.myfnb.us/Account/Login?ReturnUrl=%2F"
qa_url: "https://qa.fnbonline.fnb-online.com/documentcenter/Account/Login#"
```

- **Switching Environments:**
    - To change the environment for the tests (e.g., from QA to production), simply modify the feature files or the test runner to use the appropriate URL key from the configuration file. This avoids hardcoding URLs in the tests and ensures that the same test suite can be executed across different environments.

---

## Reporting

### ExtentReports
The framework integrates with **ExtentReports**, which generates rich, interactive HTML reports after each test execution. The reports include:
- **Pass/Fail Summary**: A breakdown of which tests passed, failed, or were skipped.
- **Detailed Steps**: Every step in each scenario is logged, showing exactly what was executed.
- **Screenshots**: If a test fails, a screenshot is automatically captured and embedded in the report.
- **Parallel Execution Timeline**: If tests are executed in parallel, the report will display a visual timeline of the execution.

Reports are stored in:
- **HTML Report**: `target/cucumber-reports.html`
- **Extent Report**: `target/extent-reports/`
- **Screenshots**: `target/screenshots/`

### Example of a Report Entry:
```plaintext
Test Case: User Login
Status: Failed
Step: User enters "wrong_password" in the password field
Screenshot: [embedded]
```

---

## How to Run Tests

### Prerequisites
- **Java**: Install [Java 11+](https://adoptopenjdk.net/).
- **Maven**: Install [Maven](https://maven.apache.org/install.html).
- **Playwright**: Install Playwright dependencies by running:
  ```bash
  mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install-deps"
  ```

### Running All Tests
To execute all Cucumber scenarios in parallel, run the following command:
```bash
mvn clean test
```

### Running Specific Tags
To run tests tagged with a specific keyword (e.g., `@regression`), use the following Maven command:
```bash
mvn test -Dcucumber.options="--tags @regression"
```

### Viewing Reports
After the test execution is complete, open the HTML report located at `target/cucumber-reports.html` in a browser to view the results.

---

## Adding New Features and Tests

### 1. Adding a New Feature
1. Create a new `.feature` file under `src/test/resources/features/`.
2. Define scenarios in Gherkin syntax that describe the new feature's behavior.

### 2. Adding New Step Definitions
1. If the steps in the feature file are not already defined, add corresponding methods in `src/test/java/com/ptaf/stepdefinitions/`.

### 3. Adding Locators for New Elements
1. Add new element locators to `src/test/resources/elements/elements.yml`.
2. Update step definitions to use the new locators.

---

## Conclusion

The **FNB PTAF (Playwright Test Automation Framework)** is designed to be flexible, scalable, and easy to maintain. With its environment-based configuration, parallel execution, detailed reporting, and reusable utilities, it significantly improves the speed and reliability of testing web applications. Whether you're testing locally or in a CI/CD pipeline, this framework provides the tools and structure needed to ensure your application is thoroughly tested across multiple environments and browsers.

Feel free to contribute to the project or raise issues as you continue to expand the test suite!