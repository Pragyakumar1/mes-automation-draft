package com.twist.twistautomation.stepdef;

import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.pages.mes.MESHomePage;
import com.twist.twistautomation.pages.mes.MESLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import com.twist.twistautomation.DO.TestContext;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class ForthSteps extends BaseTest {

    @Autowired
    private MESLoginPage loginPage;
    private MESHomePage homePage;
//    private String worId = "WOR_61152609616f44000688387c";

    @Autowired
    TestContext testContext;


    //  launches browser and navigate to MES
    public void beforeForthTest() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getMesUrl());
    }


    @Given("user test order packing and booking from Forth UI")
    public void testForth(List<Map<String, String>> list) {

        String wor_id = list.stream().findFirst().get().get(WOR_ID);
        if (!wor_id.equalsIgnoreCase(NA))
            testContext.getOrderDetail().setWor_id(wor_id);
        String sfdc_id = list.stream().findFirst().get().get(SFDC_ID);
        if (!sfdc_id.equalsIgnoreCase(NA))
            testContext.getOrderDetail().setSfdc_id(sfdc_id);

        // to launch browser and navigate to MES
        beforeForthTest();

        try {
            // Packing
            homePage = loginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());
            homePage.getForthComponent().launchCatalogPacking().startPackingForWor(testContext.getOrderDetail().getWor_id());

            // Booking
            homePage.getForthComponent()/*.launchCatalogBooking()*/.startBooking(testContext.getOrderDetail().getWor_id(), IConstants.TRACKING_NUMBER);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }

    }

}
