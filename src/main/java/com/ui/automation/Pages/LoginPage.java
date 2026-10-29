package com.ui.automation.Pages;

import com.ui.automation.objects.LoginObjects;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    private final LoginObjects objects;

    public LoginPage(AppiumDriver driver) {
        super(driver);
        objects = new LoginObjects(driver);
    }

    public boolean isLoginScreenDisplayed() {
        return visible(objects.usernameField);
    }

    public boolean isUsernameErrorDisplayed() {
        return visible(objects.userNameTextFieldError);
    }

    public boolean isPasswordErrorDisplayed() {
        return visible(objects.passwordTextFieldError);
    }

    public boolean isDateOfBirthErrorDisplayed() {
        return visible(objects.dateOfBirthTextFieldError);
    }

    public void enterUsername(String value) {
        visible(objects.usernameField);
        click(objects.usernameField);
        type(objects.usernameField, value);
    }

    public void enterPassword(String value) {
        visible(objects.passwordField);
        click(objects.passwordField);
        type(objects.passwordField, value);
    }

    public void clickCalendarIcon() {
        visible(objects.calendarIcon);
        click(objects.calendarIcon);
    }

    public void clickEditButton() {
        visible(objects.editButton);
        click(objects.editButton);
    }

    public void enterDate(String value) {
        visible(objects.dateTextField);
        click(objects.dateTextField);
        String date = value.replace("/", "");
        type(objects.enterDateInTheField, date);
        waitForSeconds(3);
    }

    public void clickConfirmButton() {
        visible(objects.confirmButton);
        wait.until(ExpectedConditions.elementToBeClickable(objects.confirmButton));
        click(objects.confirmButton);
    }

    public void enterDateOfBirth(String value) throws InterruptedException {
        clickCalendarIcon();
        clickEditButton();
        enterDate(value);
        clickConfirmButton();
    }

    public void clickSignIn() {
        click(objects.submitButton);
    }

    public boolean isElementDisplayed(String elementName) {
        WebElement element;
        switch (elementName.trim().toLowerCase()) {
            case "username text field":
                element = objects.usernameField;
                break;
            case "password text field":
                element = objects.passwordField;
                break;
            case "date of birth text field":
                element = objects.dateOfBirthField;
                break;
            case "eye button":
                element = objects.eyeButton;
                break;
            case "calendar icon":
                element = objects.calendarIcon;
                break;
            case "submit button":
                element = objects.submitButton;
                break;
            default:
                throw new IllegalArgumentException("Unknown UI element: " + elementName);
        }
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
