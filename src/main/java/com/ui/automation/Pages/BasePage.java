package com.ui.automation.Pages;

import com.ui.automation.DriverManager.AppiumDriverManager;
import com.ui.automation.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class BasePage {
    protected final WebDriverWait wait;

    protected BasePage(AppiumDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
    }

    protected void type(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) AppiumDriverManager.getDriver()).executeScript(
                "mobile: type", Map.of("text", value));
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected boolean visible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    protected void backgroundApp(int seconds) {
        AndroidDriver driver = (AndroidDriver) AppiumDriverManager.getDriver();
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    protected void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Wait interrupted", e);
        }
    }
}

