package com.twist.twistautomation.components.sampletracker.base;

import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Lazy
@Component
public class SampleTrackerComponent extends AbstractComponent {

    private By containerBarCodeField = By.xpath("//input[@ng-change='inputChanged(plate)']");
    private By scanInstrumentIdField = By.xpath("//*[contains(text(),'Scan Instrument ID')]//following::input[1]");
    private By responseField = By.xpath("//*[contains(text(),'Response:')]//following::input[1]");
    private By chpDropdown = By.id("single-button1");
    private By chpDeprotection = By.linkText("CHP Deprotection");
    public By during_frame = By.xpath("//iframe[@name='during-questionnaire-iframe']");
    public By before_frame = By.xpath("//iframe[@name='before-questionnaire-iframe']");
    public By after_frame = By.xpath("//iframe[@name='after-questionnaire-iframe']");
    private By errorObervationLabel = By.xpath("//*[contains(text(),'Error Observation:')]");
    private By userName = By.xpath("//input[@placeholder='scan your barcode or enter your username...']");
    private By password = By.xpath("//input[@placeholder='Enter your password']");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");
    public By loadingText = By.xpath("//p[contains(text(),'Loading During-Step Work Record for')]");
    public By loadingBeforeText = By.xpath("//p[contains(text(),'Loading Before')]");
    public By loadingAfterText = By.xpath("//p[contains(text(),'Loading After')]");
    public By btnSubmit = By.xpath("//button[@class='twst-button twst-blue-button' and normalize-space()='Submit']");
    public By stepFinishedText = By.xpath("//span[normalize-space()='Step Finished']");
    public ByT inputPlateBarcodes = ByT.xpath("(//input[@class='form-control ng-pristine ng-untouched ng-valid ng-empty'])[%s]");
    public By inputPlateBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-empty']");
    public By destinationBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");
    public By addPlate = By.xpath("//button[text()='+']");
    public By btnSaveBeforeStep = By.xpath("//button[text()='Save before-Step Responses']");
    public By btnSaveAfterStep = By.xpath("//button[text()='Save After-Step Responses']");
    public By scanInstrumentId = By.xpath("//*[contains(text(),'Instrument')]//following::input[1]");
    public By checkBx_EnsureAll = By.xpath("//span[@class='twst-checkbox-unchecked-icon']");
    public ByT selectWizard = ByT.xpath("//span[normalize-space()='%s']");
    public By startWizard = By.xpath("//button[normalize-space()='Start Hamilton Wizard']");
    public By inputInstrumentBarcode = By.xpath("//input[@placeholder='Scan barcode']");
    public By btnSkipCarrierScan = By.xpath("//button[contains(normalize-space(),'Skip Carrier')]");
    public By btnSkipPlate = By.xpath("//button[contains(normalize-space(),'Skip Plate')]");
    public By btnSourceScanComplete = By.xpath("//button[contains(normalize-space(),'Source Scan Complete')]");
    public By btnDestinationScanComplete = By.xpath("//button[contains(normalize-space(),'Destination Scan Complete')]");
    public By btnDestinationPlacementComplete = By.xpath("//button[contains(normalize-space(),'Destination Placement Complete')]");
    public By btnRunFinished = By.xpath("//button[normalize-space()='Run Finished']");
    public By btnRecordAnotherStep = By.xpath("//button[text()='Record Another Step']");
    public By positionIndicator = By.xpath("//strong[@class='twst-hamilton-step-direction-main ng-binding']");
    public By btnStartCyBioWizard = By.xpath("//button[contains(text(),'Start Cybio Wizard')]");
    public By btnPlateScanComplete = By.xpath("//button[contains(text(),'Plate Scan Complete')]");
    private By instrumentIdField = By.xpath("//span[contains(text(),'Instrument #:')]//following::input[1]");

