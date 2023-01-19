package com.twist.twistautomation.pages.SFDC;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class SFDCLoginPage extends BasePage {

    @Autowired
    SFDCHomePage homePage;

    private By sfdcUserName= By.id("username");
    private By sfdcPassword=By.id("password");
    private By sfdcLoginButton=By.id("Login");
    public SFDCHomePage doLogin(String username, String password) throws Exception {
        driver.sendKeys(sfdcUserName,username);
        driver.sendKeys(sfdcPassword,password);
        driver.clickLocator(sfdcLoginButton);
        return homePage;
    }
}
