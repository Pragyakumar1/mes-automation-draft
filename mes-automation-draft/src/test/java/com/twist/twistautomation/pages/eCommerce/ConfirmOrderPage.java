package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import com.twist.twistautomation.DO.TestContext;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class ConfirmOrderPage extends BasePage {

    @Autowired
    TestContext testContext;

    public ConfirmOrderPage() {
        Log.info("Landed to Payment Page");
    }

    private By btnConfirmOrder = By.xpath("//button[text()='Confirm Order']");
    private By labelThankYouOrder = By.id("checkout-thank-you-page-title");
    private By viewQuoteButton = By.xpath("//span[contains(text(),'View Quote')]");
    private By quoteId = By.xpath("//span[contains(text(),'QUOTE')]//following::span[1]");
    private By modalCloseButton = By.xpath("//div[@data-testid='modal-close-button']");

    public void confirmOrder() throws Exception {
        Log.info("Waiting and clicking Confirm Order button");
        driver.waitForLocatorToBeVisible(header, 30);
        driver.clickLocator(viewQuoteButton);
        String quoteIdText = driver.getText(quoteId);
        testContext.setQuotation_id(quoteIdText.substring(1, quoteIdText.length()));
        Log.info("******Quote Id is==========>" + quoteIdText);
        driver.clickLocator(modalCloseButton);
        driver.clickLocator(btnConfirmOrder);
        Log.info("Waiting for Thank You for your order label");
        driver.waitForLocatorToBeVisible(labelThankYouOrder);
        Log.info("Thank You for your order label appeared");
    }
}
