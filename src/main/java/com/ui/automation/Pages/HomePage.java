package com.ui.automation.Pages;

import com.ui.automation.objects.HomeObjects;
import io.appium.java_client.AppiumDriver;

public class HomePage extends BasePage{

    private final HomeObjects homePageObjects;

    public HomePage(AppiumDriver driver) {
        super(driver);
        homePageObjects = new HomeObjects(driver);
    }

    public boolean isHomeScreenDisplayed() {
        return visible(homePageObjects.allTab);
    }

    public void selectTab(String tabName) {
        switch (tabName) {
            case "All":
                visible(homePageObjects.allTab);
                click(homePageObjects.allTab);
                break;
            case "Hotels":
                visible(homePageObjects.hotelsTab);
                click(homePageObjects.hotelsTab);
                break;
            case "Holidays":
                visible(homePageObjects.holidaysTab);
                click(homePageObjects.holidaysTab);
                break;
            default:
                throw new IllegalArgumentException("Invalid tab: " + tabName);
        }
    }

    public boolean isContentDisplayed() {
        return homePageObjects.contentCards.size() > 0;
    }

    public void suspendAndResumeApp(int seconds) {
        backgroundApp(seconds);
    }
}
