package com.twist.twistautomation.components.sampletracker.CLO;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CLO extends SampleTrackerComponent {
    public By cloDropdown = By.id("single-button8");
    public By btnCloTransformation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-CLO dropdown open']//a[@class='ng-binding'][normalize-space()='CLO Transformation']");
    public By btnHamiltonWizard = By.xpath("//button[contains(text(),'Start Hamilton Wizard')]");
    public By inputInstrumentBarcode = By.xpath("//input[@placeholder='Scan barcode']");
    public By btnSourceScanComplete = By.xpath("//button[contains(text(),'Source Scan Complete')]");
    public By listOfpCLOxBarcode = By.xpath("//h5[contains(text(),'pCLOx')]");
    public By labelTransformWidgets = By.xpath("//h4[contains(text(),'Transformation Widget')]");
    public ByT pCLOxBarcode = ByT.xpath("//h4[contains(text(),'Transformation Widget')]//following::h5['%s']");
    public By labelMediaPartner = By.xpath("//h4[contains(text(),'Media Parameters')]");
    public By labelForPosition = By.xpath("//strong[contains(text(),'Place this plate in position')]");
    public By btnDestinationPlacementComplete = By.xpath("//button[contains(text(),'Destination Placement Complete')]");
    public By btnRunFinished = By.xpath("//button[contains(text(),'Run Finished')]");
    public By btnDone = By.xpath("//button[contains(text(),'Done')]");
    public By btnSubmit = By.xpath("//button[contains(text(),'Submit')]");
    //public ByT checkBoxpCLOx = ByT.xpath("//*[contains(text(),'%s')]//div[@class='twst-custom-radio twst-custom-radio-as-checkbox twst-selected-false']");
    public ByT checkBoxpCLOx = ByT.xpath("//*[contains(text(),'%s')]//div[@class='twst-custom-radio twst-selected- twst-custom-radio-as-checkbox']");
    public By txtpCLOx = By.xpath("//*[contains(text(),'pCLOx1101B449238I9')]");
    public By btnCLOPlatingPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-CLO dropdown open']//a[@class='ng-binding'][normalize-space()='CLO Plating Planning']");
    public By loadingText = By.xpath("//p[contains(text(),'Loading During-Step Work Record for')]");
    // public ByT selectBarcode = ByT.xpath("//tr//td[normalize-space()='%s']");
    // public ByT selectBarcode = ByT.xpath("//td[contains(text(),'%s')]");
    public ByT selectBarcode = ByT.xpath("//*[contains(text(),'pCLOx1101B449232I9')]//div[@class='twst-custom-radio twst-selected- twst-custom-radio-as-checkbox']//preceding::td[1]//preceding::td[1]");
    public By btnViewSavedSpec = By.xpath("//button[contains(text(),'View Saved Spec')]");
    public By btnPlateNow = By.xpath("//button[contains(text(),'Plate Now')]");
    public By btnSkipPlate = By.xpath("//button[contains(text(),'Skip Plate')]");
    public By pCLOqBarCodes = By.xpath("//table[@class='twst-data-readout-table twst-smaller ng-scope']//td[contains(normalize-space(),'pCLOq')]");

    public CLO CLOTransformation() {
        try {
            selectMenu(cloDropdown, btnCloTransformation);
            driver.clickLocator(btnHamiltonWizard);
            //selectHamiltonWizard();
            driver.sendKeys(inputInstrumentBarcode, IHAM04);
            driver.pauseExecutionFor(4000);
            scanCarriers(C384_E0, 2);
            scanPosition(C384_E0, "1", "1");
            driver.pauseExecutionFor(3000);

            for (String barcode :
                    testContext.getPlates().getPPLSiPlateList()) {
                driver.copyTextToClipboard(barcode);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.scrollTillElement(cloDropdown);
            driver.pauseExecutionFor(4000);
            driver.clickLocator(btnSourceScanComplete);
            driver.pauseExecutionFor(2000);
            driver.scrollTillElement(labelTransformWidgets);
            ArrayList<String> updateCLOxBarcodeList = new ArrayList<>();
            List<WebElement> pCLOxBarcodeList = driver.findElements(By.xpath("//h5[contains(text(),'pCLOx')]"));
            int sizeOfpCLOBarcodeList = pCLOxBarcodeList.size();

            for (WebElement element : pCLOxBarcodeList) {
                updateCLOxBarcodeList.add(driver.getText(element));
            }
            Log.info("List of pCLOx barcode is: " + updateCLOxBarcodeList);
            testContext.getPlates().setPCLOxBarcodeList(updateCLOxBarcodeList);


            for (int i = 1; i <= updateCLOxBarcodeList.size(); ++i) {

                driver.copyTextToClipboard(updateCLOxBarcodeList.get(i - 1));
                driver.pauseExecutionFor(3000);
                driver.paste();
                driver.pauseExecutionFor(3000);
                String ss = String.valueOf(i);
                driver.scrollTillElement(cloDropdown);
                String positionLabel = driver.getText(labelForPosition);
                String position = positionLabel.replaceAll("\\D", "");
                scanPosition(C384_E0, "2", position);
                driver.pauseExecutionFor(3000);
            }

            driver.clickLocator(btnDestinationPlacementComplete);
            driver.pauseExecutionFor(2000);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
            driver.clickLocator(btnDone);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("CLO Transformation Failed due to ");
        }
        return this;
    }

    public CLO CLOPlatingPlanning() throws Exception {

        try {
            ArrayList arrayList = new ArrayList();


            selectMenu(cloDropdown, btnCLOPlatingPlanning);
            driver.waitForLocatorToBeDeleted(loadingText, 180);
            driver.pauseExecutionFor(2000);
            for (int i = 0; i < testContext.getPlates().getPCLOxBarcodeList().size(); ++i) {
                if (i > 1) {
                    break;
                } else {
                    driver.pauseExecutionFor(3000);
                    // driver.zoomOut(10);
                    driver.scrollTillElement(selectBarcode.format(testContext.getPlates().getPCLOxBarcodeList().get(i)));
                    driver.pauseExecutionFor(3000);
                    driver.clickLocator(checkBoxpCLOx.format(testContext.getPlates().getPCLOxBarcodeList().get(i)));
                }
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("CLO Plating Planning Failed due to ");
        }

        return this;
    }


    public CLO CLOPlatingonHamilton() {
        try {
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnViewSavedSpec);
            driver.clickLocator(btnPlateNow);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnHamiltonWizard);
            driver.sendKeys(inputInstrumentBarcode, IHAM04);
            scanCarriers(C384_E0, 2);
            //scanPosition(C384_E0, "1", "1");
            driver.pauseExecutionFor(3000);
            int listSize = testContext.getPlates().getPCLOxBarcodeList().size();
            for (int i = 0; i < listSize; ++i) {
                String position = String.valueOf(i);
                scanPosition(C384_E0, "1", position);
                driver.copyTextToClipboard(testContext.getPlates().getPCLOxBarcodeList().get(i));
                driver.pauseExecutionFor(3000);
                driver.paste();
                if (listSize == 1) {
                    driver.clickLocator(btnSkipPlate);
                }
            }
            driver.clickLocator(btnSourceScanComplete);
            driver.waitForLocatorToBeVisible(pCLOqBarCodes);
            List<WebElement> elements = driver.findElements(pCLOqBarCodes);
            for (WebElement element :
                    elements) {
                String pCLOqBarCode = driver.getText(element);
                driver.copyTextToClipboard(pCLOqBarCode);
                driver.pauseExecutionFor(2000);
                driver.paste();

                String positionLabel = driver.getText(labelForPosition);
                String position = positionLabel.replaceAll("\\D", "");
                scanPosition(C384_E0, "2", position);
                driver.pauseExecutionFor(3000);
                testContext.getPlates().getDestinationPlatepCLOq().add(pCLOqBarCode);
            }
            driver.clickLocator(btnDestinationPlacementComplete);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnRunFinished);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("CLO Plating on Hamilton failed due to ");
        }

        return this;
    }

}