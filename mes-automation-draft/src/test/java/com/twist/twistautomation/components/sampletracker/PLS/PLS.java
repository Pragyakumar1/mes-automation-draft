package com.twist.twistautomation.components.sampletracker.PLS;

import com.twist.twistautomation.components.sampletracker.VIN.VIN;
import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.JsonUtils;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Lazy
@Component
public class PLS extends SampleTrackerComponent {

    public By plsDropdown = By.id("single-button7");
    public By btnPLSPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Planning']");
    public By btnPLSVectorHitpickingCreateSource = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Vector Hitpicking - Create Source']");
    public By btnPLSVectorPlateRetrieval = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Vector Plate Retrieval']");
    public By btnPLSVectorPlateBuild = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Vector Plate Build']");
    public By btnPLSHamiltonVectorSourcePlateCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Hamilton Vector Source Plate Creation']");
    public By btnPLSDrydownIn = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Drydown In']");
    public By btnPLSDrydownOut = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Drydown Out']");
    public By btnPLSVectorHitpicking = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Vector Hitpicking']");
    public By btnPLSMasterMixCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Master Mix Creation']");
    public By btnPLSMasterMixAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Master Mix Addition']");
    public By btnPLSThermocyling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Thermocyling']");
    public By btnPLSDilution = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PLS dropdown open']//a[@class='ng-binding'][normalize-space()='PLS Dilution']");

    public ByT selectBarcode = ByT.xpath("//tr//td[normalize-space()='%s']");
    public ByT chkBoxSelectBarcode = ByT.xpath("//tr//td[normalize-space()='%s']//parent::tr//following-sibling::tr[@ng-repeat='sampleGroup in plate']//td[@class='twst-pls-planning-plate-table-plate-data-cell']");
    public By btnViewSavedSpec = By.xpath("//button[text()='View Saved Spec #']");
    public By btnRebatchNow = By.xpath("//button[text()='Rebatch Now']");
    public By pPLSiBarcodes = By.xpath("//table[@class='twst-data-readout-table twst-smaller ng-scope']//td[contains(normalize-space(),'pPLSi')]");
    public By pPLSvBarcodes = By.xpath("//table[@class='twst-data-readout-table twst-smaller ng-scope']//td[contains(normalize-space(),'pPLSv')]");
    public By txtPlateBarcode = By.xpath("//tr[@class='twst-data-readout-table-row-selected-false']//td[1]");
    public By txtTubeBarcode = By.xpath("//table[@class='twst-data-readout-table twst-smaller ng-scope']//td[contains(normalize-space(),'LT')]");
    public By vectorTubeTable = By.xpath("//table[@class='twst-data-readout-table twst-smaller ng-scope']//tr");

    private By btnPLSVectorPlate = By.xpath("//button[text()='PLS Vector Plate Build']");
    private By activeTubes = By.xpath("(//div[@class='twst-tube-shuttle-tube twst-tube-shuttle-tube-active'])[1]");
    private By tubePositions = By.xpath("//div[contains(text(),'Destination Rack')]//following-sibling::div//div[@class='twst-tube-shuttle-tube']");
    private By tubePosition = By.xpath("(//div[contains(text(),'Destination Rack')]//following-sibling::div//div[@class='twst-tube-shuttle-tube'])[1]");
    private By destinationPLSv = By.xpath("//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pPLSv')]");
    private By destinationMXPs = By.xpath("//p[contains(text(), 'pMXPs')]");

    @Autowired
    VIN vin;

    public void PLSPlanning() {
        try {
            selectMenu(plsDropdown, btnPLSPlanning);
            driver.pauseExecutionFor(5000);
            driver.waitForLocatorToBeDeleted(spinnerSampleTracker, 600);
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRt()) {
                driver.scrollTillElement(selectBarcode.format(barcode));
                driver.pauseExecutionFor(3000);
                driver.clickLocators(chkBoxSelectBarcode.format(barcode));
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSPlanning Failed due to " + e);
        }
    }