    public void selectMenu(By mainMenu, By subMenu) throws Exception {
        driver.waitForLocatorToBeVisible(mainMenu, 60);
        driver.clickLocatorByJS(mainMenu);
        driver.clickLocator(subMenu);
        driver.pauseExecutionFor(2000);
    }

    public void scrollAndClickSubmit() throws Exception {
        driver.pauseExecutionFor(2000);
        driver.waitForLocatorToBeVisible(btnSubmit);
        driver.scrollTillElement(btnSubmit);
        driver.clickLocator(btnSubmit);
        driver.waitForLocatorToBeVisible(stepFinishedText, 300);
        driver.pauseExecutionFor(5000);
        Log.info("Submitted successfully");
    }

    public void scanBarcode(By locator, String barcode) throws Exception {
        driver.waitForLocatorToBeVisible(locator);
        driver.pauseExecutionFor(3000);
        driver.sendKeys(locator, barcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info("Barcode: " + barcode + " scanned successfully");
    }

    public void scanBarcode(WebElement locator, String barcode) throws Exception {
        driver.waitForLocatorToBeVisible(locator);
        driver.pauseExecutionFor(2000);
        driver.sendKeys(locator, barcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info("Barcode: " + barcode + " scanned successfully");
    }

    public void operatorLoginAfterQuestionnaire() throws Exception {
        if (driver.IsLocatorPresent(after_frame)) {
            driver.switchToFrame(after_frame);
            driver.waitForLocatorToBeDeleted(loadingAfterText, 180);
            driver.clickLocator(btnSaveAfterStep);
            driver.switchToDefault();
        }

    }

    public void operatorLoginDuringQuestionnaire() throws Exception {
        if (driver.IsLocatorPresent(during_frame)) {
            driver.switchToFrame(during_frame);
            driver.waitForLocatorToBeDeleted(loadingText, 180);
            if (driver.IsLocatorPresent(userName)) {
                driver.sendSlowKeys(userName, propertyUtil.getSampleTrackerUserName(), 10);
                driver.sendSlowKeys(password, propertyUtil.getSampleTrackerPassword(), 10);
                driver.clickLocatorByJS(loginButton);
            }
            driver.pauseExecutionFor(2000);
            if (driver.IsLocatorPresent(checkBx_EnsureAll))
                driver.clickLocator(checkBx_EnsureAll);
            if (driver.IsLocatorPresent(instrumentIdField))
                driver.sendSlowKeys(instrumentIdField, INSTRUMENT_ID, 10);
            driver.switchToDefault();
        }
    }

    public void operatorLoginBeforeQuestionnaire() throws Exception {
        if (driver.IsLocatorPresent(before_frame)) {
            driver.switchToFrame(before_frame);
            driver.waitForLocatorToBeDeleted(loadingBeforeText, 180);
            if (driver.IsLocatorPresent(userName)) {
                driver.sendKeys(userName, propertyUtil.getSampleTrackerUserName());
                driver.sendKeys(password, propertyUtil.getSampleTrackerPassword());
                driver.clickLocatorByJS(loginButton);
                driver.pauseExecutionFor(3000);
            }
            if (driver.IsLocatorPresent(scanInstrumentId)) {
                List<WebElement> list = driver.findElements(scanInstrumentId);
                for (WebElement element :
                        list) {
                    driver.sendKeys(element, DEV);
                }
            }
            driver.pauseExecutionFor(1000);
            if (driver.IsLocatorPresent(btnSaveBeforeStep))
                driver.clickLocator(btnSaveBeforeStep);
            driver.switchToDefault();
            driver.pauseExecutionFor(5000);
            if (driver.IsLocatorPresent(during_frame)) {
                driver.switchToFrame(during_frame);
                driver.waitForLocatorToBeDeleted(loadingText, 180);
                driver.switchToDefault();
            }
            driver.pauseExecutionFor(1000);
        }
    }

    public void selectHamiltonWizard(String hamiltonBarcode) throws Exception {
        driver.pauseExecutionFor(2000);
        if (driver.IsLocatorVisible(selectWizard.format(HAMILTON)))
            driver.clickLocator(selectWizard.format(HAMILTON));
        driver.clickLocator(startWizard);
        driver.sendKeys(inputInstrumentBarcode, hamiltonBarcode);
        driver.pauseExecutionFor(2000);
    }

    public void selectCybioWizard(String barcode) throws Exception {
        driver.clickLocator(btnStartCyBioWizard);
        driver.sendKeys(inputInstrumentBarcode, barcode);
        driver.pauseExecutionFor(3000);
    }

    public void scanCarriers(String carrierBarcode, Integer numberOfCarriers) throws Exception {
        Log.info("Scanning the carrier barcode");
        for (int i = 1; i <= numberOfCarriers; i++) {
            driver.pauseExecutionFor(2000);
            driver.copyTextToClipboard(carrierBarcode + i);
            driver.pauseExecutionFor(1000);
            driver.paste();
            driver.pauseExecutionFor(5000);
        }
        if (numberOfCarriers == 2) {
            driver.pauseExecutionFor(2000);
            if (driver.IsLocatorVisible(btnSkipCarrierScan)) {
                driver.clickLocatorByJS(btnSkipCarrierScan);
                driver.clickLocatorByJS(btnSkipCarrierScan);
            }
        }
        if (driver.IsLocatorPresent(btnSkipCarrierScan)) {
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSkipCarrierScan);
        }

    }

    public void scanPosition(String carrierBarcode) throws Exception {
        driver.pauseExecutionFor(3000);
        String position = GenericUtil.extractInt(driver.getTextByJS(positionIndicator));
        List<String> list = Arrays.asList(position.split(SPACE));
        scanPosition(carrierBarcode, list.get(0), list.get(1));
        driver.pauseExecutionFor(2000);
    }

    public void scanPosition(String carrierBarcode, String carrier, String position) throws InterruptedException {
        driver.pauseExecutionFor(1000);
        driver.copyTextToClipboard(carrierBarcode + carrier + _0 + position);
        driver.pauseExecutionFor(3000);
        driver.paste();
        driver.pauseExecutionFor(4000);
    }

    public SampleTrackerComponent submitChpDeprotection(String barCode, String instrumentId, String response) throws Exception {
        driver.pauseExecutionFor(20000);
        Log.info("Waiting for spinning block deleted");
        driver.clickLocator(chpDropdown);
        driver.clickLocator(chpDeprotection);
        driver.pauseExecutionFor(7000);
        Log.info("Entering container bar code");
        driver.clickLocatorByJS(containerBarCodeField);
        driver.sendKeys(containerBarCodeField, barCode);
        driver.pauseExecutionFor(5000);
        if (driver.IsLocatorPresent(during_frame)) {
            driver.switchToFrame(during_frame);
            driver.waitForLocatorToBeDeleted(loadingText, 180);
            driver.sendSlowKeys(userName, propertyUtil.getSampleTrackerUserName(), 10);
            driver.sendSlowKeys(password, propertyUtil.getSampleTrackerPassword(), 10);
            driver.clickLocatorByJS(loginButton);
            driver.clickLocator(scanInstrumentIdField);
            Log.info("Entering instrument id");
            driver.pauseExecutionFor(9000);
            driver.sendSlowKeys(scanInstrumentIdField, instrumentId, 10);
            Log.info("Entering response");
            driver.moveToLocatorByJS(errorObervationLabel);
            driver.clickLocator(responseField);
            driver.sendSlowKeys(responseField, response, 10);
            Log.info("Switching to default window");
            driver.switchToDefault();
        }
        driver.moveToLocatorByJS(btnSubmit);
        driver.waitForLocatorToBeVisible(btnSubmit);
        driver.clickLocator(btnSubmit);
        driver.pauseExecutionFor(15000);
        return this;
    }
}


