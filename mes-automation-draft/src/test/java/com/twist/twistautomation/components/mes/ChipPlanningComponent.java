package com.twist.twistautomation.components.mes;

import com.google.common.util.concurrent.Uninterruptibles;
import com.twist.twistautomation.DO.OrderDetail;
import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Lazy
@Component
public class ChipPlanningComponent extends AbstractComponent {
    private By btnChipPlan = By.id("twist-app-button-chips-app");
    private By tableSynthesisPlan = By.xpath("//table[contains(@class,'synthesis')]//tbody");
    private By btnNewPlan = By.xpath("//button[normalize-space()=\"+ New Plan\"]");
    private By inputNewPlanDesc = By.xpath("//textarea[@placeholder=\"plan description\"]");
    private By btnCreatePlan = By.xpath("//button[contains(text(),\"✶ Create Plan\")]");
    private By lblChipPlan = By.xpath("//h1[@class='twst-synthesis-plan-title']//strong");
    private By toggleFIFO = By.xpath("//span[@class=\"twst-toggle-control-track-click-area-off\"]");
    private ByT selectWorId = ByT.xpath("//td[span[a[text()='%s']]]/preceding-sibling::td//span[@class='twst-checkbox-unchecked-icon']");
    private By btnAddSelectedItems = By.xpath("//button[contains(text(),'Add Selected Items')]");
    private By btnDequeue = By.cssSelector("button.twst-dequeue-items-button");
    private ByT selectAPlate = ByT.xpath("//div[@title='Plate %s']//span[@class='twst-checkbox-unchecked-hover']");
    private By btnClearSelected = By.xpath("//button[text()='clear selected']");
    private By btnSynthesisPlanLock = By.cssSelector(".btn.btn-primary.twst-synthesis-plan-lock-btn-v2");
    private By btnLockPlan = By.xpath("//button[normalize-space()='Lock Plan']");
    private By btnRefresh = By.xpath("//button[@title='refresh now']");
    private By refreshQueue = By.xpath("//button[normalize-space()='refresh queue']");
    private By btnSendToSynthesis = By.xpath("//button[normalize-space()='Send to Synthesis']");
    private By chipTypeList = By.xpath("//div[@class='twst-send-to-synthesis-chip-type btn-group']//button[@type='button'][normalize-space()='select...']");
    private By inputRecipe = By.xpath("//p[@class='twst-send-to-synthesis-recipe-controls']//input[@type='text']");
    private By writerList = By.xpath("//div[@class='twst-send-to-synthesis-writer btn-group']//button[@type='button'][normalize-space()='select...']");
    private By inputNotes = By.xpath("//div[@class='twst-send-to-synthesis-notes']//textarea");
    private By btnConfirmSendToSynthesis = By.xpath("//button[@class='twst-modal-ok-button btn btn-primary']");
    private ByT selectChipType = ByT.xpath("//button[@class='dropdown-item'][normalize-space()='%s']");
    private ByT selectWriter = ByT.xpath("//button[@class='dropdown-item'][normalize-space()='%s']");
    private By planIdlabel = By.xpath("//strong[contains(text(),\"SP_\")]");
    private By toggleStatus = By.xpath("//*[@class='twst-toggle-control-bead twst-toggle-control-bead-on']");
    private By btnGenesThumbsUp = By.xpath("//div[contains(@class,'twst-synthesis-plan-extraction-grid-plate-genes')]//child::*[@class='twst-thumb twst-success-thumb']");


    public ChipPlanningComponent launchChipPlan() throws Exception {
        driver.clickLocator(btnChipPlan);
        return this;
    }

    public ChipPlanningComponent createNewPlan(String planName) throws Exception {
        driver.waitForLocatorToBeVisible(btnNewPlan);
        driver.clickLocator(btnNewPlan);
        driver.sendKeys(inputNewPlanDesc, planName);
        driver.clickLocator(btnCreatePlan);
        return this;
    }

    public ChipPlanningComponent lockPlan() throws Exception {
        driver.clickLocator(btnSynthesisPlanLock);
        driver.clickLocator(btnLockPlan);
        driver.waitForLocatorToBeDeleted(spinner, 120);
        driver.waitForLocatorToBeVisible(btnRefresh);
        int counter = 0;
        while (!driver.IsLocatorVisible(btnSendToSynthesis)) {
            driver.pauseExecutionFor(5000);
            driver.clickLocator(btnRefresh);
            counter++;
            if (counter == 30)
                break;
        }
        driver.clickLocator(btnSendToSynthesis);
        return this;
    }

