package com.ui.automation.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.ui.automation.context.ScenarioContext;
import com.ui.automation.utils.TestDataReader;
import com.ui.automation.DriverManager.AppiumDriverManager;
import com.ui.automation.Pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Locale;

public class ScreenSteps {

    private final ScenarioContext context;
    public ScreenSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("user is in {string} screen")
    public void userIsInScreen(String screenName, DataTable table) throws InterruptedException {
        LoginPage login = new LoginPage(AppiumDriverManager.getDriver());
        // The screen name selects testdata/<screen>.json; the table selects one record.
        JsonNode data = TestDataReader.load(screenName, table.asList(String.class));
        context.setTestData(data);
        switch (screenName.trim().toLowerCase(Locale.ROOT)) {
            case "login":
                login.enterUsername(context.getValue("username"));
                login.enterPassword(context.getValue("password"));
                login.enterDateOfBirth(context.getValue("date"));
                break;
            case "home":
                login.enterUsername(context.getValue("username"));
                login.enterPassword(context.getValue("password"));
                login.enterDateOfBirth(context.getValue("date"));
                login.clickSignIn();
                break;
            default:
                throw new IllegalArgumentException("No screen action implemented for: " + screenName);
        }
    }
}
