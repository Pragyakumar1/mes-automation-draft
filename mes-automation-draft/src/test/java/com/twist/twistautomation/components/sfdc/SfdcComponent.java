package com.twist.twistautomation.components.sfdc;

import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class SfdcComponent extends AbstractComponent {


    String mesWorId;
    int mesExternalIdLength;

    private By searchField = By.id("phSearchInput");
    private By searchButton = By.id("phSearchButton");
    private By existingOrder = By.cssSelector("th.dataCell>a");
    private By orderStatusLabel = By.xpath("//td[contains(text(),'Order Status')]");
    private By currentOrderStatus = By.xpath("//td[contains(text(),'Order Status')]//following::td[1]");
    private By statusDropDown = By.id("00N5A00000HQIxN");
    private By mesExternalId = By.xpath("//td[contains(text(),'MES External Id')]//following::td[1]");
    private By saveButton = By.xpath("//h2[contains(text(),'Order Detail')]//following::input[@value=' Save ']");
    private By pbError = By.xpath("//div[@class='pbError']");
    private By orderNumberLabel = By.xpath("//a[contains(text(),'Order Number')]");
    private By sfdcId = By.id("00N5A00000HQIxM_ileinner");
    private By btnCreateFollowUp = By.xpath("//h2[contains(text(),'Order Detail')]//following::input[10]");
    private By chkBoxRecordId = By.xpath("//span[contains(text(),'Please choose order items from the list')]//following::label[@for='lgt-dt-header-factory-id-10']");
    private By btnCreateFollowUpOrder = By.xpath("//button[contains(text(),'Create Follow Up Order')]");
    private By labelOnChildWidonw = By.xpath("//span[contains(text(),'Please choose order items from the list')]");
    private By linkGoToOrder = By.xpath("//a[contains(text(),'Go to order')]");
    private By baseOrderNumber = By.xpath("//span[contains(text(),'Base Order')]//following::a[1]");
    private By glycerolQuoteNumber = By.id("Name_ileinner");
    private By glycerolSfdcId = By.xpath("//td[contains(text(),'Order Id')]//following::div[1]");

    public SfdcComponent updateOrderStatus(String orderId) throws Exception {
        driver.pauseExecutionFor(8000);
        Log.info("Clicking search field");
        do {
            driver.pauseExecutionFor(8000);
            driver.clickLocator(searchField);
            Log.info("Enter Order Id in Search field");
            driver.sendKeys(searchField, orderId);
            Log.info("click on Search Button ");
            driver.clickLocator(searchButton);
        } while (!driver.IsLocatorVisible(orderNumberLabel));
        Log.info("Click on Order Id");
        driver.clickLocator(existingOrder);
        driver.clickLocator(orderStatusLabel);
        driver.clickLocator(currentOrderStatus);
        String currentStatus = driver.getText(currentOrderStatus);
        testContext.getOrderDetail().setSfdc_id(driver.getText(sfdcId));
        Log.info("********Current Status=========>" + currentStatus);
        if (currentStatus.equals(IConstants.ORDER_DETAILS_NOT_VERIFIED)) {
            driver.pauseExecutionFor(5000);
            driver.doubleClickLocatorByAction(currentOrderStatus);
            driver.selectTextFromOptions(statusDropDown, IConstants.READY_FOR_PRODUCTION);
            driver.clickLocator(saveButton);
            driver.pauseExecutionFor(4000);
            if (driver.IsLocatorVisible(pbError)) {
                driver.refreshPage();
                driver.doubleClickLocatorByAction(currentOrderStatus);
                driver.selectTextFromOptions(statusDropDown, IConstants.READY_FOR_PRODUCTION);
                driver.clickLocator(saveButton);
            }

        } else {
            Log.info(IConstants.READY_FOR_PRODUCTION);
        }

        return this;
    }

    public SfdcComponent verifyMesOrderIdIsVisible() throws InterruptedException {
        do {
            mesWorId = driver.getText(mesExternalId);
            mesExternalIdLength = mesWorId.length();
            driver.refreshPage();
            driver.pauseExecutionFor(5000);
            if (mesExternalIdLength == 28) {
                testContext.getOrderDetail().setWor_id(mesWorId);
                Log.info("***MES External Id********:=======>" + mesWorId);
                Log.info("********Length of MES External Id is*****+========>" + mesExternalIdLength);
            }

        } while (mesExternalIdLength != 28);

        return this;
    }

    public SfdcComponent searchOrderInSFDC(String sfdcId) throws Exception {
        driver.pauseExecutionFor(8000);
        driver.clickLocator(searchField);
        Log.info("Enter Order Id in Search field");
        driver.sendKeys(searchField, sfdcId);
        Log.info("click on Search Button ");
        driver.clickLocator(searchButton);
        driver.clickLocator(existingOrder);
        driver.clickLocator(btnCreateFollowUp);
        driver.pauseExecutionFor(10000);
        driver.switchToWindow("Aura");
        String textOnFollowUpOrderPage = driver.getText(labelOnChildWidonw);
        Log.info(textOnFollowUpOrderPage);
        driver.clickLocator(chkBoxRecordId);
        driver.clickLocator(btnCreateFollowUpOrder);
        driver.pauseExecutionFor(25000);
        driver.clickLocator(linkGoToOrder);
        String titleTextAfterGoToOrder = driver.getTitle();
        Log.info("Title for sfdc Page after go to Order:-->" + titleTextAfterGoToOrder);
        driver.switchToWindow(titleTextAfterGoToOrder);

        String glysfdcId = driver.getText(glycerolSfdcId);
        String glyQuoteNumber = driver.getText(glycerolQuoteNumber);
        String glyOrderNumber = driver.getText(baseOrderNumber);
        testContext.setGlycerol_sfdc_id(glysfdcId);
        testContext.setGlycerol_quote_number(glyQuoteNumber);
        testContext.setGlycerol_order_number(glyOrderNumber);

        String currentStatus = driver.getText(currentOrderStatus);

        if (currentStatus.equals(IConstants.ORDER_DETAILS_NOT_VERIFIED)) {
            driver.pauseExecutionFor(5000);
            driver.doubleClickLocatorByAction(currentOrderStatus);
            driver.selectTextFromOptions(statusDropDown, IConstants.READY_FOR_PRODUCTION);
            driver.clickLocator(saveButton);
            driver.pauseExecutionFor(4000);
            if (driver.IsLocatorVisible(pbError)) {
                driver.refreshPage();
                driver.doubleClickLocatorByAction(currentOrderStatus);
                driver.selectTextFromOptions(statusDropDown, IConstants.READY_FOR_PRODUCTION);
                driver.clickLocator(saveButton);
            }

        } else {
            Log.info(IConstants.READY_FOR_PRODUCTION);
        }
        Log.info("Glycerol sfdc Id is:---->" + glysfdcId);
        Log.info("Glycerol Quote Number is:---->" + glyQuoteNumber);
        Log.info("Glycerol Order Number is:---->" + glyOrderNumber);

        return this;
    }
}