    public ChipPlanningComponent confirmSendToSynthesis(String chipType, String recipe, String writer, String notes) throws Exception {
        driver.clickLocator(chipTypeList);
        driver.clickLocator(selectChipType.format(chipType));
        driver.sendKeys(inputRecipe, recipe);
        driver.clickLocator(writerList);
        driver.clickLocator(selectWriter.format(writer));
        driver.sendKeys(inputNotes, notes);
        driver.clickLocator(btnConfirmSendToSynthesis);
        driver.waitForLocatorToBeDeleted(spinner, 120);
        Log.info("captured Chip_plan_sp_id:" + driver.getText(planIdlabel));
        testContext.setChip_plan_sp_id(driver.getText(planIdlabel));
        return this;
    }

    public String getChipPlanName() {
        return driver.getText(lblChipPlan);
    }

    public ChipPlanningComponent addWorIdToPlan(List<OrderDetail> orderDetailList) throws Exception {
        Log.info("Adding orders in Chip Plate");
        List<String> clonalOrders = new ArrayList<>();
        List<String> nonClonalOrders = new ArrayList<>();
        List<String> otherOrders = new ArrayList<>();
        for (OrderDetail orderDetail :
                orderDetailList) {
            if (orderDetail.getType().equalsIgnoreCase(CLONAL_GENE))
                clonalOrders.add(orderDetail.getWor_id());
            else if (orderDetail.getType().equalsIgnoreCase(NON_CLONAL_GENE))
                nonClonalOrders.add(orderDetail.getWor_id());
            else
                otherOrders.add(orderDetail.getWor_id());
        }
        for (OrderDetail orderDetail :
                orderDetailList) {
            driver.waitForLocatorToBeDeleted(spinner, 900);
            if (driver.IsLocatorVisible(toggleStatus)) {
                driver.clickLocator(toggleFIFO, 1200);
            }
            driver.pauseExecutionFor(7000);
            int counter = 0;
            while (!driver.IsLocatorVisible(selectWorId.format(orderDetail.getWor_id()))) {
                driver.pauseExecutionFor(5000);
                driver.clickLocator(refreshQueue);
                driver.waitForLocatorToBeDeleted(spinner, 900);
                counter++;
                if (counter == 3) {
                    Assert.fail("Wor_id: " + orderDetail.getWor_id() + " is not available in priority queue");
                    break;
                }
            }
        }

        for (int i = 0; i < otherOrders.size(); ++i) {
            driver.pauseExecutionFor(5000);
            driver.clickLocator(selectWorId.format(otherOrders.get(i)));
            driver.waitForLocatorToBeVisible(btnDequeue);
            driver.clickLocator(btnAddSelectedItems);
            driver.waitForLocatorToBeDeleted(spinner, 120);
        }

        for (int i = 0; i < clonalOrders.size(); ++i) {
            driver.pauseExecutionFor(5000);
            driver.clickLocator(selectWorId.format(clonalOrders.get(i)));
            driver.waitForLocatorToBeVisible(btnDequeue);
            driver.clickLocator(btnAddSelectedItems);
            driver.waitForLocatorToBeDeleted(spinner, 120);
        }
        if (clonalOrders.size() > 0)
            driver.clickLocators(btnGenesThumbsUp);
        for (int i = 0; i < nonClonalOrders.size(); ++i) {
            driver.pauseExecutionFor(5000);
            driver.clickLocator(selectWorId.format(nonClonalOrders.get(i)));
            driver.waitForLocatorToBeVisible(btnDequeue);
            driver.clickLocator(btnAddSelectedItems);
            driver.waitForLocatorToBeDeleted(spinner, 120);
        }
        return this;
    }

    public ChipPlanningComponent removePlates(List<String> plateNumbers) throws Exception {
        plateNumbers.forEach(plateNumber -> {
            try {
                driver.clickLocator(selectAPlate.format(plateNumber));
                Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        driver.clickLocator(btnChipPlan);
        return this;
    }


}
