package com.twist.twistautomation.utils;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverUtil {

    @Autowired
    public WebDriver driver;

    public void launchBrowser() {

        System.out.println("Driver ----" + driver);
        try {
            driver.manage().window().maximize();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void closeBrowser() {
        driver.close();
    }

    public void quitBrowser() {
        driver.quit();
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }


}
