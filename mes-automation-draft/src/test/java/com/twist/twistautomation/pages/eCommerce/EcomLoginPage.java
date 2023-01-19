package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class EcomLoginPage extends BasePage {


    @Autowired
    EcomHomePage homePage;

    public EcomLoginPage() {
        Log.info("Landed to Login Page");
    }

    private By inputLogin = By.id("user_email");
    private By inputPassword = By.id("user_password");
    private By btnLogIn = By.cssSelector("button.button-has-loader.base-button");

    public EcomHomePage doLogin(String username, String password) throws Exception {
        Log.info("Logging to eCommerce application with username : "+ username);
        driver.sendKeys(inputLogin,username);
        driver.sendKeys(inputPassword,password);
        driver.clickLocator(btnLogIn);
        return homePage;
    }





}
