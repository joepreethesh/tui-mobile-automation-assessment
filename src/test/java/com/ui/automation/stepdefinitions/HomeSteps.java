package com.ui.automation.stepdefinitions;

import com.ui.automation.DriverManager.AppiumDriverManager;
import com.ui.automation.Pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class HomeSteps {
    HomePage homePage = new HomePage(AppiumDriverManager.getDriver());

    @Then("user verifies content is seen under the following tabs")
    public void verifyHomeTabs(DataTable dataTable) {
        List<String> tabs = dataTable.asList(String.class);
        for (String tab : tabs) {
            homePage.selectTab(tab);
            Assert.assertTrue(homePage.isContentDisplayed(), "Content is not displayed under: " + tab);
            System.out.println("Validated tab: " + tab);
        }
    }

    @When("user sends the application to background for {int} seconds")
    public void userBackgroundsApplication(int seconds) {
        homePage.suspendAndResumeApp(seconds);
    }

    @Then("user sees Home screen")
    public void userSeesHomeScreen() {
        Assert.assertTrue(homePage.isHomeScreenDisplayed(), "Home screen is not displayed");
    }
}
