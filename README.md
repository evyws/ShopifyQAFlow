# Test Automation Framework

## Overview
This project is a  test automation framework built with Java, Selenium WebDriver, and TestNG. It's designed for high-performance, parallel execution of tests across multiple environments and browsers.

## Key Features

- **Parallel Execution**: Leverages TestNG for multithreaded test execution, significantly reducing overall test runtime.
- **Cross-Browser Compatibility**: Runs tests concurrently on multiple browsers (Chrome, Firefox, Edge, Safari).
- **Thread-Safe WebDriver Management**: Utilizes ThreadLocal for managing WebDriver instances, ensuring thread safety in parallel executions.
- **Page Object Model (POM)**: Implements POM design pattern for improved maintainability and readability.
- **Data-Driven Testing**: Utilizes TestNG data providers for comprehensive test coverage.
- **Dynamic Configuration**: Employs the Owner library for flexible, environment-specific configurations.
- **Comprehensive Reporting**: Integrates Allure for detailed, interactive test reports.

## CI/CD Integration

### Jenkins Pipeline

This framework is designed to seamlessly integrate with Jenkins CI/CD pipelines, enabling automated test execution as part of your continuous integration process.

#### Jenkinsfile

The project includes a `Jenkinsfile` that defines the pipeline stages for the automation framework. Here's an overview of the pipeline stages:

1. **Checkout**: Retrieves the latest code from the repository.
2. **Build**: Compiles the project and installs dependencies.
3. **Test**: Executes the test suite in parallel across specified browsers and environments.
4. **Report**: Generates and publishes the Allure report.