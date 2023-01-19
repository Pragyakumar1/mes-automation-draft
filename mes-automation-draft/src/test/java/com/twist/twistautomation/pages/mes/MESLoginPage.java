package com.twist.twistautomation.pages.mes;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class MESLoginPage extends BasePage {

    @Autowired
    MESHomePage homePage;

    public MESLoginPage() {
        Log.info("Landed to MES Login Page");
    }

    private By inputUserName = By.cssSelector("input[autocomplete='username']");
    private By inputPassword = By.cssSelector("input[autocomplete='password']");
    private By btnLogin = By.xpath("//button[text()='Login']");

    public MESHomePage doLogin(String username, String password) throws Exception {
        driver.pauseExecutionFor(4000);
        if(driver.IsLocatorVisible(inputUserName)) {
            driver.sendKeys(inputUserName, username);
            driver.sendKeys(inputPassword, password);
            driver.clickLocator(btnLogin);
        }
        return homePage;

    }

}
