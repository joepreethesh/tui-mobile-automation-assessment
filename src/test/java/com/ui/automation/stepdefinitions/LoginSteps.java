package com.ui.automation.stepdefinitions;

import com.ui.automation.DriverManager.AppiumDriverManager;
import com.ui.automation.Pages.HomePage;
import com.ui.automation.Pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
    HomePage homePage = new HomePage(AppiumDriverManager.getDriver());

    @Given("the app is launched")
    public void appIsLaunched() {
        Assert.assertTrue(loginPage.isLoginScreenDisplayed(), "Login screen is not displayed");
    }

    @When("user clicks Submit button")
    public void userClickSubmitButton() {
        loginPage.clickSignIn();
    }

    @Then("user sees error messages are displayed")
    public void userSeesErrorMessageAreDisplayedForTextFields() {
        Assert.assertTrue(loginPage.isUsernameErrorDisplayed(), "Error message is not displayed for Username text field");
        Assert.assertTrue(loginPage.isPasswordErrorDisplayed(), "Error message is not displayed for Password text field");
        Assert.assertTrue(loginPage.isDateOfBirthErrorDisplayed(), "Error message is not displayed for Date Of Birth text field");
    }

    @Then("user verifies the following UI elements are displayed")
    public void verifyLoginUIElements(DataTable table) {
        List<String> elements = table.asList(String.class);
        for (String element : elements) {
            Assert.assertTrue(loginPage.isElementDisplayed(element), "UI element is not displayed: " + element);
            System.out.println("Verified UI element: " + element);
        }
    }
}
