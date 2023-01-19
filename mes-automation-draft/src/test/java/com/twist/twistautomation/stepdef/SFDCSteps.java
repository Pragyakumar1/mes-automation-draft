package com.twist.twistautomation.stepdef;

import com.twist.twistautomation.pages.SFDC.SFDCHomePage;
import com.twist.twistautomation.pages.SFDC.SFDCLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import com.twist.twistautomation.DO.TestContext;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class SFDCSteps extends BaseTest {
    @Autowired
    private SFDCLoginPage loginPage;

    @Autowired
    private SFDCHomePage homePage;
    @Autowired
    TestContext testContext;

    //    @Before
    public void beforeSfdcTest() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getSfdcUrl());
    }

    @Then("user sync order to MES from SFDC")
    public void testSFDC() {
        beforeSfdcTest();
        // todo
        // need to find a solution to search with either quotation/sfdc in both API and UI flow

        try {
            homePage = loginPage.doLogin(propertyUtil.getSfdcUserName(), propertyUtil.getSfdcPassword());
            if (testContext.getOrderDetail().getSfdc_id() == null)
                homePage.sfdcComponent
                        .updateOrderStatus(testContext.getQuotation_id());
            else
                homePage.sfdcComponent
                        .updateOrderStatus(testContext.getOrderDetail().getSfdc_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("Operator login to SFDC")
    public void loginSFDC() {
        beforeSfdcTest();
        try{homePage = loginPage.doLogin(propertyUtil.getSfdcUserName(), propertyUtil.getSfdcPassword());}
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("user open orders on SFDC")
    public void userOpenOrdersOnSfdc(){
        beforeSfdcTest();
        try {
            homePage.sfdcComponent
                    .searchOrderInSFDC(testContext.getSfdcListItem().get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("operator has sfdcid")
    public void userHasExistingSfdcId(List<Map<String, String>> list) {
        String sfdcId = list.stream().findFirst().get().get("sfdcId");
        if (!sfdcId.equalsIgnoreCase(NA))
            testContext.setSfdcListItem(Arrays.asList(sfdcId.split(",")));
    }
}
