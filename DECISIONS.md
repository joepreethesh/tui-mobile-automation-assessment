# DECISIONS.md
## 1. Why  Chose This Project Structure
Java, Appium, Cucumber BDD, TestNG and Maven tools are commonly used for mobile automation and are 
easy to maintain.
Followed the Page Object Model (POM) design to keep the framework organized and avoid 
writing all the automation code in one place.

- DriverManager: Handles Appium driver initialization, device configuration and driver cleanup.
- BasePage: Contains common methods such as clicking, entering text and waiting for elements.
- LoginPage and HomePage: Contain actions and validations related to their respective screens.
- LoginObjects and HomeObjects: Store element locators separately from the test steps.
- Step Definitions: Connect the Cucumber feature steps with the page methods and contain test assertions.
- Hooks: Handle setup and cleanup before and after scenarios.
- TestRunner: Executes the Cucumber scenarios using TestNG.
- Test Data: Stores input values in JSON files.
- Configuration: Maintains device details, Appium server URL, APK path and other execution settings.

## 2. Which Parts Used AI Tools For and Where had to Correct the Output
Used ChatGPT as a supporting tool during framework development, mainly for understanding,
implementation and troubleshooting errors.
AI assistance was useful in the following areas:
- Reviewing reusable methods in BasePage.
- Understanding Appium driver configuration.
- Reading test data from JSON files.
Made changes based on the actual application behaviour and test execution results.
- Text entry issue
- Date of birth field
- Driver initialization

## 3. What Would Add Given More Time

The current framework covers the main functionality available in the application.
Given more time, would consider the following improvements.
- More test scenarios
- Parallel execution 
- Re-execution of failed test cases
- 
## 4. How to Run Tests in CI with Parallel Execution Across Android and iOS
Would use Jenkins to execute the automation tests through a parameterized pipeline.
-- Jenkins Parameters
Create the following parameters in Jenkins:
- PLATFORM ->  Android, iOS or Both
- DEVICE_UDID ->  To select the device for execution
- TEST_TAGS -> To execute all scenarios or selected Cucumber tags
- EXECUTION_TARGET ->  Local or Cloud

-- Platform Selection
Based on the selected platform, Jenkins would pass the required parameters to Maven.
Android:
`mvn clean test -Dplatform=Android`
iOS:
`mvn clean test -Dplatform=iOS`

## 5. Why I Selected These Five Test Scenarios
selected five scenarios that cover the main application flow, input validation, 
screen elements, navigation and application behaviour.
The selected scenarios provide coverage of:
- Successful login
- Input validation
- Login screen UI elements
- Home screen navigation and content
- Application suspend and resume behaviour
