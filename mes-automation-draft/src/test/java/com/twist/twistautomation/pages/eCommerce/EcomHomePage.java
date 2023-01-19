package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class EcomHomePage extends BasePage {

    @Autowired
    NGSProductPage ngsProductPage;
    public EcomHomePage() {
        Log.info("Landed to Home Page");
    }

    private By lstAllProducts= By.xpath("//div[@class='twist-app-buttons-container']//label[@class='twist-app-button-card-front-label']");
    private By labelHeader = By.className("twist-app-container-header");
    private By labelAllProducts = By.className("css-op3zxg");
    private By btnStore = By.xpath("//button[text()='Store']");
    private By btnShopProducts = By.xpath("//h3[contains(text(),'Next generation sequencing')]//following::label[1]");

    public NGSProductPage selectNGSProduct() throws Exception {
        Log.info("Selecting NGS Product ");
        driver.waitForLocatorToBeVisible(labelHeader);
        driver.clickLocatorFromList(lstAllProducts,"NGS");
        if(driver.IsLocatorPresent(btnStore)){
            driver.clickLocator(btnStore);

        }
        driver.pauseExecutionFor(10000);
        if(driver.IsLocatorVisible(btnShopProducts)){
            driver.clickLocator(btnShopProducts);
        }
        else{
            Log.info("Shop Products button not available");
        }
        return ngsProductPage;
    }


}
