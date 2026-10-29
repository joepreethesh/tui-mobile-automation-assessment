package com.ui.automation.objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginObjects {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Username\")")
    public WebElement usernameField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
    public WebElement passwordField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Date of birth\")")
    public WebElement dateOfBirthField;

    @AndroidFindBy(accessibility =  "Calendar")
    public WebElement calendarIcon;

    @AndroidFindBy(accessibility = "Switch to text input mode")
    public WebElement editButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Date\")\n")
    public WebElement dateTextField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(10)")
    public WebElement enterDateInTheField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Confirm\")")
    public WebElement confirmButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Submit\")")
    public WebElement submitButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username_input_field_error\")")
    public WebElement userNameTextFieldError;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password_input_field_error\")")
    public WebElement passwordTextFieldError;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"date_of_birth_field_error\")")
    public WebElement dateOfBirthTextFieldError;

    @AndroidFindBy(accessibility = "Show password")
    public WebElement eyeButton;

    public LoginObjects(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
