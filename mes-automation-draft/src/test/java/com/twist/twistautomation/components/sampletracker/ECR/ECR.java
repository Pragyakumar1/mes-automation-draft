package com.twist.twistautomation.components.sampletracker.ECR;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ECR extends SampleTrackerComponent {
    private By ecrDropdown = By.id("single-button4");
    private By btnEcrAnnealingBufferAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR Annealing Buffer Addition']");
    private By btnEcrDenaturationReannealing = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR Denaturation/Reannealing']");
    private By btnEcrPcrPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Planning']");
    private By btnCybioDispense = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR CyBio Dispense']");
    private By btnEcrEchoEnzymeAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR Echo Enzyme Addition']");
    private By containerBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']");
    private By inputPlateBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-empty']");
    private By btnSubmit = By.xpath("//button[@class='twst-button twst-blue-button' and normalize-space()='Submit']");
    public By btnStartCyBioWizard = By.xpath("//button[contains(text(),'Start Cybio Wizard')]");
    private By inputInstrumentBarcode = By.xpath("//input[@placeholder='Scan barcode']");
    public By btnPlateScanComplete = By.xpath("//button[contains(text(),'Plate Scan Complete')]");
    private By btnRunFinished = By.xpath("//button[contains(text(),'Run Finished')]");
    private By addPlate = By.xpath("//button[text()='+']");
    private By associatedStampDownBarCodes = By.xpath("//h4[contains(text(),'Associated ECR Stamp/Drydown Barcodes')]//following::input[1]");
    public By positionIndicatorCyBioWizard = By.xpath("//div[@class='twst-cybio-wizard-step-header-number ng-binding']//following::span[1]");
    private By destinationContainerBarCode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-not-empty']");
    private By btnEcrThermoCycling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR Thermocycling']");
    private By inputContainerBarcode = By.xpath("//input[@type='text']");
    private By thermocyclerBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");
    private By btnRecordAnotherStep = By.xpath("//button[text()='Record Another Step']");
    private By btnEcrButtonHitPickingCreateSource = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Primer Hitpicking - Create Source']");
    private By btnEcrMasterMixCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Master Mix Creation']");
    private By btnEcrPcrPrimerHitpickingDownloadWl = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Primer Hitpicking - Download WL']");
    private By btnEcrPcrMasterMixAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Master Mix Addition']");
    private By txtMasterMixBarcode = By.xpath("//p[contains(text(),'tMXEr')]");
    private By associatedMasterMixTubeBarCode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");
    private By btnEcrPcrPrimerHitPicking = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Primer Hitpicking']");
    private By instrumentIdField = By.xpath("//span[contains(text(),'Instrument #:')]//following::input[1]");
    private By loadingText = By.xpath("//p[contains(text(),'Loading During-Step Work Record for ECR/PCR Primer Hitpicking...')]");
    private By btnEcrPcrThermocycling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Thermocycling']");
    private By btnEcrPcrPurification = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Purification']");
    private By btnEcrPcrAliquotingForQuant = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Aliquoting for Quant']");
    private By btnEcrPcrStampEchoPlate = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-ECR dropdown open']//a[@class='ng-binding'][normalize-space()='ECR/PCR Stamp into Echo Plate']");

    private By btnSkipPlate = By.xpath("//button[contains(normalize-space(),'Skip Plate')]");

    private By btnSourceScanComplete = By.xpath("//button[contains(normalize-space(),'Source Scan Complete')]");
    private ByT destinationpEPRq = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pEPRq')])[%s]");
    private ByT destinationPlatePEPRp = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pEPRp')])[%s]");
    private ByT destinationPlatePEPRt = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pEPRt')])[%s]");
    private By btnDestinationPlacementComplete = By.xpath("//button[contains(normalize-space(),'Destination Placement Complete')]");
    private By positionIndicator = By.xpath("//strong[@class='twst-hamilton-step-direction-main ng-binding']");
    private By btnDestinationScanComplete = By.xpath("//button[contains(normalize-space(),'Destination Scan Complete')]");
    private By btnDestinationScanComplete1 = By.xpath("//button[contains(text(),'Destination Placement Complete')]");
    private By txtEcrPcrPurification = By.xpath("//h1[contains(text(),'ECR/PCR Purification')]");
    private By btnReadyPlatesQueue = By.xpath("//button[text()='Ready plates queue ']");
    private ByT barcodeInReadyPlate = ByT.xpath("//a[normalize-space()='%s']");
    private By btnCloseReadyPlates = By.xpath("//button[normalize-space()='Close']");

    private ByT thermocyclingCondition = ByT.xpath("(//td[contains(text(), 'CYC')])[%s]");


    public ECR ECRAnnealingBufferAddition() {
        try {
            selectMenu(ecrDropdown, btnEcrAnnealingBufferAddition);
            driver.waitForLocatorToBeVisible(btnStartCyBioWizard);
            driver.clickLocator(btnStartCyBioWizard);
            driver.sendKeys(inputInstrumentBarcode, IDSP64);
            driver.pauseExecutionFor(3000);

            int count = 2;
            for (String barcode : testContext.getPlates().getDestinationPlatePPRn()) {
                driver.pauseExecutionFor(2000);
                String positioinIndicator = driver.getText(positionIndicatorCyBioWizard);
                scanPosition(IDSP64, "", count + "");
                driver.copyTextToClipboard(barcode);
                driver.paste();
                driver.pauseExecutionFor(3000);
            }
            driver.clickLocatorByJS(btnPlateScanComplete);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(10000);
        } catch (Exception e) {
            Assert.fail("ECR Annealing Buffer Addition Failed due to " + e);
        }
        Log.info("ECR Annealing Buffer Addition Submitted");
        return this;
    }

    public ECR ECRDenaturationReannealing() {
        try {
            selectMenu(ecrDropdown, btnEcrDenaturationReannealing);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPRn().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRn()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("ECR Denaturation Reannealing Failed due to " + e);
        }
        Log.info("ECR Denaturation Reannealing Submitted");
        return this;
    }

    public ECR ECRPCRPlanning() {
        try {

            selectMenu(ecrDropdown, btnEcrPcrPlanning);
            driver.waitForLocatorToBeVisible(containerBarcode);
            driver.clickLocator(containerBarcode);
            driver.copyInputBoxText();
            Log.info("Container ECRs barcode is: " + driver.getTextFromClipboard());
            testContext.getPlates().setContainerpECRsBarCode(driver.getTextFromClipboard());
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPRn().size();
            int count = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRn()) {
                scanBarcode(associatedStampDownBarCodes, barcode);
                String thermoCondition = driver.getText(thermocyclingCondition.format(count));
                testContext.getPlates().getThermoConditionECR().add(thermoCondition);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
                count++;
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("ECR PCR Planning Failed due to " + e);
        }
        Log.info("ECR PCR Planning Submitted");
        return this;
    }

    public ECR ECRCyBioDispense() {
        try {

            selectMenu(ecrDropdown, btnCybioDispense);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPRn().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRn()) {
                scanBarcode(inputPlateBarcode, barcode);
                driver.pauseExecutionFor(3000);
                driver.waitForLocatorToBeVisible(destinationContainerBarCode);
                driver.clickLocator(destinationContainerBarCode);
                driver.copyInputBoxText();
                Log.info("Destination Container pECRe barcode is: " + driver.getTextFromClipboard());
                // Need to set multiple pECRe Bar code
                testContext.getPlates().getDestinationPlatepECRe().add(driver.getTextFromClipboard());
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("ECR CyBio Dispense Failed due to " + e);
        }
        Log.info("ECR CyBio Dispense Submitted");
        return this;
    }

    public ECR ECREchoEnzymeAddition() {
        try {
            selectMenu(ecrDropdown, btnEcrEchoEnzymeAddition);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatepECRe().size();
            for (String barcode : testContext.getPlates().getDestinationPlatepECRe()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("ECR Echo Enzyme Addition Failed due to " + e);
        }
        Log.info("ECR Echo Enzyme Addition Submitted");
        return this;
    }

    public ECR ECRThermocycling() {
        try {
            selectMenu(ecrDropdown, btnEcrThermoCycling);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatepECRe().size();
            int count = 0;
            for (String barcode : testContext.getPlates().getDestinationPlatepECRe()) {
                scanBarcode(inputContainerBarcode, barcode);
                scanBarcode(thermocyclerBarcode, I + testContext.getPlates().getThermoConditionECR().get(count));
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
                count++;
            }
        } catch (Exception e) {
            Assert.fail("ECR Thermocycling Failed due to " + e);
        }
        Log.info("ECR Thermocycling Submitted");
        return this;
    }

    public ECR ECRPCRPrimerHitpickingCreateSource() {
        try {
            selectMenu(ecrDropdown, btnEcrButtonHitPickingCreateSource);
            Log.info("Container ECRs Barcode is: " + testContext.getPlates().getContainerpECRsBarCode());
            scanBarcode(inputContainerBarcode, testContext.getPlates().getContainerpECRsBarCode());
            scrollAndClickSubmit();
            driver.pauseExecutionFor(4000);
        } catch (Exception e) {
            Assert.fail("ECRPCRPrimerHitpickingCreateSource Failed due to " + e);
        }
        Log.info("ECR/PCR Primer Hitpicking - Create Source Submitted");

        return this;
    }

    public ECR ECRPCRMasterMixCreation() {
        try {
            selectMenu(ecrDropdown, btnEcrMasterMixCreation);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatePPRn().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRn()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
            driver.pauseExecutionFor(4000);
        } catch (Exception e) {
            Assert.fail("ECRPCRMasterMixCreation Failed due to " + e);
        }
        Log.info("ECR PCR Master Mix Creation Submitted");
        return this;
    }

    public ECR ECRPCRPrimerHitpickingDownloadWL() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrPrimerHitpickingDownloadWl);
            scanBarcode(inputContainerBarcode, testContext.getPlates().getContainerpECRsBarCode());
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("ECRPCRPrimerHitpickingDownloadWL Failed due to " + e);
        }
        Log.info("ECR PCR Primer Hitpicking - Download WL Submitted");

        return this;
    }

    public ECR ECRPCRMasterMixAddition() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrMasterMixAddition);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatepECRe().size();
            for (String barCode : testContext.getPlates().getDestinationPlatepECRe()) {
                scanBarcode(inputContainerBarcode, barCode);
                driver.waitForLocatorToBeVisible(txtMasterMixBarcode);
                String masterMixTubeBarcode = driver.getText(txtMasterMixBarcode);
                scanBarcode(associatedMasterMixTubeBarCode, masterMixTubeBarcode);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
        } catch (Exception e) {
            Assert.fail("ECRPCRMasterMixAddition Failed due to " + e);
        }

        Log.info("ECR PCR Master Mix Addition Submitted");
        return this;
    }

    public ECR ECRPCRPrimerHitpicking() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrPrimerHitPicking);
            driver.pauseExecutionFor(7000);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatepECRe().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatepECRe()) {
                driver.clickLocatorByJS(inputContainerBarcode);
                scanBarcode(inputContainerBarcode, barcode);
                operatorLoginDuringQuestionnaire();
                driver.scrollDownByJS(2000);
                driver.waitForLocatorToBeVisible(btnSubmit);
                driver.clickLocatorByJS(btnSubmit);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
            driver.pauseExecutionFor(4000);
        } catch (Exception e) {
            Assert.fail("ECRPCRPrimerHitpicking Failed due to " + e);
        }
        Log.info("ECRPCRPrimerHitpicking Submitted");
        return this;
    }

    public ECR ECRPCRThermocycling() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrThermocycling);
            driver.pauseExecutionFor(4000);
            int noOfBarcodes = testContext.getPlates().getDestinationPlatepECRe().size();
            for (String barcode :
                    testContext.getPlates().getDestinationPlatepECRe()) {
                scanBarcode(inputContainerBarcode, barcode);
                //todo need to update hardcoded barcode
                scanBarcode(thermocyclerBarcode, ICYC187);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
        } catch (Exception e) {
            Assert.fail("ECRPCRThermocycling Failed due to " + e);
        }
        Log.info("ECR/PCR Thermocycling Submitted");
        return this;
    }

    public ECR ECRPCRPurification() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrPurification);
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, testContext.getPlates().getDestinationPlatepECRe().size() > 2 ? 4 : 2);
            for (String barcode :
                    testContext.getPlates().getDestinationPlatepECRe()) {
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            if (testContext.getPlates().getDestinationPlatepECRe().size() % 2 == 1)
                driver.clickLocatorByJS(btnSkipPlate);
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);

            int count = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatepECRe()) {
                driver.pauseExecutionFor(2000);
                String destinationPlateBarcode = driver.getText(destinationPlatePEPRp.format(count));
                // todo make it as list
                testContext.getPlates().getDestinationPlatePEPRp().add(destinationPlateBarcode);
                driver.copyTextToClipboard(destinationPlateBarcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
                driver.pauseExecutionFor(1000);
                scanPosition(C384_E0, count > 2 ? FOUR : TWO, GenericUtil.extractInt(driver.getText(positionIndicator)));
                count++;
            }
            driver.clickLocatorByJS(btnDestinationPlacementComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(12000);
            driver.moveToLocator(txtEcrPcrPurification);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ECRPCRPurification Failed due to ");
        }
        return this;
    }

    public ECR ECRPCRAliquotingforQuant() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrAliquotingForQuant);
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, 2);
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRp()) {
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            int counter = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRp()) {
                scanPosition(C384_E0);
                String destinationPlateBarcode = driver.getText(destinationpEPRq.format(counter));
                testContext.getPlates().getDestinationPlatePEPRq().add(destinationPlateBarcode);
                driver.copyTextToClipboard(destinationPlateBarcode);
                driver.paste();
                counter++;
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationScanComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ECR/PCR Aliquoting for Quant Failed due to " + e);
        }
        return this;
    }


    public void ECRPCRUploadQuantData() {
        try {
            for (String barcode : testContext.getPlates().getDestinationPlatePEPRq()) {
                Log.info("Upload Quant Data inProgress for barcode: " + barcode);
                sampleTrackerAPIComponent.fetchBasicPlateInfo(barcode);
                sampleTrackerAPIComponent.invokeTransformPreview(barcode, ECR_PCR_UPLOAD_QUANT_DATA, null);
                sampleTrackerAPIComponent.invokeTransformSpecs(barcode, ECR_PCR_UPLOAD_QUANT_DATA);
                Log.info("Upload Quant Data Completed for barcode: " + barcode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ECR/PCR Upload Quant Data Failed due to " + e);
        }

    }

    public ECR ECRPCRStampintoEchoPlate() {
        try {
            selectMenu(ecrDropdown, btnEcrPcrStampEchoPlate);
            driver.waitForLocatorToBeDeleted(loadingText);
            driver.pauseExecutionFor(2000);
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, 2);
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRp()) {
                waitForBarcodeAvailableInReadyPlates(barcode);
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            int counter = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRp()) {
                String destinationPlateBarcode = driver.getText(destinationPlatePEPRt.format(counter));
                testContext.getPlates().getDestinationPlatePEPRt().add(destinationPlateBarcode);
                driver.copyTextToClipboard(destinationPlateBarcode);
                driver.paste();
                driver.pauseExecutionFor(20000);
                String position = GenericUtil.extractInt(driver.getTextByJS(positionIndicator));
                List<String> list = Arrays.asList(position.split(SPACE));
                scanPosition(C384_E0, "2", list.get(0));
                counter++;
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationScanComplete1);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
            /*driver.switchToFrame(iframeAfterStepWorkRecord);
            driver.clickLocator(chkBoxPostdispenseTransfer);
            driver.scrollDownByJS(1000);
            driver.clickLocatorByJS(btnSaveAfterStepResponses);
            driver.pauseExecutionFor(4000);*/

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ECR/PCR Stamp into Echo Plate Failed due to " + e);
        }
        return this;
    }

    public void waitForBarcodeAvailableInReadyPlates(String barcode) throws Exception {
        driver.pauseExecutionFor(2000);
        driver.clickLocatorByJS(btnReadyPlatesQueue);
        int counter = 1;
        while (!driver.IsLocatorVisible(barcodeInReadyPlate.format(barcode))) {
            driver.pauseExecutionFor(10000);
            counter++;
            if (counter == 25)
                break;
        }
        driver.clickLocator(btnCloseReadyPlates);
    }
}
