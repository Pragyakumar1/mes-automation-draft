package com.twist.twistautomation.components.mes;

import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.junit.Assert;

import java.util.Arrays;

@Lazy
@Component
public class SynthesisAdminComponent extends AbstractComponent {
    private By btnSynthesisAdmin = By.id("twist-app-button-synthesis-admin-app");
    private ByT request_id = ByT.xpath("//p[normalize-space()='%s']//parent::div[contains(text(),\"SRQ_\")]");
    private By spId = By.xpath("//strong[contains(text(),'Locked')]//following::strong[1]");
    private By srqId = By.xpath("//h5[contains(text(),'Synthesis Request ')]//following::strong[5]");
    private By synthesisAdminIcon = By.id("twist-app-button-synthesis-admin-app");
    private ByT selectTransporationLaneHumanId = ByT.xpath("//span[contains(text(),'%s')]");
    private ByT transporationLaneShipIcon = ByT.xpath("//span[contains(text(),'%s')]//following::button[3]");
    private By shipButton = By.xpath("//button[contains(text(),'Ship')]");
    private By doneLabel = By.xpath("//*[contains(text(),'Done')]");
    private ByT plateInDoneLane = ByT.xpath("//*[contains(text(),'Done')]//following::div[contains(text(),'%s')]");


    public SynthesisAdminComponent launchSynthesisAdmin() throws Exception {
        driver.clickLocator(btnSynthesisAdmin);
        driver.waitForLocatorToBeDeleted(spinner, 600);
        return this;
    }

    public SynthesisAdminComponent fetchSPIdAndSRQId() throws Exception {
        driver.waitForLocatorToBeVisible(request_id.format(testContext.getChip_plan_sp_id()));
        String req_id = Arrays.stream(driver.getText(request_id.format(testContext.getChip_plan_sp_id())).split("\n")).findFirst().get();
        testContext.setChip_plan_srq_id(req_id);
        driver.clickLocator(request_id.format(testContext.getChip_plan_sp_id()));
        driver.waitForLocatorToBeVisible(spId, 60);
        testContext.setSynthesis_sp_id(driver.getText(spId));
        testContext.setSynthesis_srq_id(driver.getText(srqId));
        // just setting a unique number since sp_id is already unique
        testContext.setSynthesis_chip_id(testContext.getChip_plan_sp_id().replace(IConstants.SP, IConstants.CP));

        Log.info("Request_id: " + testContext.getChip_plan_srq_id());
        Log.info("Synthesis_sp_id: " + testContext.getSynthesis_sp_id());
        Log.info("Synthesis_srq_id: " + testContext.getSynthesis_srq_id());
        return this;
    }

    public SynthesisAdminComponent movePlateFromTransporationToDone() throws Exception {
        driver.clickLocator(synthesisAdminIcon);
        driver.waitForLocatorToBeDeleted(spinner, 600);
        driver.moveToLocatorByJS(selectTransporationLaneHumanId.format(testContext.getHuman_id()));
        driver.scrollDownByJS(2000);
        driver.clickLocator(transporationLaneShipIcon.format(testContext.getHuman_id()));
        driver.clickLocator(shipButton);
        driver.pauseExecutionFor(10000);
        driver.refreshPage();

        return this;
    }

    public SynthesisAdminComponent verifyPlateHasModedInDoneLane() {
        String doneLaneHumanId = Arrays.stream(driver.getText(plateInDoneLane.format(testContext.getHuman_id())).split("\n")).findFirst().get();
        Assert.assertEquals("Content is mis-matching", doneLaneHumanId, testContext.getHuman_id());
        Log.info(testContext.getHuman_id() + " Plate has moved in Done Lane");
        return this;
    }
}
