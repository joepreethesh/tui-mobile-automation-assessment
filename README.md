# TUI Mobile Automation Assessment

## 1. About the Project

This project is created to automate the TUI Challenge Android application using Appium, Java, 
Cucumber BDD, TestNG and Maven. The framework follows the Page Object Model (POM) design. 
Test data is maintained in separate JSON files so that it can be updated without changing the test scripts.
The project contains 5 automated test scenarios covering Login and Home screen functionality.

## 2. Tools
- Java 21
- Appium with UiAutomator2
- Cucumber BDD
- TestNG
- Maven
- JSON for test data
- Android Emulator / Real Android Device

## 3. Prerequisites
Before running the tests, make sure the following are installed:
1. Java JDK 21
2. Maven
3. Node.js and Appium
4. Android Studio and Android SDK
5. Appium UiAutomator2 driver
6. Android emulator or real Android device

Check the installations using the commands below:

java -version
mvn -version
node -v
appium -v
adb version

Install the Appium Android driver if it is not already installed:
appium driver install uiautomator2

For a real Android device, enable Developer Options and USB Debugging.

## 4. Clone the Project

Open the terminal and run:
git clone https://github.com/joepreethesh/tui-mobile-automation-assessment.git

Go to the project folder:
cd tui-mobile-automation-assessment
Make sure the `TUIChallengeApp.apk` file is available in the `apps` folder.

## 5. Project Configuration
Open the following file:
`src/test/resources/config/config.properties`

The main configuration values are:
execution.target=local
platform.name=Android
device.name=AndroidDevice
device.udid=
platform.version=
automation.name=UiAutomator2
app.path=apps/TUIChallengeApp.apk
app.noReset=false
app.fullReset=false
appium.server.url=http://127.0.0.1:4723
explicit.wait=20
new.command.timeout=120

The UDID can be left empty when only one Android device is connected.
If multiple devices are connected, specify the device UDID in the configuration or through the Maven 
command, provided the latest driver configuration supports command-line overrides.

## 6. Start Appium Server
Open a separate terminal and start Appium:
appium

The server should be running at:
`http://127.0.0.1:4723`

Keep the Appium server running during test execution.

## 7. Connect an Android Device

Start an Android emulator from Android Studio or connect a real Android device using USB.
Check the connected devices:
adb devices

Example output:
```text
List of devices attached
emulator-5554    device
```

## 8. Run the Tests
Open a terminal in the project folder.
To execute all scenarios:
mvn clean test

To execute on a specific device:
mvn clean test "-Dudid=DEVICE_ID"
Replace `DEVICE_ID` with the actual device UDID.

## 9. Run Tests Using Cucumber Tags
Cucumber tags are used to execute selected scenarios.
Run only smoke tests:
mvn clean test "-Dcucumber.filter.tags=@smoke"

Run only Login tests:
mvn clean test "-Dcucumber.filter.tags=@login"

## 10. Automated Test Scenarios
The framework contains the following 5 scenarios:
1. Verify successful login with valid credentials.
2. Verify validation messages when submitting empty login fields.
3. Verify UI elements displayed on the Login screen.
4. Verify content displayed under All, Hotels and Holidays tabs.
5. Verify the Home screen after suspending and resuming the application.
These scenarios cover the main application flow, field validation, screen elements, 
6. tab navigation and application background behaviour.

## 11. Framework Structure
The project is divided into the following main components:
- **DriverManager:** Initializes and closes the Appium driver.
- **Pages:** Contains page actions and common methods.
- **Objects:** Contains element locators.
- **Step Definitions:** Connects Cucumber steps with page methods.
- **Hooks:** Handles driver setup, cleanup and failure screenshots.
- **Features:** Contains the test scenarios written in Gherkin.
- **Test Data:** Contains JSON files used during execution.
- **Config:** Contains application and device settings.
- **TestRunner:** Executes the Cucumber scenarios using TestNG.

The driver is managed using `ThreadLocal` to maintain separate driver instances for different 
execution threads.

## 12. Test Reports
After executing the tests, the Cucumber HTML report is generated at:
`target/cucumber-reports/cucumber.html`
The JSON report is available at:
`target/cucumber-reports/cucumber.json`
Maven/TestNG execution results are available in:
`target/surefire-reports/`
The HTML report can be opened in a web browser.

Cucumber report publishing is also enabled. A temporary online report link is displayed 
in the terminal after execution.
Screenshots are attached to the Cucumber report when a scenario fails

## 13. Test Data
Test data is maintained separately from the automation code.
The main files are:
- `src/test/resources/testdata/login.json`
- `src/test/resources/testdata/home.json`
- `src/test/resources/criteria/criteria.json`
The test data is selected based on the criteria provided in the Cucumber feature files.
This helps in reusing the same test steps with different input values.

## 16. Current Scope
The current implementation supports Android automation using Appium UiAutomator2.
The framework has configuration options for device selection and local execution. 
Cloud execution and iOS support can be added separately.
