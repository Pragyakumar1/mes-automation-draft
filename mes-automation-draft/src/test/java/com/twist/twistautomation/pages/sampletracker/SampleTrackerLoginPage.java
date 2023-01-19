package com.twist.twistautomation.pages.sampletracker;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class SampleTrackerLoginPage extends BasePage {

    @Autowired
    SampleTrackerHomePage homePage;

    public SampleTrackerLoginPage() {
        Log.info("Landed on sample tracker login page");
    }

    private By userNameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");

    public SampleTrackerHomePage doLogin(String userBarCode) throws Exception {
        driver.sendKeys(userNameField, userBarCode);
        return homePage;
    }

    public SampleTrackerHomePage doLogin(String username, String password) throws Exception {
        driver.pauseExecutionFor(2000);
        if(driver.IsLocatorVisible(userNameField)) {
            driver.sendKeys(userNameField, username);
            driver.sendKeys(passwordField, password);
            driver.clickLocator(loginButton);
        }
        return homePage;
    }
}
