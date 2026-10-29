package com.ui.automation.objects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomeObjects {

    @AndroidFindBy(accessibility = "All")
    public WebElement allTab;

    @AndroidFindBy(accessibility = "Hotels")
    public WebElement hotelsTab;

    @AndroidFindBy(accessibility = "Holidays")
    public WebElement holidaysTab;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"search_result_content_screen_root\")")
    public List<WebElement> contentCards;

    public HomeObjects(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
