package com.twist.twistautomation.stepdef;

import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.pages.eCommerce.EcomLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;


public class ECommerceSteps extends BaseTest {

    @Autowired
    protected EcomLoginPage loginPage;


    //    @Before
    public void beforeCommerceTest() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getECommerceUrl());
    }


    @Given("place NGS kit order for existing user")
    public void testPlaceNGSOrder() throws Exception {
        beforeCommerceTest();
        loginPage.doLogin(propertyUtil.getECommerceUserName(), propertyUtil.getECommercePassword())
                .selectNGSProduct()
                .typeAProduct(IConstants.KITS_AND_REAGENTS)
                .generateQuote().doCheckout()
                .selectVisaPayment().confirmOrder();
    }
}
