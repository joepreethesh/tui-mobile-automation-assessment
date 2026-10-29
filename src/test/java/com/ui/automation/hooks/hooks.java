package com.ui.automation.hooks;

import com.ui.automation.DriverManager.AppiumDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class hooks {

    @Before
    public void setUp() {
        AppiumDriverManager.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && AppiumDriverManager.getDriver() != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) AppiumDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failure screenshot");
                } catch (Exception e) {
                    System.err.println("Screenshot failed: " + e.getMessage());
                }
            }
        } finally { AppiumDriverManager.quitDriver(); }
    }
}
