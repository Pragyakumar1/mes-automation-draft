package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class NGSProductPage extends BasePage {

    @Autowired
    QuotationPage quotationPage;
    public NGSProductPage() {
        Log.info("Landed to NGS Product Page");
    }

    //private By inputFilterByType= By.xpath("//span[text()='Type']");
    private By inputFilterByType= By.xpath("//label[contains(text(),'Filter by')]//following::span[1]");
    private By inputProductName = By.xpath("//input[@class='dropdown-window dropdown-window-open']");
    private By chkProduct = By.xpath("//label[@class='checkbox-mock']");
    // private By ngsProduct = By.xpath("//input[@placeholder='Search Item']//following::button[1]");
    private By ngsProduct = By.xpath("//span[contains(text(),'100984')]//following::button[1]");
    private By btnRequestQuote = By.id("add-to-cart-btn");

    public QuotationPage typeAProduct(String productName) throws Exception {
        Log.info("Clicking Filter by type");
        driver.clickLocator(inputFilterByType);
        Log.info("typing product name : "+productName);
        driver.pauseExecutionFor(3000);
        driver.sendSlowKeys(inputProductName,productName,20);
        driver.pauseExecutionFor(10000);
        Log.info("Selecting desired NGS product : Amp Mix");
        driver.clickLocator(chkProduct);
        driver.clickLocator(By.tagName("h2"));
        driver.moveToLocator(ngsProduct);
        driver.clickLocator(ngsProduct);
        Log.info("Clicking Request Quote button");
        driver.clickLocator(btnRequestQuote,90);
        return quotationPage;
    }

}
