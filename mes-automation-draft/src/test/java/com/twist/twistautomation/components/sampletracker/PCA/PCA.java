package com.twist.twistautomation.components.sampletracker.PCA;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class PCA extends SampleTrackerComponent {
    private By pcaDropdown = By.id("single-button2");
    private By btnPCAPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Planning']");
    private By btnPCAMasterMixCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Master Mix Creation']");
    private By btnPCAPrimerHitpickingCreateSource = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Primer Hitpicking - Create Source']");
    private By btnPCAMasterMixAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Master Mix Addition']");
    private By btnPCAOilAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Oil Addition']");
    private By btnPCAThermocycling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA Thermocycling']");
    private By btnPCAPCRMasterMixCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Master Mix Creation']");
    private By btnPCAPCRPrimerHitpicking = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Primer Hitpicking']");
    private By btnPCAPCRMasterMixAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Master Mix Addition']");
    private By btnPCAPCRThermocycling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Thermocycling']");
    private By btnPCAPCRPurification = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Purification (384 plate)']");
    private By btnPCAPCRAliquotingForQuant = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Aliquoting for Quant']");
    private By btnPCAPCRNormalization = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-PCA dropdown open']//a[@class='ng-binding'][normalize-space()='PCA/PCR Normalization']");


    private By addPlate = By.xpath("//button[text()='+']");
    private By btnRecordAnotherStep = By.xpath("//button[text()='Record Another Step']");
    private By btnSubmit = By.xpath("//button[@class='twst-button twst-blue-button' and normalize-space()='Submit']");

    private By inputPlateBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-empty']");
    private By containerBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']");
    private By inputContainerBarcode = By.xpath("//input[@type='text']");
    private By associatedMasterMixPlaceBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");
    private By txtMasterMixBarcode = By.xpath("//td[contains(normalize-space(),'pMXP')]");
    private By txtMasterMixTubeBarcode = By.xpath("//p[contains(normalize-space(),'tMXP')]");
    private By thermocyclerBarcode = By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");

    private By btnSkipPlate = By.xpath("//button[contains(normalize-space(),'Skip Plate')]");
    private By btnSourceScanComplete = By.xpath("//button[contains(normalize-space(),'Source Scan Complete')]");
    private ByT destinationPlatePPRp = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pPPRp')])[%s]");
    private ByT destinationpPPRq = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pPPRq')])[%s]");
    private ByT destinationpPPRn = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pPPRn')])[%s]");
    private By btnDestinationPlacementComplete = By.xpath("//button[contains(normalize-space(),'Destination Placement Complete')]");
    private By btnDestinationScanComplete = By.xpath("//button[contains(normalize-space(),'Destination Scan Complete')]");
    private By btnRunFinished = By.xpath("//button[normalize-space()='Run Finished']");
    private By positionIndicator = By.xpath("//strong[@class='twst-hamilton-step-direction-main ng-binding']");

    private By btnReadyPlatesQueue = By.xpath("//button[text()='Ready plates queue ']");
    private ByT barcodeInReadyPlate = ByT.xpath("//a[normalize-space()='%s']");
    private By btnCloseReadyPlates = By.xpath("//button[normalize-space()='Close']");
    private By loadingBeforeText = By.xpath("//p[contains(text(),'Loading Before')]");
    private By txtPcaPcrNormalization = By.xpath("//h1[contains(text(),'PCA/PCR Normalization')]");


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

    public PCA PCAPlanning() {
        try {
            selectMenu(pcaDropdown, btnPCAPlanning);
            //storing container barcode into context
            driver.waitForLocatorToBeVisible(containerBarcode);
            driver.clickLocator(containerBarcode);
            driver.copyInputBoxText();
            Log.info("Container PCAs barcode is: " + driver.getTextFromClipboard());
            testContext.getPlates().setContainerBarcode(driver.getTextFromClipboard());
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PCAPlanning Failed due to " + e);
        }
        Log.info("PCA Planning Submitted");
        return this;
    }

    public PCA PCAMasterMixCreation() {
        try {
            selectMenu(pcaDropdown, btnPCAMasterMixCreation);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("PCAMasterMixCreation Failed due to " + e);
        }
        Log.info("PCA Master Mix Creation Submitted");
        return this;
    }

    public PCA PCAPrimerHitpickingCreateSource() {
        try {
            selectMenu(pcaDropdown, btnPCAPrimerHitpickingCreateSource);
            Log.info("Container PCAs Barcode is: " + testContext.getPlates().getContainerBarcode());
            scanBarcode(inputContainerBarcode, testContext.getPlates().getContainerBarcode());
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("PCAPrimerHitpickingCreateSource Failed due to " + e);
        }
        Log.info("PCA Primer Hitpicking - Create Source Submitted");

        return this;
    }

    public PCA PCAMasterMixAddition() {
        try {
            selectMenu(pcaDropdown, btnPCAMasterMixAddition);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                driver.waitForLocatorToBeVisible(txtMasterMixBarcode);
                String masterMixBarcode = driver.getText(txtMasterMixBarcode);
                scanBarcode(associatedMasterMixPlaceBarcode, masterMixBarcode);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
        } catch (Exception e) {
            Assert.fail("PCAMasterMixAddition Failed due to " + e);
        }

        Log.info("PCA Master Mix Addition Submitted");
        return this;
    }

    public PCA PCAOilAddition() {
        try {
            selectMenu(pcaDropdown, btnPCAOilAddition);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("PCAOilAddition Failed due to " + e);
        }
        return this;
    }

    public PCA PCAThermocycling() {
        try {
            selectMenu(pcaDropdown, btnPCAThermocycling);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                //todo need to update hardcoded barcode
                scanBarcode(thermocyclerBarcode, ICYC180);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
        } catch (Exception e) {
            Assert.fail("PCAThermocycling Failed due to " + e);
        }
        Log.info("PCA Thermocycling Submitted");
        return this;
    }

    public PCA PCAPCRMasterMixCreation() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRMasterMixCreation);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("PCAPCRMasterMixCreation Failed due to " + e);
        }
        Log.info("PCAPCRMasterMixCreation Submitted");
        return this;
    }

    public PCA PCAPCRPrimerHitpicking() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRPrimerHitpicking);
            scanBarcode(inputContainerBarcode, testContext.getPlates().getContainerBarcode());
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("PCAPCRPrimerHitpicking Failed due to " + e);
        }
        Log.info("PCAPCRPrimerHitpicking Submitted");
        return this;
    }

    public PCA PCAPCRMasterMixAddition() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRMasterMixAddition);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                driver.waitForLocatorToBeVisible(txtMasterMixTubeBarcode);
                String tubeBarcode = driver.getText(txtMasterMixTubeBarcode);
                scanBarcode(associatedMasterMixPlaceBarcode, tubeBarcode);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }

        } catch (Exception e) {
            Assert.fail("PCAPCRMasterMixAddition Failed due to " + e);
        }
        Log.info("PCAPCRMasterMixAddition Submitted");
        return this;
    }

    public PCA PCAPCRThermocycling() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRThermocycling);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                //todo need to update hardcoded barcode
                scanBarcode(thermocyclerBarcode, ICYC180);
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }

        } catch (Exception e) {
            Assert.fail("PCAPCRThermocycling Failed due to " + e);
        }
        Log.info("PCA/PCR Thermocycling Submitted");
        return this;
    }

    public PCA PCAPCRPurification() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRPurification);
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, testContext.getPlatesBarCodeList().size() > 2 ? 4 : 2);
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            if (testContext.getPlatesBarCodeList().size() % 2 == 1)
                driver.clickLocatorByJS(btnSkipPlate);
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);

            int count = 1;
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                driver.pauseExecutionFor(2000);
                String destinationPlateBarcode = driver.getText(destinationPlatePPRp.format(count));
                // todo make it as list
                testContext.getPlates().getDestinationPlatePPRp().add(destinationPlateBarcode);
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
            driver.pauseExecutionFor(3000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PCAPCRPurification Failed due to ");
        }
        return this;
    }

    public PCA PCAPCRAliquotingforQuant() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRAliquotingForQuant);
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, 2);
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            int counter = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                scanPosition(C384_E0);
                String destinationPlateBarcode = driver.getText(destinationpPPRq.format(counter));
                testContext.getPlates().getDestinationPlatePPRq().add(destinationPlateBarcode);
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
            Assert.fail("PCAPCRAliquotingforQuant Failed due to " + e);
        }
        return this;
    }

    public void PCAPCRNormalization() {
        try {
            selectMenu(pcaDropdown, btnPCAPCRNormalization);
            driver.waitForLocatorToBeDeleted(loadingBeforeText);
            driver.pauseExecutionFor(2000);
            operatorLoginBeforeQuestionnaire();
            selectHamiltonWizard(IHAM02);
            scanCarriers(C384_E0, 2);

            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                waitForBarcodeAvailableInReadyPlates(barcode);
            }

            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                scanPosition(C384_E0);
                driver.copyTextToClipboard(barcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            int counter = 1;
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                scanPosition(C384_E0);
                String destinationPlateBarcode = driver.getText(destinationpPPRn.format(counter));
                testContext.getPlates().getDestinationPlatePPRn().add(destinationPlateBarcode);
                driver.copyTextToClipboard(destinationPlateBarcode);
                driver.paste();
                counter++;
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationScanComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(7000);
            driver.moveToLocator(txtPcaPcrNormalization);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PCAPCRUploadQuantData() {
        try {
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRq()) {
                Log.info("Upload Quant Data inProgress for barcode: " + barcode);
                sampleTrackerAPIComponent.fetchBasicPlateInfo(barcode);
                sampleTrackerAPIComponent.invokeTransformPreview(barcode, PCA_PCR_UPLOAD_QUANT_DATA, null);
                sampleTrackerAPIComponent.invokeTransformSpecs(barcode, PCA_PCR_UPLOAD_QUANT_DATA);
                Log.info("Upload Quant Data Completed for barcode: " + barcode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("PCAPCRUploadQuantData Failed due to " + e);
        }

    }

}
