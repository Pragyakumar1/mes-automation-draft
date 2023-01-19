package com.twist.twistautomation.pages.base;

import com.twist.twistautomation.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

public class BasePage {

    @Autowired
    protected WebDriverUtil driver;

    protected By btnFooter = By.id("app-footer-next-button");
    protected By header = By.className("heading2");

    protected void clickFooterButton() throws Exception {
        driver.clickLocator(btnFooter);
    }
}
