package com.twist.twistautomation.stepdef;

import com.twist.twistautomation.pages.mes.MESHomePage;
import com.twist.twistautomation.pages.mes.MESLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import com.twist.twistautomation.DO.TestContext;
import io.cucumber.java.en.And;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class ChipPlanningSteps extends BaseTest {

    @Autowired
    MESLoginPage loginPage;
    MESHomePage homePage;
    @Autowired
    TestContext testContext;

    public void navigateToMES() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getMesUrl());
    }

    @And("user create chip plan from UI")
    public void userCreateChipPlan() {
        Log.info("Creating Synthesis Chip Plan");
        navigateToMES();
        String uniqueNumber = GenericUtil.getUniqueAlphanumeric(5);
        String planName = "MES_Auto_" + uniqueNumber;

        testContext.setChip_type_id("CT_0040");
        testContext.setRecipe_name("531");
        testContext.setMachine_id("OZ4");
        try {
            homePage = loginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());
            homePage.
                    getChipPlanningComponent().
                    launchChipPlan().
                    createNewPlan(planName).
                    //addWorIdToPlan(testContext.getWor_id())
                    addWorIdToPlan(testContext.getOrderDetails())
                    .lockPlan()
                    .confirmSendToSynthesis(testContext.getChip_type_id(), testContext.getRecipe_name(), testContext.getMachine_id(), "AutomationTestPlan");
            System.out.println("Done");
        } catch (Exception e) {
            Assert.fail("Create chip plan failed due to: \n"+ e);
        }
    }

    @And("user navigate to synthesis admin for SRN details")
    public void userNavigateToSynthesisAdminForSRNDetails() {
        // enable this if you want to run only synthesis admin
//        testContext.setChip_plan_sp_id("SP_018358");
        Log.info("Navigate to synthesis admin UI for SRN details");
        try {
            if(ObjectUtils.allNull(homePage)){
                navigateToMES();
                homePage = loginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());
            }
            homePage.navigateToHomePage()
                    .getSynthesisAdminComponent()
                    .launchSynthesisAdmin()
                    .fetchSPIdAndSRQId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
