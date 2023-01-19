package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class QuotationPage extends BasePage {


    @Autowired
    PaymentPage paymentPage;

    public QuotationPage() {
        Log.info("Landed to Quotation Page");
    }

    private By btnCheckout = By.id("app-footer-next-button");
    private By headerQuote = By.xpath("//div[@class='toast info-toast']");
    private By addressRadioButton = By.xpath("//input[@name='Phone number-input']//following::div[@data-automation-id='address-viewer-organization'][1]");

    public QuotationPage generateQuote() throws Exception {
        Log.info("Waiting and clicking Generate Quote button");
        driver.waitForLocatorToBeVisible(header);
        driver.clickLocator(addressRadioButton);
        driver.clickLocator(btnFooter);
        return this;
    }

    public PaymentPage doCheckout() throws Exception {
        Log.info("Clicking checkout button");
        driver.waitForLocatorToBeVisible(headerQuote, 325);
        driver.clickLocator(btnCheckout);
        return paymentPage;
    }
}