    public void PLSNormalizationandReformatting() {
        try {
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnViewSavedSpec);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnRebatchNow);
            int count = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRt()) {
                scanBarcode(inputPlateBarcodes.format(count), barcode);
                count++;
            }
            driver.waitForLocatorToBeVisible(pPLSiBarcodes);
            List<WebElement> elements = driver.findElements(pPLSiBarcodes);
            for (WebElement element :
                    elements) {
                String pPLSiBarcode = driver.getText(element);
                testContext.getPlates().getDestinationPlatePPLSi().add(pPLSiBarcode);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSNormalizationandReformatting Failed due to " + e);
        }
    }

    public void PLSVectorHitpickingCreateSource() {
        try {
            selectMenu(plsDropdown, btnPLSVectorHitpickingCreateSource);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPLSi().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPLSi()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
            driver.waitForLocatorToBeVisible(pPLSvBarcodes);

            List<WebElement> allRows = driver.findElements(vectorTubeTable);
            int rowNumber = 1;
            for (WebElement row : allRows) {
                String pPLSvBarcode = row.findElement(ByT.xpath("//tr[%s]//td[1]").format(rowNumber)).getText();
                if (pPLSvBarcode.contains("pPLSv")) {
                    testContext.getPlates().setDestinationPlatePPLSv(pPLSvBarcode);
                    String tubeBarcode = row.findElement(ByT.xpath("//tr[%s]//td[6]").format(rowNumber)).getText();
                    Integer volume = (int) Double.parseDouble(row.findElement(ByT.xpath("//tr[%s]//td[5]").format(rowNumber)).getText());
                    Integer prevVolume = testContext.getPlates().getVectorVolume().get(tubeBarcode) == null ? 0 : testContext.getPlates().getVectorVolume().get(tubeBarcode);
                    testContext.getPlates().getVectorVolume().put(tubeBarcode, prevVolume + volume);
                }
                rowNumber++;
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLS Vector Hitpicking - Create Source Failed due to " + e);
        }
        Log.info("PLS Vector Hitpicking - Create Source Submitted");
    }


    public void PLSVectorPlateRetrieval() {
        try {

            sampleTrackerAPIComponent.fetchBasicPlateInfo(testContext.getPlates().getDestinationPlatePPLSv());
            String response = sampleTrackerAPIComponent.invokeTransformPreview(testContext.getPlates().getDestinationPlatePPLSv(), PLS_VECTOR_PLATE_RETRIEVAL, null);
            Boolean success = (Boolean) JsonUtils.extractJSON(response, SUCCESS);
            if (!success) {
                List<String> message = Arrays.asList(JsonUtils.extractJSON(response, MESSAGE).toString().split(" ").clone());
                Log.info("PLSVectorPlateRetrieval Returned error : " + response);

                for (String tube : message) {
                    if (tube.contains("LT_")) {
                        tube = tube.replace(",", "");
                        testContext.getPlates().getLtId().add(tube);
                        //Remove after testing
                        testContext.getPlates().getVectorVolume().put(tube, 1800);
                    }
                }
                Log.info("VIN Register is happening for LT_ID's: " + testContext.getPlates().getLtId());
                vin.VINRegisterVectorToTubes();
                vin.VINRegisterTubestoPlate();
                vin.VINRegisterPlatetoStorage();
            }
            selectMenu(plsDropdown, btnPLSVectorPlateRetrieval);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSv());
            driver.pauseExecutionFor(5000);
            driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
            List<WebElement> allRows = driver.findElements(vectorTubeTable);
            int rowNumber = 1;
            for (WebElement row : allRows) {
                if (rowNumber != 1) {
                    String plateBarcode = row.findElement(ByT.xpath("//tr[%s]//td[1]").format(rowNumber)).getText();
                    testContext.getPlates().getVectorPlateBarcode().add(plateBarcode);
                    String tubeBarcode = row.findElement(ByT.xpath("//tr[%s]//td[3]").format(rowNumber)).getText();
                    testContext.getPlates().getVectorTubeBarcodePLS().add(tubeBarcode);
                }
                rowNumber++;
            }

            List<WebElement> elements = driver.findElements(destinationBarcode);
            int count = 0;
            for (WebElement element : elements) {
                scanBarcode(element, testContext.getPlates().getVectorPlateBarcode().toArray()[count].toString());
                count++;
            }
            operatorLoginDuringQuestionnaire();
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSVectorPlateRetrieval Failed due to " + e);
        }
    }

    public void PLSVectorPlateBuild() {
        try {
            selectMenu(plsDropdown, btnPLSVectorPlateBuild);
            driver.pauseExecutionFor(2000);
            operatorLoginBeforeQuestionnaire();
            driver.clickLocator(btnPLSVectorPlate);
            int noOfBarcode = testContext.getPlates().getVectorPlateBarcode().size();
            for (String barcode : testContext.getPlates().getVectorPlateBarcode()) {
                if (noOfBarcode != 1) {
                    driver.scrollTillElement(addPlate);
                    driver.clickLocator(addPlate);
                }
                noOfBarcode--;
            }
            List<WebElement> elements = driver.findElements(inputPlateBarcode);
            int count = 0;
            for (WebElement element : elements) {
                scanBarcode(element, testContext.getPlates().getVectorPlateBarcode().toArray()[count].toString());
                count++;
            }
            if (testContext.getPlates().getDestinationRackId() == null) {
                String rackId = GenericUtil.getUniqueNumeric(10);
                testContext.getPlates().setDestinationRackId(rackId);
            }
            scanBarcode(destinationBarcode, testContext.getPlates().getDestinationRackId());

            List<WebElement> tubeElements = driver.findElements(tubePositions);
            int tubes = 0;
            for (WebElement element : tubeElements) {
                driver.pauseExecutionFor(4000);
                driver.moveToElement(element);
                driver.clickLocator(element);
                driver.copyTextToClipboard(testContext.getPlates().getVectorTubeBarcodePLS().toArray()[tubes].toString());
                driver.paste();
                driver.scrollTillElement(activeTubes);
                driver.pauseExecutionFor(4000);
                driver.clickLocatorByJS(activeTubes);
                tubes++;
            }
            driver.pauseExecutionFor(2000);
            driver.waitForLocatorToBeVisible(btnSubmit);
            driver.moveToLocatorByJS(btnSubmit);
            driver.clickLocatorByJS(btnSubmit);
            driver.waitForLocatorToBeVisible(stepFinishedText, 300);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSVectorPlateBuild Failed due to " + e);
        }
    }

    public void PLSTubePlacementConfirmation() {
        Log.info("Starting PLSTubePlacementConfirmation: " + testContext.getPlates().getVectorTubeBarcodePLS());
        sampleTrackerAPIComponent.invokeTransformPreview(testContext.getPlates().getDestinationRackId(), PLS_TUBE_PLACEMENT_CONFIRMATION, testContext.getPlates().getVectorTubeBarcodePLS());
        sampleTrackerAPIComponent.invokeTransformSpecs(testContext.getPlates().getDestinationRackId(), PLS_TUBE_PLACEMENT_CONFIRMATION);
        Log.info("PLSTubePlacementConfirmation completed: " + testContext.getPlates().getVectorTubeBarcodePLS());
    }

    public void PLSHamiltonVectorSourcePlateCreation() {
        try {
            selectMenu(plsDropdown, btnPLSHamiltonVectorSourcePlateCreation);
            operatorLoginBeforeQuestionnaire();
            selectHamiltonWizard(IHAM02);
            operatorLoginDuringQuestionnaire();
            scanCarriers(C384_E0, 2);
            scanPosition(C384_E0, "1", "1");
            driver.copyTextToClipboard(testContext.getPlates().getDestinationRackId());
            driver.pauseExecutionFor(1000);
            driver.paste();
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            driver.pauseExecutionFor(4000);
            scanPosition(C384_E0, "2", "1");
            driver.pauseExecutionFor(2000);
            String destinationPlateBarcode = driver.getText(destinationPLSv);
            driver.copyTextToClipboard(destinationPlateBarcode);
            driver.paste();
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationScanComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(4000);
            operatorLoginAfterQuestionnaire();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSHamiltonVectorSourcePlateCreation Failed due to " + e);
        }
    }

    public void PLSDrydownIn() {
        try {
            selectMenu(plsDropdown, btnPLSDrydownIn);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPLSi().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPLSi()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSDrydowIn Failed due to " + e);
        }
    }

    public void PLSDrydownOut() {
        try {
            selectMenu(plsDropdown, btnPLSDrydownOut);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPLSi().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPLSi()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            driver.pauseExecutionFor(4000);
            operatorLoginDuringQuestionnaire();
            driver.pauseExecutionFor(2000);
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSDrydowOut Failed due to " + e);
        }
    }

    public void PLSVectorHitpicking() {
        try {
//            mesapiComponent.movePlateToPLSVectorHitpicking(testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            selectMenu(plsDropdown, btnPLSVectorHitpicking);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSv());
            scanBarcode(destinationBarcode, testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSVectorHitpicking Failed due to " + e);
        }
    }

    public void PLSMasterMixCreation() {
        try {
            selectMenu(plsDropdown, btnPLSMasterMixCreation);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSMasterMixCreation Failed due to " + e);
        }
    }

    public void PLSMasterMixAddition() {
        try {
            selectMenu(plsDropdown, btnPLSMasterMixAddition);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            String mxps = driver.getText(destinationMXPs);
            scanBarcode(destinationBarcode, mxps);
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PLSMasterMixCreation Failed due to " + e);
        }
    }

    public void PLSThermocyling() {
        try {
            selectMenu(plsDropdown, btnPLSThermocyling);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            scanBarcode(destinationBarcode, ICYC180);
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PLSDilution() {
        try {
            selectMenu(plsDropdown, btnPLSDilution);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationPlatePPLSi().stream().findFirst().get());
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
