package com.twist.twistautomation.components.mes;

import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
public class ForthComponent extends AbstractComponent {

    private By btnForth = By.id("twist-app-button-forth-app");
    private By btnCatalog = By.xpath("//span[text()='Catalog']");
    private By btnCatalogBooking = By.xpath("//a[text()='catalog booking']");
    private By divPackingSpinner = By.xpath("//div[@class='twst-spinner-inner']");
    private By btnManualEntry = By.xpath("//button[text()='Use manual entry']");
    private By btnUnscan = By.xpath("//button[text()='Unscan']");
    private By inputBarCode = By.cssSelector("input[placeholder='Barcode']");
    private By lnkApply = By.xpath("//button[text()='Apply']");
    private By lstAllOrders = By.cssSelector("table.twst-collect-containers-table.table.table-striped.table-hover tbody tr > :nth-child(1)");
    private By btnContinue = By.xpath("//button[text()='Continue']");
    private By btnClose = By.xpath("//button[text()='Close']");
    private By btnSubmit = By.xpath("//button[text()='Submit']");
    private By checkBoxPackingSlip = By.id("packing-slip-inserted-check-box");
    private By inputWeight = By.xpath("//input[@type='number']");
    private By checkBoxShippingLabel = By.id("shipping-label-inserted-check-box");
    private By btnFinishPacking = By.xpath("//button[text()='Finish Packaging']");
    private By btnFinalize = By.xpath("//button[text()='Finalize']");
    private By btnTakeOverShipment = By.xpath("//button[@class='twst-modal-ok-button btn btn-primary']");
    private By headerOverlay = By.xpath("//h5[@class='modal-title']");
    private By inputTrackingNumber = By.cssSelector("[placeholder=\"enter a tracking number...\"]");
    private By bookingSuccessMessage = By.tagName("h2");

    public ForthComponent launchCatalogPacking() throws Exception {
        Log.info("Clicking Forth button");
        driver.clickLocator(btnForth);
        Log.info("Clicking Catalog button");
        driver.clickLocator(btnCatalog);
        Log.info("Waiting for Packing spinner to disappear");
        driver.waitForLocatorToBeDeleted(divPackingSpinner,180);
        Log.info("Packing spinner to disappeared");
        return this;
    }

    public ForthComponent startPackingForWor(String worId) throws Exception {
        String selector ="button[data-shipping-plan-id='%s'].btn-primary";
        String startSelector =String.format(selector, worId);
        Log.info("clicking custom selector "+ startSelector);
        driver.waitForLocatorToBeDeleted(divPackingSpinner,180);
        driver.pauseExecutionFor(6000);
        driver.clickLocator(By.cssSelector(startSelector));
        driver.pauseExecutionFor(15000);
        if(driver.IsLocatorVisible(headerOverlay)){
            String text = driver.getText(headerOverlay);
            driver.pauseExecutionFor(6000);
            if (text.contains("Mutliple")){
                driver.moveToLocator(btnContinue);
                driver.clickLocator(btnContinue);
            }else{
                driver.clickLocatorIfExists(btnTakeOverShipment);
            }
        }
        Log.info("Clicking Local Use Manual entry...");
        driver.pauseExecutionFor(20000);
        driver.clickLocator(btnManualEntry);
        driver.waitForLocatorToBeVisible(btnUnscan);
        List<WebElement> orderNumbers = driver.findElements(lstAllOrders);
        Log.info("Scanning all orders....");
        for(WebElement orderNumber : orderNumbers){
            String text = orderNumber.getText();
            driver.sendKeys(inputBarCode,text+ IConstants.BATCH_NUMBER_100984);
            driver.pauseExecutionFor(500);
            driver.clear(inputBarCode);
            driver.pauseExecutionFor(500);
        }
        Log.info("Scanning completed for all orders");
        Log.info("clicking continue");
        driver.clickLocator(btnContinue);
        Log.info("Typing weight ");
        driver.clickLocator(checkBoxPackingSlip);
        driver.sendSlowKeys(inputWeight,"0.5",10);
        driver.clickLocator(btnFinishPacking);
        driver.pauseExecutionFor(15000);
        if(driver.IsLocatorVisible(headerOverlay)){
            driver.clickLocator(btnClose);
        }

        return this;
    }


    /*public ForthComponent launchCatalogBooking() throws Exception {
        Log.info("Clicking Forth button");
        driver.clickLocator(btnForth);
        Log.info("Clicking Catalog Booking");
        driver.clickLocator(btnCatalog);
        Log.info("Clicking Catalog booking");
        driver.clickLocator(btnCatalogBooking);
        return this;
    }*/

    public ForthComponent startBooking(String worId,String trackingNumber) throws Exception {
        driver.pauseExecutionFor(7000);
        driver.clickLocator(btnCatalogBooking);
        String selector ="//tr[td[text()='%s']]//button[text()='Start']";
        String bookingSelector = String.format(selector, worId);
        driver.clickLocator(By.xpath(bookingSelector));
        driver.clickLocator(btnContinue);
        driver.pauseExecutionFor(3000);
        driver.sendKeys(inputTrackingNumber,trackingNumber);
        driver.pauseExecutionFor(2000);
        driver.clickLocator(btnSubmit);
        driver.pauseExecutionFor(2000);
        driver.clickLocator(checkBoxShippingLabel);
        driver.clickLocator(btnContinue);
        driver.clickLocator(btnFinalize);
        driver.pauseExecutionFor(5000);
        driver.waitForLocatorToBeVisible(bookingSuccessMessage,90);
        return  this;
    }
}
