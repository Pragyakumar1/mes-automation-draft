package com.twist.twistautomation.pages.eCommerce;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class PaymentPage extends BasePage {

    @Autowired
    ConfirmOrderPage confirmOrderPage;
    public PaymentPage() {
        Log.info("Landed to Payment Page");
    }

    private By radioBtnVisa = By.xpath("//span[text()='visa']");

    public ConfirmOrderPage selectVisaPayment () throws Exception {
        Log.info("Selecting VISA as payment option");
        driver.clickLocator(radioBtnVisa);
        driver.clickLocator(btnFooter);
        return confirmOrderPage;
    }

}
