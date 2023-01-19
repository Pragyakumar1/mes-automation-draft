package com.twist.twistautomation.components.sampletracker.TEA;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.ByT;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.twist.twistautomation.constants.SampleTrackerMenuItems.*;

@Component
public class TEA extends SampleTrackerComponent {
    SoftAssert softAssert= new SoftAssert();

    private By teaDropdown = By.id("single-button19");
    private By btnTeAReSuspension = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA Re-Suspension']");
    private By btnTeaCreateDaughterPlates = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA Create Daughter Plates']");
    private By btnTeaMmCreation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA MM Creation']");
    private By btnMMDispensing = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA PCR Master Mix Dispensing']");
    private By btnPCRPlateStamping = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA PCR Plate Stamping']");
    private By btnPCRPlateThermocycling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA PCR Plate Thermocycling']");
    private By btnTEPPoolingPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEP Pooling Planning']");
    private By btnTeaRetention = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA Retention']");
    private By btnTeaRevival = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEA Revival']");
    private By spinnerSampleTracker = By.xpath("//div[@class='twst-spinner twst-spec-updating-spinner ng-scope ng-isolate-scope']");
    //  private By btnSubmit = By.xpath("//button[@class='twst-button twst-blue-button' and normalize-space()='Submit']");
    private By btnSubmit = By.xpath("//button[contains(text(),'Submit')]");
    private By stepFinishedText = By.xpath("//span[normalize-space()='Step Finished']");
    private ByT srnCheckBox = ByT.xpath("//td[contains(text(),'%s')]//preceding::div[2]");
    private By btnStartCyBioWizard = By.xpath("//button[contains(text(),'Start Cybio Wizard')]");
    private By inputInstrumentBarcode = By.xpath("//input[@placeholder='Scan barcode']");
    private By txtDilutionA = By.xpath("//td[contains(text(),'Destinations')]//following::span[1]");
    private By getTxtDilutionB = By.xpath("//td[contains(text(),'Destinations')]//following::span[3]");
    private By btnPlateScanComplete = By.xpath("//button[contains(text(),'Plate Scan Complete')]");
    private By btnRunFinished = By.xpath("//button[contains(text(),'Run Finished')]");
    private By inputPlateBarCode = By.xpath("//input[@class='form-control ng-pristine ng-valid ng-empty ng-touched']");
    private By txtFirstMMxBarCode = By.xpath("//td[contains(text(),'Destinations')]//following::span[1]");
    private By txtSecondMMxBarCode = By.xpath("//td[contains(text(),'Destinations')]//following::span[3]");
    private By txtThirddMMxBarCode = By.xpath("//td[contains(text(),'Destinations')]//following::span[5]");
    private By inputFirstMMxDestinationContainerBarCode = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[1]");
    private By inputSecondMMxDestinationContainerBarCode = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[2]");
    private By inputThirdMMxDestinationContainerBarCode = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[3]");
    private By positionIndicatorCyBioWizard = By.xpath("//div[@class='twst-cybio-wizard-step-header-number ng-binding']//following::span[1]");
    private By btnPrint = By.xpath("//div[contains(text(),'Millenium Lane')]//following::button[1]");
    private By txtDILA = By.xpath("//td[contains(text(),'Destinations')]//following::span[1]");
    private By txtDILB = By.xpath("//td[contains(text(),'Destinations')]//following::span[3]");
    private ByT destinationDILA = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'A')])[%s]");
    private ByT destinationDILB = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'B')])[%s]");
    private ByT destinationMMX = ByT.xpath("(//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'MMX')])[%s]");
    private By inputContainerBarcode = By.xpath("//input[@type='text']");
    private ByT inputContainerBarcodeList = ByT.xpath("//div[@class='twst-record-step-plate-input twst-record-step-source-plate-input-%s']");
    //private By associatedDestinationContainerBarCodeField = By.xpath("//input[@class='form-control ng-pristine ng-valid ng-scope ng-empty ng-touched']");
    private By associatedDestinationContainerBarCodeField = By.xpath("//p[contains(text(),'Destination Container Barcode:')]//following::input[1]");
    private By associatedDestinationContainerBarCodeFields = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[@type='text']");
    private By associateDestinationContainerPCRSingleBarCodeField = By.xpath("//input[@type='text']//following::input[@type='text']");
    private By associateDestinationContainerFLPBarCodeField = By.xpath("//p[contains(text(),'Destination Container Barcode:')]//following::input[@type='text']");
    private By btnRecordAnotherStep = By.xpath("//button[text()='Record Another Step']");
    private By addPlate = By.xpath("//button[text()='+']");
    private By btnClose = By.xpath("//span[@title='close']");
    private By txtNumberofRequiredAmplification = By.xpath("//td[contains(text(),'Number of Plate Amplifications Required')]//following::td[4]");
    private By txtClusterCount = By.xpath("//td[contains(text(),'Number of Plate Amplifications Required')]//following::td[5]");
    private By txtplateProcessing = By.xpath("//p[@class='twst-requested-data-readout ng-binding ng-scope']");
    private By btnRestartPlateScan = By.xpath("//button[contains(text(),'restart plate scan')]");
    private By labelCreateDaughterPlates = By.xpath("//h1[contains(text(),'TEA Create Daughter Plates')]");
    private By btnSrnSelectionComplete = By.xpath("//button[contains(text(),'SRN Selection Complete')]");
    private By btnSelectNone = By.xpath("//button[contains(text(),'select none')]");
    private By transformSpecCheckBox = By.xpath("//span[contains(text(),'Preview Transform Spec')]//preceding::div[1]");
    private By btnAddNote = By.xpath("//button[contains(text(),'Add Note')]");
    private By btnSelectAll = By.xpath("//button[contains(text(),'select all')]");
    private By labelReSuspension = By.xpath("//h1[contains(text(),'TEA Re-Suspension')]");
    private By btnReadyPlatesQueue = By.xpath("//*[contains(text(),'Ready plates queue')]");
    private By readyPlateLoadingSpinner = By.xpath("//span[contains(text(), 'Plates Ready for')]/../../..//div[@class='twst-spinner ng-scope ng-isolate-scope']");
    private By readyPlateTable = By.xpath("//table[contains(@class, 'twst-data-readout-table twst-ready-plates-list')]");
    private By btnClearForm = By.xpath("//a[contains(text(),'clear form')]");
    private By btnSelectAStep = By.xpath("//h1[contains(text(),'Select a Step')]");
    //private By linkDownloadBloankTransformTemplate = By.xpath("//a[contains(text(),'Download blank transform template')]");
    private By listOfpCHPxBarcode = By.xpath("//span[contains(text(),'pCHPx')]");
    private By listOfContainerBarCode = By.xpath("//p[contains(text(),'Container Barcodes:')]//following::input[@type='text']");
    private By dilPlates = By.xpath("//td[contains(text(),'Destinations')]//following::span[contains(text(),'DIL')]");
    // private By destinationDILAContainerInputField=By.xpath("//input[@class='form-control ng-pristine ng-valid ng-scope ng-empty ng-touched']");
    private By destinationDILAContainerInputField = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[1]");
    private By destinationDILBContainerInputField = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[2]");
    //private By destinationDILBContainerInputField=By.xpath("//input[@class='form-control ng-pristine ng-untouched ng-valid ng-scope ng-empty']");
    // private By txtClusterCount = By.xpath("//td[contains(text(),'DES - Individual Panel Amplifications Required - C')]//following::td[4]");
    private By MMXBarcode = By.xpath("//span[contains(text(),'MMX')]");
    private By addSinglePlate = By.xpath("//span[contains(text(),'Use standard template')]//following::button[2]");
    private By tabUseStandardTemplate = By.xpath("//span[contains(text(),'Use standard template')]");
    private By PCRBarcode = By.xpath("//td[contains(text(),'Destinations')]//following::span[contains(text(),'PCR')]");
    //private By thermocyclerBarCodeInputField = By.xpath("//td[contains(text(),'Thermocycler')]//following::input[@type='text']");
    private By thermocyclerBarCodeInputField = By.xpath("//input[@type='text' and @class='ng-pristine ng-untouched ng-valid ng-empty']");
    private By divPackingSpinner = By.xpath("//div[@class='twst-spinner-inner']");
    private By hamiltonPlatesSRNCheckBox = By.xpath("//*[contains(text(),'Hamilton Plates')]//following::td[contains(text(),'%s')]");
    private By btnViewSavedSpec = By.xpath("//button[contains(text(),'View Saved Spec')]");
    private By btnProceedToNextStep = By.xpath("//button[contains(text(),'Proceed to Next Step')]");
    private By btnselectNone = By.xpath("//button[contains(text(),'select none')]");
    private By btnReadyPlateQueue = By.xpath("//button[contains(text(),'Ready plates queue')]");
    private ByT barcodeInReadyPlate = ByT.xpath("//a[normalize-space()='%s']");
    private By btnCloseReadyPlates = By.xpath("//button[normalize-space()='Close']");
    private By barcodeScanMessage = By.xpath("//input[@type='text']/..//span[@class='twst-thumb-validation-error ng-binding ng-scope']");
    private By barcodeScanHandIcon = By.xpath("//input[@type='text']/..//div[contains(@class, 'twst-thumb-validation-icon ')]");
    //public By btnAddSource = By.xpath("//span[@class='twst-add-plate-button twst-add-source-plate-button'][span[text()='Source']]/button");
    private By srnWithMoreThanOneBarCode = By.xpath("//table[@class='twst-tea-re-suspension-plate-table']/tbody[count(./tr) > 3]//td[contains(@class, 'twst-tea-re-suspension-plate-table-plate-barcode-cell')]");
    String srn_number = "//td[contains(text(),'%s')]//preceding::div[1]//preceding::td[9]";
    String chkBox = "//td[contains(text(),'%s')]//preceding::div[1]";
    String invalidHandIconClass = "twst-thumb-validation-icon-error";
    String validHandIconClass = "twst-thumb-validation-icon-valid";
    private String labelTextForSuccessfulSubmission = "//h3[contains(text(),'%s')]//span[contains(text(),'Step Finished')]";
    private By btnShowErrorMessage = By.xpath("//button[contains(text(),'show error message')]");
    private By txtServerError = By.xpath("//span[contains(text(),'Server Error!')]");
    private By txtFullErrorMessage = By.xpath("//p[@ng-if='showSpecErrorMessage']");
    private By txtCountOfReadyPlateBarcode = By.xpath("//button[contains(text(),'Ready plates queue')]//following::span[@ng-if='!ReadyPlatesDataObj.fetches']");
    private By linkDownloadBlankTransformTemplate = By.xpath("//a[contains(text(),'Download plan in excel format')]");
    private By transformSpecTableSourcePlate = By.xpath("//td[contains(text(),'Destination Well Count')]//following::td[2]");
    private By transformSpecTableDestinationPlate = By.xpath("//td[contains(text(),'Destination Well Count')]//following::td[4]");
    private By checkBoxTransformSpec = By.xpath("//span[contains(text(),'Preview Transform Spec')]");
    private By firstHamiltonPlateCheckBox = By.xpath("//h4[contains(text(),'Hamilton Plates')]//following::td[1]");
    private By firstFlipJigCheckBox = By.xpath("//h4[contains(text(),'Flip Jig Plates')]//following::td[1]");



    public TEA TEAReSuspension() throws Exception {
        try {
            Log.info(OPERATING + SPACE + TEA_RE_SUSPENSION);
            //testContext.getSrnSet().add("SRN_006585");
            /*validateDuplicateBarcodeScan(); //Test Case #18466
            validateSRNBoxCheckAndUncheck(testContext.getSrnSet().stream().findFirst().get()); *///Test Case #18453

            selectMenu(btnTeAReSuspension);
            driver.pauseExecutionFor(3000);
            ArrayList listFromSet = new ArrayList(testContext.getSrnSet());
            for (int i = 0; i < listFromSet.size(); ++i) {
                String srnCheckBox = String.format(srn_number, listFromSet.get(i));
                chkBox = String.format(chkBox, listFromSet.get(i));
                driver.pauseExecutionFor(5000);
                driver.scrollTillElement(By.xpath(srnCheckBox));
                driver.clickLocator(By.xpath(chkBox));
            }

            List<WebElement> listOfBarcode = driver.findElements(listOfpCHPxBarcode);
            ArrayList<String> updatedList = new ArrayList();

            for (int i = 0; i < listOfBarcode.size(); ++i) {
                updatedList.add(listOfBarcode.get(i).getText());
            }
            testContext.setPCHPxBarcodeList(updatedList);
            Log.info("***List of pCHPx barcodes are following***");
            Log.info(String.valueOf(testContext.getPCHPxBarcodeList()));
            //--
            verifyFormatOfpCHPxBarcode(testContext.getPCHPxBarcodeList());
            //--
            Log.info("*******************************************");
            Log.info(updatedList.toString());
            List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);

            //---
            verifyCountOfContainerInputField(listOfBarcode, listOfInputContainerField);
            //verifySubmitButtonDisableUntilScanAllBarcode(btnSubmit, inputContainerBarcode, updatedList); (Will use in separate flow)

            //--
            int count = 0;
            for (WebElement element : listOfInputContainerField) {
                scanBarcode(element, updatedList.get(count));
                count++;
            }
            driver.pauseExecutionFor(5000);
            driver.scrollTillElement(btnSubmit);
            scrollAndClickSubmit();
            driver.pauseExecutionFor(4000);
            verifyViewedSavedSpecRecordAnotherStepButtonVisible(btnViewSavedSpec, btnProceedToNextStep, btnRecordAnotherStep);
            verifySuccessfulSubmissionMessage(TEA_RE_SUSPENSION);
        } catch (Exception e) {
            Assert.fail("TEA Re-Suspension Failed due to " + e);
        }
        Log.info("TEA Re-Suspension Submitted");
        return this;
    }

    public TEA TEACreateDaughterPlates() {
        try {
            Log.info(OPERATING + SPACE + TEA_CREATE_DAUGHTER_PLATES );
            selectMenu(teaDropdown, btnTeaCreateDaughterPlates);
            verifypCHPxbarcodeIsAvailableInReadyPlates(testContext.getPCHPxBarcodeList());

            int sizeOfpCHPxBarCodeList = testContext.getPCHPxBarcodeList().size();
            if (sizeOfpCHPxBarCodeList > 1) {
                for (int i = 1; i < sizeOfpCHPxBarCodeList; ++i) {
                    driver.clickLocator(addSinglePlate);
                }

                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
                int count = 0;
                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getPCHPxBarcodeList().get(count));
                    count++;
                }
            } else {
                scanBarcode(inputContainerBarcode, testContext.getPCHPxBarcodeList().get(0));
            }

            driver.pauseExecutionFor(4000);
            int numberOfRequiredAmplification = Integer.parseInt(driver.getText(txtNumberofRequiredAmplification));
            Log.info("Number of Required Amplification is------->" + numberOfRequiredAmplification);
            int clusterCount = getClusterCount(driver.getText(txtClusterCount));
            Log.info("Total number of cluster count is---------->" + clusterCount);
            testContext.setNumberOfRequiredAmplification(numberOfRequiredAmplification);
            testContext.setClusterCount(clusterCount);

            List<WebElement> DILbarcodeList = driver.findElements(dilPlates);
            ArrayList<String> updatedDILBarCodeList = new ArrayList<>();
            ArrayList<String> updatedDILABarcodeList = new ArrayList<>();
            ArrayList<String> updatedDILBBarcodeList = new ArrayList<>();

            for (int i = 0; i < DILbarcodeList.size(); ++i) {
                updatedDILBarCodeList.add(driver.getText(DILbarcodeList.get(i)));
            }
            testContext.setDILBarcodeList(updatedDILBarCodeList);
            Log.info("********DIL Barcode lists are following********");
            Log.info(String.valueOf(testContext.getDILBarcodeList()));
            //--
            verifyDILPlateIsStartingWithSRNExcludingZero(updatedDILBarCodeList);
            verifyPlateProcessingText(NO_PLACE_IN_STORAGE, testContext.getNumberOfRequiredAmplification());


            //--

            for (int i = 0; i < updatedDILBarCodeList.size(); ++i) {
                if (updatedDILBarCodeList.get(i).contains("A")) {
                    updatedDILABarcodeList.add(updatedDILBarCodeList.get(i));
                } else {
                    updatedDILBBarcodeList.add(updatedDILBarCodeList.get(i));
                }
            }

            testContext.setDILABarCodeList(updatedDILABarcodeList);
            testContext.setDILBBarCodeList(updatedDILBBarcodeList);
            //--
            verifyFormatOfDILBarcode(testContext.getSrnSet(), testContext.getDILABarCodeList(), A);
            verifyFormatOfDILBarcode(testContext.getSrnSet(), testContext.getDILBBarCodeList(), B);
            //--

            List<WebElement> dilDestinationContainerFieldList = driver.findElements(associatedDestinationContainerBarCodeFields);

            int flag = 0;
            for (WebElement element : dilDestinationContainerFieldList) {
                scanBarcode(element, updatedDILBarCodeList.get(flag));
                flag++;
            }
            driver.pauseExecutionFor(4000);
            scrollAndClickSubmit();
            //--
            verifySuccessfulSubmissionMessage(TEA_CREATE_DAUGHTER_PLATES);
            verifyDILBPlateExistOnRetentionOnDaughterPlatesSubmission(testContext.getNumberOfRequiredAmplification(), testContext.getDILBBarCodeList());

            //-

        } catch (Exception e) {
            Assert.fail("TEA Create Daughter Plates Failed due to " + e);
        }
        Log.info("TEA Create Daughter Plates Submitted");
        return this;
    }


    public TEA TEAMMCreation() {
        Log.info(OPERATING+SPACE+TEA_MM_CREATION);
        try {
            /*testContext.setNumberOfRequiredAmplification(1);
            //testContext.setDILAPlate("5939DILI901A");
            //testContext.setDILBPlate("6648DILOP11B");
            testContext.getDILBarcodeList().add("5866DILI913A");
            testContext.getDILBarcodeList().add("5866DILI913B");
            testContext.getDILABarCodeList().add("5866DILI913A");
            testContext.getDILBBarCodeList().add("5866DILI913B");*/

            // Above mentioned code is for temporary basis to run this step
            int count = 0;

            Log.info("TEA MM Creation operation started");
            if (testContext.getDILABarCodeList().size() > 0 && testContext.getDILBBarCodeList().size() > 0) { //Test Case #18664, 18663
                validateDILScanErrorMessageAsPerAmplificationLevel(testContext.getDILABarCodeList().get(0), testContext.getDILBBarCodeList().get(0));
            }

            selectMenu(teaDropdown, btnTeaMmCreation);
           // validateReadyPlatesQueue(); //Test Case #18471, 18472

            int sizeOfDILABarcodelist = testContext.getDILABarCodeList().size();
            driver.pauseExecutionFor(7000);
            if (testContext.getNumberOfRequiredAmplification() <= 16 && sizeOfDILABarcodelist >= 1) {
                for (int i = 1; i < sizeOfDILABarcodelist; ++i) {
                    driver.clickLocator(addSinglePlate);
                }

                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);

                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getDILABarCodeList().get(count));
                    count++;
                }
                driver.pauseExecutionFor(4000);
            } else {
                int sizeOfDILAllPlate = testContext.getDILBarcodeList().size();

                for (int i = 1; i < sizeOfDILAllPlate; ++i) {
                    driver.clickLocator(addSinglePlate);
                }
                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);

                count = 0;
                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getDILBarcodeList().get(count));
                    count++;
                }
                driver.pauseExecutionFor(4000);
            }

            List<WebElement> MMXElementList = driver.findElements(MMXBarcode);
            List<WebElement> destinationContainerFieldList = driver.findElements(associatedDestinationContainerBarCodeFields);
            ArrayList<String> updatedMMXBarcodeList = new ArrayList<>();


            for (int i = 0; i < MMXElementList.size(); ++i) {
                updatedMMXBarcodeList.add(driver.getText(MMXElementList.get(i)));
            }
            Log.info("*******Destination MMX barcodes are following*****");
            Log.info(String.valueOf(updatedMMXBarcodeList));
            //--
            verifySubmitButtonIsDisabledForMMCreation(btnSubmit);
            verifyMMXBarcodeIsStartingWithMMXAndCurrentExeDate(updatedMMXBarcodeList);
            //--

            if (MMXElementList.size() == 1) {
                scanBarcode(associatedDestinationContainerBarCodeField, updatedMMXBarcodeList.get(0));
            } else {
                count = 0;
                for (WebElement element : destinationContainerFieldList) {
                    scanBarcode(element, updatedMMXBarcodeList.get(count));
                    count++;
                }
            }

            driver.pauseExecutionFor(4000);
            testContext.setDestinationPlateMMX(updatedMMXBarcodeList);
            //--
            verifyFormatOfMMXBarcode(testContext.getDestinationPlateMMX());
            //--
            scrollAndClickSubmit();
            //--
            verifyDILAPlateExistOnPlateStampingAfterMMCreationionSubmission(testContext.getNumberOfRequiredAmplification(), testContext.getDILABarCodeList());
            verifyMMXBarcodeExistOnMMDispensingAfterMMCreationSubmission(testContext.getNumberOfRequiredAmplification(), testContext.getDestinationPlateMMX());
            //--
        } catch (Exception e) {
            Assert.fail("TEAMMCreation Failed due to " + e);
        }
        Log.info("TEA MM Creation Submitted");
        return this;
    }

    public TEA TEAPCRMasterMixDispensing() {
        Log.info(OPERATING+SPACE+TEA_PCR_MASTER_MIX_DISPENSING);
        try {
           /* testContext.getDestinationPlateMMX().add("MMX211112I9016");
            testContext.getDestinationPlateMMX().add("MMX211108I9001");*/

            //above code is for temporary basis

            int count = 0;
            selectMenu(teaDropdown, btnMMDispensing);
            driver.pauseExecutionFor(4000);
            int sizeOfMMXBarcodeList = testContext.getDestinationPlateMMX().size();
            if (sizeOfMMXBarcodeList == 1) {
                scanBarcode(inputContainerBarcode, testContext.getDestinationPlateMMX().get(0));
                driver.pauseExecutionFor(6000);
            } else {
                for (int i = 1; i < sizeOfMMXBarcodeList; ++i) {
                    driver.clickLocator(addSinglePlate);
                }
                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getDestinationPlateMMX().get(count));
                    count++;
                }
                driver.pauseExecutionFor(6000);
            }

            driver.pauseExecutionFor(5000);
            List<WebElement> PCRBarcodeList = driver.findElements(PCRBarcode);
            List<WebElement> destinationContainerFieldList = driver.findElements(associatedDestinationContainerBarCodeFields);
            ArrayList<String> updatedPCRBarcodeList = new ArrayList<>();

            int sizeOfPCRElements = PCRBarcodeList.size();
            driver.clickLocator(tabUseStandardTemplate);
            for (int i = 0; i < sizeOfPCRElements; ++i) {
                updatedPCRBarcodeList.add(driver.getText(PCRBarcodeList.get(i)));
            }
            Log.info("***PCR Barcodes are following*****");
            Log.info(String.valueOf(updatedPCRBarcodeList));
            //--
            verifyPCRBarcodeIsStartedWithNonZeroSRNNumberandPCR(updatedPCRBarcodeList);
            verifyFormatOfPCRBarcode(testContext.getSrnSet(), updatedPCRBarcodeList);
            //--
            count = 0;
            if (PCRBarcodeList.size() == 1) {
                scanBarcode(associatedDestinationContainerBarCodeField, updatedPCRBarcodeList.get(0));
            } else {
                for (WebElement element : destinationContainerFieldList) {
                    scanBarcode(element, updatedPCRBarcodeList.get(count));
                    count++;
                }
            }
            driver.pauseExecutionFor(4000);
            testContext.setDestinationPCRPlate(updatedPCRBarcodeList);

            scrollAndClickSubmit();

        } catch (Exception e) {
            Assert.fail("TEA PCR Master Mix Dispensing due to " + e);
        }
        Log.info("TEA PCR Master Mix Dispensing Submitted");
        return this;
    }

    public TEA TEAPCRPlateStamping() {
        Log.info(OPERATING+SPACE+TEA_PCR_PLATE_STAMPING);
        try {
            /*testContext.setDILABarCodeList(Collections.singletonList("6701DILI901A"));
            testContext.setNumberOfRequiredAmplification(2);*/
           /* testContext.getDILBarcodeList().add("6683DILOP02A");
            testContext.getDILBarcodeList().add("6683DILOP01A");
            testContext.setNumberOfRequiredAmplification(1);
            testContext.getDILABarCodeList().add("6683DILOP02A");
*/

            //ABove code is written for temporary basis


            selectMenu(teaDropdown, btnPCRPlateStamping);
            //-
            //verifyAssociatePCRBarcodeIsAvailableInReadyPlatesOfPlateStamping(testContext.getDestinationPCRPlate());
            //-
            driver.pauseExecutionFor(4000);
            int sizeOfDILABarcodelist = testContext.getDILABarCodeList().size();
            int count = 0;
            if (testContext.getNumberOfRequiredAmplification() <= 16 && sizeOfDILABarcodelist > 1) {
                for (int i = 1; i < sizeOfDILABarcodelist; ++i) {
                    driver.clickLocator(addSinglePlate);
                }

                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);

                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getDILABarCodeList().get(count));
                    count++;
                }
                driver.pauseExecutionFor(4000);
            } else if (testContext.getNumberOfRequiredAmplification() > 16) {
                for (int i = 1; i < testContext.getDILBarcodeList().size(); ++i) {
                    driver.clickLocator(addSinglePlate);
                }
                List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
                for (WebElement element : listOfInputContainerField) {
                    scanBarcode(element, testContext.getDILBarcodeList().get(count));
                    count++;
                }
                driver.pauseExecutionFor(6000);
            } else {
                scanBarcode(inputContainerBarcode, testContext.getDILABarCodeList().get(0));
            }

            driver.pauseExecutionFor(10000);
            List<WebElement> PCRBarcodeList = driver.findElements(PCRBarcode);
            List<WebElement> destinationContainerFieldList = driver.findElements(associatedDestinationContainerBarCodeFields);
            ArrayList<String> updatedPCRBarcodeList = new ArrayList<>();

            int sizeOfPCRElements = PCRBarcodeList.size();
            driver.clickLocator(tabUseStandardTemplate);
            for (int i = 0; i < sizeOfPCRElements; ++i) {
                updatedPCRBarcodeList.add(driver.getText(PCRBarcodeList.get(i)));
            }

            count = 0;
            if (PCRBarcodeList.size() == 1) {
                scanBarcode(associatedDestinationContainerBarCodeField, updatedPCRBarcodeList.get(0));
            } else {
                for (WebElement element : destinationContainerFieldList) {
                    scanBarcode(element, updatedPCRBarcodeList.get(count));
                    count++;
                }
            }

            driver.pauseExecutionFor(4000);
            //--
            //verifyBarcodeIsPresentInPrintLabel(testContext.getDestinationPCRPlate());
            //--
            scrollAndClickSubmit();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("TEA PCR Plate Stamping failed due to " + e);
        }
        Log.info("TEA PCR Plate Stamping Submitted");
        return this;
    }


    public TEA TEAPCRPlateThermocycling() {
        Log.info(OPERATING+SPACE+TEA_PCR_PLATE_THERMOCYCLING);
        try {
            //int noOfBarcodes=2;
            testContext.getDestinationPCRPlate().add("6702PCR01I9R01");
            testContext.getDestinationPCRPlate().add("6702PCR01I9R02");
            // testContext.getDestinationPCRPlate().add("6676PCR01OPR11");
            // Above code is used for temporary basis
            selectMenu(teaDropdown, btnPCRPlateThermocycling);
            driver.pauseExecutionFor(3000);
            int noOfBarcodes = testContext.getDestinationPCRPlate().size();

            for (int i = 1; i < noOfBarcodes; ++i) {
                driver.clickLocator(addSinglePlate);
            }
            driver.pauseExecutionFor(2000);

            int count = 0;
            List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
            for (WebElement element : listOfInputContainerField) {
                scanBarcode(element, testContext.getDestinationPCRPlate().get(count));
                count++;
            }

            //driver.pauseExecutionFor(15000);
            //-
            // verifySubmitButtonDisabledForThermocycling(btnSubmit);
            //-

            driver.waitForLocatorToBeVisible(thermocyclerBarCodeInputField);
            driver.pauseExecutionFor(3000);
            List<WebElement> listOfThermocyclerInputField = driver.findElements(thermocyclerBarCodeInputField);
            for (WebElement element : listOfThermocyclerInputField) {
                /*driver.sendSlowKeys(element,ICYC34,10);
                driver.clickLocator(tabUseStandardTemplate);
                driver.pauseExecutionFor(4000);*/
                driver.pauseExecutionFor(2000);
                driver.waitForElementoBeClickable(element);
                driver.pauseExecutionFor(2000);
                driver.clickLocator(element);
                scanBarcode(element, ICYC34);
                driver.pauseExecutionFor(4000);
            }
            driver.waitForLocatorToBeDeleted(divPackingSpinner);
            //--
            verifyTransformSpecDisplayed(testContext.getDestinationPCRPlate());
            //--
            softAssert.assertAll();
            scrollAndClickSubmit();
        } catch (Exception e) {
            Assert.fail("TEA PCR Plate Thermocycling failed due to " + e);
        }
        Log.info("TEA PCR Plate Thermocycling submitted");
        return this;
    }


    public TEA TEARetention() {
        try {
            //testContext.getDILBBarCodeList().add("6696DILI901B");
            validateInvalidBarCode(); //Test Case #18506

            Log.info("TEA Retention operation started");
            selectMenu(btnTeaRetention);
            /*driver.pauseExecutionFor(7000);
            int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                driver.pauseExecutionFor(5000);

                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);*/
            scanBarcode(inputContainerBarcode, testContext.getDILBBarCodeList().get(0));
            scrollAndClickSubmit();

        } catch (Exception e) {
            Assert.fail("TEA Retention Failed due to " + e);
        }
        Log.info("TEA Retention Submitted");
        return this;
    }

    private void validateInvalidBarCode() throws Exception {
        selectMenu(btnTeaRetention);
        driver.waitForLocatorToBeVisible(inputContainerBarcode);
        scanBarcode(inputContainerBarcode, testContext.getDILBBarCodeList().get(0) + "INVALID");

        driver.waitForLocatorToBePresent(barcodeScanMessage);
        driver.pauseExecutionFor(2000);
        List<WebElement> barcodeScanMessageElements = driver.findElements(barcodeScanMessage);
        Assert.assertEquals("Invalid barcode scan message is not correct",
                "This plate is not in the IN bucket for this step. Please contact a supervisor.",
                barcodeScanMessageElements.get(0).getAttribute("innerHTML"));

        List<WebElement> barcodeScanHandElements = driver.findElements(barcodeScanHandIcon);
        Assert.assertTrue("Duplicate barcode error hand icon class did not match: ",
                barcodeScanHandElements.get(0).getAttribute("className").contains("twst-thumb-validation-icon-error"));
    }

    public TEA TEARevival() {
        try {
            Log.info("TEA Revival operation started");
            validateWrongBarCodeScanErrorMessageOnTeaRevival("INVALIDBARCODE"); //Test Case #18509

            selectMenu(btnTeaRevival);
            driver.pauseExecutionFor(7000);
           /* int noOfBarcodes = testContext.getPlatesBarCodeList().size();
            for (String barcode :
                    testContext.getPlatesBarCodeList()) {
                scanBarcode(inputContainerBarcode, barcode);
                driver.pauseExecutionFor(5000);

                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }*/
            scanBarcode(inputContainerBarcode, testContext.getDILBBarCodeList().get(0));
            scrollAndClickSubmit();

        } catch (Exception e) {
            Assert.fail("TEA Revival Failed due to " + e);
        }
        Log.info("TEA Revival Submitted");
        return this;
    }

    private void validateWrongBarCodeScanErrorMessageOnTeaRevival(String barcode) throws Exception {
        String expectedErrorMessage = "This plate is not in the IN bucket for this step. Please contact a supervisor.";

        selectMenu(btnTeaRevival);
        driver.waitForLocatorToBeVisible(inputContainerBarcode);
        scanBarcode(driver.findElement(inputContainerBarcode), barcode);

        driver.waitForLocatorToBePresent(barcodeScanMessage);
        WebElement barcodeScanMsgElement = driver.findElement(barcodeScanMessage);
        Assert.assertEquals("Invalid barcode message is not correct: ",
                expectedErrorMessage,
                barcodeScanMsgElement.getAttribute("innerHTML").trim());

        WebElement barcodeScanHandElement = driver.findElement(barcodeScanHandIcon);
        Assert.assertTrue("Duplicate barcode error hand icon class did not match: ",
                barcodeScanHandElement.getAttribute("className").contains(invalidHandIconClass));

        verifySubmitButtonIsDisabledForMMCreation(btnSubmit);
    }

    public void scrollAndClickSubmit() throws Exception {
        Log.info("Clicking on Submit button");
        driver.scrollDownByJS(2000);
        driver.pauseExecutionFor(3000);
        driver.waitForLocatorToBeVisible(btnSubmit);
        driver.clickLocatorByJS(btnSubmit);
        driver.waitForLocatorToBeVisible(stepFinishedText);
        driver.pauseExecutionFor(5000);
        Log.info("Submitted successfully");
    }

    public void selectMenu(By subMenu) throws Exception {
        driver.waitForLocatorToBeVisible(teaDropdown, 60);
        driver.clickLocator(teaDropdown);
        driver.clickLocator(subMenu);
        driver.pauseExecutionFor(2000);
    }

    public void scanBarcode(By locator, String barcode) throws Exception {
        driver.waitForLocatorToBeVisible(locator);
        driver.pauseExecutionFor(3000);
        driver.sendKeys(locator, barcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info("Barcode scanned successfully");
    }

    public void scanBarcode(WebElement locator, String barcode) throws Exception {
        driver.waitForLocatorToBeVisible(locator);
        driver.pauseExecutionFor(3000);
        driver.sendKeys(locator, barcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        driver.pauseExecutionFor(3000);
        Log.info(barcode + "    scanned successfully");
    }

    public void scanDILADILBBarcode(By dilALocator, By dilBLocator, String dilABarcode, String dilBbarcode) throws Exception {
       /* driver.waitForLocatorToBeVisible(dilALocator);
        driver.waitForLocatorToBeVisible(dilBLocator);*/
        driver.pauseExecutionFor(3000);
        driver.sendKeys(dilALocator, dilABarcode + Keys.TAB);
        driver.pauseExecutionFor(4000);
        driver.sendKeys(dilBLocator, dilBbarcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info("Barcode DILA and DILB scanned successfully");
    }

    /*  public void scanDILADILBBarcode(By dilALocator, By dilBLocator, String dilABarcode, String dilBbarcode) throws Exception {
     *//* driver.waitForLocatorToBeVisible(dilALocator);
        driver.waitForLocatorToBeVisible(dilBLocator);*//*
        driver.pauseExecutionFor(3000);
        driver.sendKeys(dilALocator, dilABarcode + Keys.TAB);
        driver.pauseExecutionFor(4000);
        driver.sendKeys(dilBLocator, dilBbarcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info("Barcode DILA and DILB scanned successfully");
    }*/

    public void scanPosition(String position) throws InterruptedException {
        driver.pauseExecutionFor(1000);
        driver.copyTextToClipboard(IDSP64_0 + (Integer.parseInt(position) + 1));
        driver.pauseExecutionFor(1000);
        driver.paste();
        driver.pauseExecutionFor(1000);
    }

    public void scanDestinationDILPlates(String DILA, String DILB) throws Exception {
        driver.pauseExecutionFor(4000);
        driver.copyTextToClipboard(IDSP64_0 + 2);
        driver.paste();
        driver.pauseExecutionFor(2000);
        driver.copyTextToClipboard(DILA);
        driver.paste();
        driver.pauseExecutionFor(5000);
        driver.clickLocator(btnClose);
        driver.pauseExecutionFor(2000);
        driver.copyTextToClipboard(IDSP64_0 + 3);
        driver.paste();
        driver.pauseExecutionFor(2000);
        driver.copyTextToClipboard(DILB);
        driver.paste();
        driver.pauseExecutionFor(5000);
    }


    //TestCaseId:C18737
    public void verifyPlateProcessingText(String plateProcessingexpectedText, int noOfRequiredAmplification) {
        Log.info("Verifying the Plate Processing Text");
        if (noOfRequiredAmplification > 16) {
            softAssert.assertEquals(plateProcessingexpectedText, "Plate is required for processing");
        } else if (noOfRequiredAmplification <= 16) {
            softAssert.assertEquals(plateProcessingexpectedText, "No - Place in storage");
        }
    }

    public void verifyContentOfDILPlate(String DILA, String DILB) {
        Assert.assertTrue(DILA.contains(DIL));
        Assert.assertTrue(DILB.contains(DIL));
        Assert.assertTrue(DILA.contains(A));
        Assert.assertTrue(DILB.contains(B));
    }

    public int getClusterCount(String text) {
        String[] strArray = text.split(" ");
        int clusterCount = 0;
        for (int i = strArray.length - 1; i >= 0; --i) {
            clusterCount = Integer.parseInt(strArray[i]);
            break;
        }
        return clusterCount;
    }


    //TestCaseId:C18454
    public void verifySubmitButtonDisableUntilScanAllBarcode(By submitButton, By inputContainerBarcodeField, ArrayList<String> updatedList) {
        try {
            String attrValueOfSubmitButton = driver.getAttribute(submitButton, "class");

            if (updatedList.size() == 1) {
                Assert.assertTrue("Submit button is enabled", attrValueOfSubmitButton.contains("disabled-button"));
            } else {
                scanBarcode(inputContainerBarcodeField, updatedList.get(0));
                Assert.assertTrue("Submit button is enabled", attrValueOfSubmitButton.contains("disabled-button"));
            }

        } catch (Exception e) {
            Log.info("Submit button is enabled due to" + e.getMessage());
            Assert.fail();
        }
    }

    public void verifyViewedSavedSpecRecordAnotherStepButtonVisible(By ViewSavedSpec, By ProceedToNextStep, By RecordAnootherStep) {
        try {
            Assert.assertTrue(driver.IsLocatorVisible(ViewSavedSpec));
            Assert.assertTrue(driver.IsLocatorVisible(ProceedToNextStep));
            Assert.assertTrue(driver.IsLocatorVisible(RecordAnootherStep));

        } catch (Exception e) {
            Log.info("Button is not visible due to" + e.getMessage());
            Assert.fail();
        }
    }

    // TestCaseId: C18452
    public void verifyCountOfContainerInputField(List<WebElement> listOfPCHPxBarcode, List<WebElement> listOfContainerField) {
        if (listOfPCHPxBarcode.size() == listOfContainerField.size()) {
            Log.info("Size of Input container field is same as barcode that is associated with SRN checkBox");
        } else {
            Log.info("Size didn't match as per SRN CheckBox");
            Assert.fail();
        }
    }

    //TestCaseId:C18487
    public void verifyAllsrnCheckBoxIsChecked(By selectAllButton, List<WebElement> listOfPCHPxBarcode, List<WebElement> listOfContainerField) {
        try {
            driver.clickLocator(selectAllButton);
            driver.pauseExecutionFor(4000);
            verifyCountOfContainerInputField(listOfPCHPxBarcode, listOfContainerField);
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    //TestCaseId: C18486
    public void verifyDILPlateIsStartingWithSRNExcludingZero(List<String> listOfDILBarcode) {
        Log.info("Verifying DIL plate is starting with SRN Number");
        ArrayList listFromSet = new ArrayList(testContext.getSrnSet());
        String srnNumber = listFromSet.get(0).toString().split("SRN_00")[1];
        for (int i = 0; i < listOfDILBarcode.size(); ++i) {
            /*if (listOfDILBarcode.get(i).contains(srnNumber)) {
                Log.info("DIL Barcode is starting with associated SRN Number");
            } else {
                Log.info("SRN Number mis-matching for DIL plate");
                softAssert.assertTrue(false);*/
            softAssert.assertTrue(listOfDILBarcode.get(i).contains(srnNumber),"DIL Barcode is starting with associated SRN Number");

            }

        }





    //TestCaseId: C18503
    public void verifySubmitButtonIsDisabledForMMCreation(By submitButton) {
        try {
            WebElement element = driver.findElement(submitButton);
            softAssert.assertTrue( element.getAttribute("className").contains("twst-disabled-button"),"Submit button is enabled");

        } catch (Exception e) {
            Log.info("Submit button is enabled due to" + e.getMessage());
            softAssert.fail();
        }
    }

    //TestCaseId: C18735
    public void verifyMMXBarcodeIsStartingWithMMXAndCurrentExeDate(ArrayList<String> listOfMMXBarcode) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate;

        try {
            currentDate = format1.format(date);
            String[] strArray = currentDate.split("-");
            String newDate = "MMX";

            for (int i = 0; i < strArray.length; ++i) {
                newDate = newDate + strArray[i];
            }

            String MMXCurrentDate = newDate.substring(5, 11);
            for (int i = 0; i < listOfMMXBarcode.size(); ++i) {
                if (listOfMMXBarcode.get(i).contains("MMX" + "211130")) {
                    Log.info("MMX barcode is starting with MMX and current execution date");
                } else {
                    Log.info("MMX barcode is existing with wrong format");
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            softAssert.fail("MMX barcode is existing with wrong format");

        }
    }

    //TestCaseId:C18736
    public void verifyPCRBarcodeIsStartedWithNonZeroSRNNumberandPCR(ArrayList<String> listOfPCRBarcode) {
        Log.info("Verifying PCR barcode is staring with Non Zero SRN and followed by PCR");
        ArrayList listFromSet = new ArrayList(testContext.getSrnSet());
        //String srnNumber = listFromSet.get(0).toString().split("_")[1].replaceAll("0", "");
        String srnNumber = listFromSet.get(0).toString().split("SRN_00")[1];

        for (int i = 0; i < listOfPCRBarcode.size(); ++i) {
            if (listOfPCRBarcode.get(i).contains(srnNumber + "PCR")) {
                Log.info("PCR barcode is starting with SRN number and PCR");
            } else {
                Log.info("PCR Barcode is not starting with SRN number and PCR");
                softAssert.fail();
            }
        }
    }

    public void verifAllSRNCheckBoxIsDeselected(List<WebElement> listOfElement) throws Exception {
        Log.info("Verifying all SRN checkbox  is deselected");
        driver.clickLocator(btnSelectAll);
        Log.info("All input container fields are opened");
        driver.clickLocator(btnSelectNone);
        for (int i = 0; i < listOfElement.size(); ++i) {
            if (driver.IsLocatorVisible(listOfElement.get(i))) {
                Assert.fail("Input container didn't disappear");
            } else {
                Log.info("Input container fields are disappear");
            }
        }
    }

    //TestCaseId: C18545
    public void verifySubmitButtonDisabledForThermocycling(By submitButton) throws Exception {
        Log.info("Verifying Submit button is disabled");
        WebElement element = driver.findElement(submitButton);
        //driver.scrollDownByJS(2000);
        String ss = driver.getAttribute(element, "class");
        /*if (element.isEnabled()) {
            Assert.fail("Submit button is not disabled");
        } else {
            Log.info("Submit button is disabled.");

        }*/

        /*if(ss.contains("disabled")){
            Log.info("Submit button is disabled.");

        }else{
            Log.info("Submit button is enabled");
            Assert.fail();
        }*/
        try {
            softAssert.assertTrue(ss.contains("disabled"),"Submit button is not disabled");
        } catch (Exception e) {
            softAssert.fail();
        }
    }

    // TestCaseId: C18470
    public void verifypCHPxbarcodeIsAvailableInReadyPlates(List<String> listOfPCHPxBarcode) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        Log.info("Verifying pCHPx barcodes are available in Ready plates of Create Daughter plates");
        driver.clickLocator(btnReadyPlateQueue);
        driver.pauseExecutionFor(7000);
        for (int i = 0; i < listOfPCHPxBarcode.size(); ++i) {
            if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfPCHPxBarcode.get(i)))) {
                Log.info("All pCHPx barcode found in ready plates queue");
            } else {
                softAssert.fail("Could not find pCHPx barcode in ready plates queue");
            }
        }
        driver.clickLocator(btnCloseReadyPlates);
    }

    // TestCaseId:C18513
    public void verifyAssociatePCRBarcodeIsAvailableInReadyPlatesOfPlateStamping(List<String> listOfPCRBarcodes) throws Exception {
        Log.info("Verifying PCR barcode is available in Ready plates of Plate Stamping");
        driver.clickLocator(btnReadyPlateQueue);
        driver.pauseExecutionFor(7000);
        for (int i = 0; i < listOfPCRBarcodes.size(); ++i) {
            if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfPCRBarcodes.get(i)))) {
                Log.info("All PCR barcode found in ready plates queue");
            } else {
                softAssert.fail("Could not find PCR barcode in ready plates queue");
            }
        }
        driver.clickLocator(btnCloseReadyPlates);
    }

    //TestCaseId: C18540
    public void verifyPCRbarcodesIsAvailableInReadyOfTEPPoolingPlatesAfterThermocycling(List<String> listOfPCRBarcodes) throws Exception {
        Log.info("Verifying PCR barcode is available in Ready plates TEP Pooling Planing");
        driver.clickLocator(btnReadyPlateQueue);
        driver.pauseExecutionFor(7000);
        for (int i = 0; i < listOfPCRBarcodes.size(); ++i) {
            if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfPCRBarcodes.get(i)))) {
                Log.info("All PCR barcode found in ready plates queue of TEP Pooling Planning");
            } else {
                Assert.fail("Could not find PCR barcode in ready plates queue in TEP Pooling Planning");
            }
        }
        driver.clickLocator(btnCloseReadyPlates);
    }

    // TestCaseId: C18473
    public void verifyDILBPlateExistOnRetentionOnDaughterPlatesSubmission(int requiredAmplification, List<String> listOfDILBPlates) {
       Log.info("Verifying DILB plate exists on TEA Retention Step after submission of   " + TEA_CREATE_DAUGHTER_PLATES);
        try {
            if (requiredAmplification <= 16) {
                driver.pauseExecutionFor(3000);
                selectMenu(teaDropdown, btnTeaRetention);
                driver.pauseExecutionFor(2000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(7000);
                for (int i = 0; i < listOfDILBPlates.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILBPlates.get(i)))) {
                        Log.info("All DILB barcode found in ready plates queue");
                    } else {
                        softAssert.fail("Could not find DILB barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);
            } else {
                Log.info("Number of required amplification is greater than 16");
            }

        } catch (Exception e) {

        }
    }

    //TestCaseId: C18481
    public void verifyDILAPlateExistOnPlateStampingAfterMMCreationionSubmission(int requiredAmplification, List<String> listOfDILABarcode) {
     Log.info("DILA plate exist on ready plates queue of Plate Stamping after submission of MM Creation steps");
        try {
            if (requiredAmplification <= 16) {
                selectMenu(teaDropdown, btnPCRPlateStamping);
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfDILABarcode.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILABarcode.get(i)))) {
                        Log.info("All DILA barcode found in ready plates queue");
                    } else {
                        softAssert.fail("Could not find DILA barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } else {
                Log.info("Number of required amplification is greater than 16");
            }

        } catch (Exception e) {
            Assert.fail("DILA doesn't exist");

        }
    }

    //TestCaseId: C18481
    public void verifyMMXBarcodeExistOnMMDispensingAfterMMCreationSubmission(int requiredAmplification, List<String> listOfMMXBarcode) {
       Log.info("Verifying MMX barcode exist in ready plates of MM dispensing");
        try {
            if (requiredAmplification <= 16) {
                selectMenu(teaDropdown, btnMMDispensing);
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfMMXBarcode.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfMMXBarcode.get(i)))) {
                        Log.info("All MMX barcode found in ready plates queue");
                    } else {
                        softAssert.fail("Could not find MMX barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } else {
                Log.info("Number of required amplification is greater than 16");
            }

        } catch (Exception e) {

        }

    }

    //C18520
    public void verifyFormatOfDILBarcode(Set<String> srnNumber, List<String> listOfDILABarcode, String plateType) {
        Log.info("Verifying the format of" + plateType + "barcode");
        ArrayList listFromSet = new ArrayList(srnNumber);
        String SrnNumber = (String) listFromSet.get(0);
        String dil = SrnNumber.split("SRN_00")[1] + DIL;
        //String plateType = plateBarcode.substring(plateBarcode.length()-1);
        // System.out.println(plateBarcode.substring(0, 7));
        for (int i = 0; i < listOfDILABarcode.size(); ++i) {
            boolean regExMatch = Pattern.compile("^[A-Z]{1}[0-9]{3}").matcher(listOfDILABarcode.get(i).substring(listOfDILABarcode.get(i).length() - 5, listOfDILABarcode.get(i).length() - 1)).matches();
            if (listOfDILABarcode.get(i).length() == 12) {
                if (regExMatch && (dil.matches(listOfDILABarcode.get(i).substring(0, 7))) &&
                        plateType.matches(listOfDILABarcode.get(i).substring(listOfDILABarcode.get(i).length() - 1))) {
                    Log.info("Format for DIL barcode is as expected");
                } else {
                    softAssert.fail("Incorrect format for DIL Barcode");
                }
            } else {
                softAssert.fail("Incorrect DIL Barcode length");
            }
        }
    }

    //C18748
    public void verifyFormatOfpCHPxBarcode(List<String> listOfpCHPxbarcode) {
        Log.info("Verifying format and length of pCHPx barcode");

        for (int i = 0; i < listOfpCHPxbarcode.size(); ++i) {
            if (listOfpCHPxbarcode.get(i).length() == 18) {
                if (pCHPx.matches(listOfpCHPxbarcode.get(i).substring(0, 5))) {
                    Log.info("Format for pCHPx barcode  as expected");
                } else {
                    softAssert.fail("Format for pCHPx is incorrect");
                }
            } else {
                softAssert.fail("Length of pCHPx barcode is incorrect");
            }
        }
    }

    //C18501
    public void verifyFormatOfMMXBarcode(List<String> listOfMMXBarcode) {
        Log.info("Verifying MMX barcode format");
        DateFormat dateFormat = new SimpleDateFormat(YYMMDD);
        String expectedBarcode = MMX + dateFormat.format(new Date());
        for (int i = 0; i < listOfMMXBarcode.size(); ++i) {
            boolean regExMatch = Pattern.compile("^[A-Z]{1}[0-9]{4}").matcher(listOfMMXBarcode.get(i).substring(listOfMMXBarcode.get(i).length() - 5, listOfMMXBarcode.get(i).length())).matches();
            //System.out.println(mmxBarcode.substring(0, 9));
            if (listOfMMXBarcode.get(i).length() == 14) {
                if (regExMatch && (expectedBarcode.matches(listOfMMXBarcode.get(i).substring(0, 9)))) {
                    Log.info("Format of MMX Barcode as expected");
                } else {
                    softAssert.fail("Incorrect format of MMX Barcode");
                }
            } else {
                softAssert.fail("Incorrect Barcode length of MMX Barcode");
            }
        }
    }

    //C18746
    public void verifyFormatOfPCRBarcode(Set<String> srnSet, List<String> listOfMMXBarcode) {
        Log.info("Verifying the format of MMX barcode");
        ArrayList listFromSet = new ArrayList(srnSet);
        String SrnNumber = (String) listFromSet.get(0);
        String pcr = SrnNumber.split("SRN_00")[1] + PCR;

        for (int i = 0; i < listOfMMXBarcode.size(); ++i) {
            // System.out.println(pcrBarcode.substring(0, 7));
            boolean regExMatch = Pattern.compile("^[0-9]{2}[A-Z]{1}[0-9]{1}[A-Z]{1}[0-9]{2}").matcher(listOfMMXBarcode.get(i).substring(listOfMMXBarcode.get(i).length() - 7, listOfMMXBarcode.get(i).length())).matches();
            if (listOfMMXBarcode.get(i).length() == 14) {
                if (regExMatch && (pcr.matches(listOfMMXBarcode.get(i).substring(0, 7)))) {
                    Log.info("Format for PCR barcode as expected");
                } else {
                    softAssert.fail("Incorrect format of PCR Barcode");
                }
            } else {
                softAssert.fail("Incorrect Barcode length for PCR Barcodde");
            }
        }
    }

    //TestCaseId: C18495
    public void verifySuccessfulSubmissionMessage(String TEAStepsName) {
        Log.info("Verifying successfully submission of " + TEAStepsName);
        String xpath = String.format(labelTextForSuccessfulSubmission, TEAStepsName);
        if (driver.IsLocatorVisible(By.xpath(xpath))) {
            Log.info(TEAStepsName + "submitted successfully");
        } else {
            softAssert.fail(TEAStepsName + "didnt submit successfully");
        }
    }

    //TestCaseId: C18522
    public void verifyPCRPlateExistOnReadyPlatesOfPlateStamping(List<String> listOfPCRBarcode) {
        Log.info("Verifying PCR barcodes is exist on Ready plates of PCR Plate Thermocycling");
        try {
            selectMenu(teaDropdown, btnPCRPlateThermocycling);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnReadyPlateQueue);
            driver.pauseExecutionFor(5000);
            for (int i = 0; i < listOfPCRBarcode.size(); ++i) {
                if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfPCRBarcode.get(i)))) {
                    Log.info("All" + PCR + "barcode found in ready plates queue");
                } else {
                    Assert.fail("Could not find" + PCR + "barcode in ready plates queue");
                }
            }
            driver.clickLocator(btnCloseReadyPlates);

        } catch (Exception e) {
            Assert.fail("Failed due to " + e.getMessage());
        }
    }

    //TestCaseId: C18526
    public void verifyBarcodeIsPresentInPrintLabel(List<String> listOfBarcode) {
        Log.info("Verifying barcode is present in Print label");
        for (int i = 1; i <= listOfBarcode.size(); ++i) {
            String barcode = driver.getText(By.xpath("(//span[@ng-if='label.displayBarcode'])[" + i + "]"));
            if (barcode.contains(listOfBarcode.get(i))) {
                Log.info("Barcode found in print label");
            } else {
                softAssert.fail("Barcode is not found in print label");
            }
        }
    }


    //TestCaseId: C18528
    public void verifyDILBarcodeMovedIntoReadyPlatesOfRetention(int numberOfRequiredAmplification, List<String> listOfDILAPlate, List<String> listOfDILAandBPlates) {
        if (numberOfRequiredAmplification <= 16) {
            try {
                selectMenu(teaDropdown, btnTeaRetention);
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfDILAPlate.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILAPlate.get(i)))) {
                        Log.info("All" + DIL + A + "barcode found in ready plates queue");
                    } else {
                        Assert.fail("Could not find" + DIL + A + "barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } catch (Exception e) {
                Assert.fail("Failed due to " + e.getMessage());
            }

        } else {
            try {
                selectMenu(teaDropdown, btnTeaRetention);
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfDILAandBPlates.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILAandBPlates.get(i)))) {
                        Log.info("All" + DIL + A + "and" + DIL + B + "barcode found in ready plates queue of TEA Retention");
                    } else {
                        Assert.fail("Could not find" + DIL + A + "and" + DIL + B + "barcode in ready plates queue of TEA Retention");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } catch (Exception e) {
                Assert.fail("Failed due to " + e.getMessage());
            }
        }
    }

    //TestCaseId: C18529
    public void verifyErrorMessage() throws Exception {
        String errorMessage = driver.getText(txtFullErrorMessage);
        if (driver.IsLocatorVisible(txtServerError)) {
            driver.clickLocator(btnShowErrorMessage);
            if (errorMessage.contains(STAMPING_ERROR_MESSAGE)) {
                Log.info("Error message verified");
            } else {
                Assert.fail("Error message not found");
            }
        }
    }

    //TestCaseId: C18510
    public void verifyDILBPlatesExistOnMMCreationAfterRevivalSubmissioin(int numberOfRequiredAmplification, List<String> listOFDILBPlate) {
        if (numberOfRequiredAmplification <= 16) {
            try {
                selectMenu(teaDropdown, btnTeaMmCreation);
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOFDILBPlate.size(); ++i) {
                    if (driver.IsLocatorVisible(barcodeInReadyPlate.format(listOFDILBPlate.get(i)))) {
                        Log.info("All" + DIL + B + "barcode found in ready plates queue");
                    } else {
                        Assert.fail("Could not find" + DIL + B + "barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } catch (Exception e) {
                Assert.fail("Failed due to " + e.getMessage());
            }

        } else {
            Log.info("Required amplification is greater than 16.");
        }

    }

    //TestCaseId: C18455
    public void verifyAllBarcodeExistOnSuspensioinReadyPlates() {
        try {
            driver.clickLocator(btnSelectAll);
            List<WebElement> listOfBarcode = driver.findElements(listOfpCHPxBarcode);
            int sizeOfReadyPlates = Integer.parseInt(driver.getText(txtCountOfReadyPlateBarcode));
            Assert.assertEquals("pHCPx Barcode count not matched", listOfBarcode.size(), sizeOfReadyPlates);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    //TestCaseId: C18527
    public void verifyDILPlateNotExistInReadyPlatesAfterSubmissionOfStamping(int numberOfRequiredAmplification, List<String> listOfDILAPlate, List<String> listOfDILAandBPlates) {
        if (numberOfRequiredAmplification <= 16) {
            try {
                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfDILAPlate.size(); ++i) {
                    if (!driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILAPlate.get(i)))) {
                        Log.info("All" + DIL + A + "barcode not found in ready plates queue");
                    } else {
                        Assert.fail(DIL + A + "barcode in ready plates queue");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } catch (Exception e) {
                Assert.fail("Failed due to " + e.getMessage());
            }

        } else {
            try {

                driver.pauseExecutionFor(3000);
                driver.clickLocator(btnReadyPlateQueue);
                driver.pauseExecutionFor(5000);
                for (int i = 0; i < listOfDILAandBPlates.size(); ++i) {
                    if (!driver.IsLocatorVisible(barcodeInReadyPlate.format(listOfDILAandBPlates.get(i)))) {
                        Log.info("All" + DIL + A + "and" + DIL + B + "barcode not found in ready plates queue of TEA Retention");
                    } else {
                        Assert.fail(DIL + A + "and" + DIL + B + "barcode found in ready plates queue of TEA Retention");
                    }
                }
                driver.clickLocator(btnCloseReadyPlates);

            } catch (Exception e) {
                Assert.fail("Failed due to " + e.getMessage());
            }
        }
    }

    //TestCaseId: C18541
    public void verifyTransformSpecDisplayed(List<String> listOfPCRBarcode) {
        try {
            Log.info("Verifying the transform Spec Details");
            driver.clickLocator(checkBoxTransformSpec);
            driver.pauseExecutionFor(3000);
            String sourcePlateBarcode = driver.getText(transformSpecTableSourcePlate);
            String destinationPlateBarcode = driver.getText(transformSpecTableDestinationPlate);
            /*Assert.assertTrue("Download plan in excel format is not available", driver.IsLocatorVisible(linkDownloadBlankTransformTemplate));
            Assert.assertEquals("Source plate barcode didn't match", listOfPCRBarcode.get(0), sourcePlateBarcode);
            Assert.assertEquals("Destination plate barcode didn't match", listOfPCRBarcode.get(0), destinationPlateBarcode);*/
            softAssert.assertTrue(driver.IsLocatorVisible(linkDownloadBlankTransformTemplate),"Download plan in excel format is not available");
            softAssert.assertEquals(listOfPCRBarcode.get(0), sourcePlateBarcode,"Source plate barcode didn't match");
            softAssert.assertEquals(listOfPCRBarcode.get(0), destinationPlateBarcode,"Destination plate barcode didn't match");

        } catch (Exception e) {
            softAssert.fail();
        }
    }

    //TestCaseId: C18660
    public void verifyHamiltonAndFlipjigCanNotMix() {
        Log.info("Verifying flip jig plates and Hamilton plates can not mixed");
        try {
            driver.clickLocator(firstFlipJigCheckBox);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(firstHamiltonPlateCheckBox);
            if (!driver.IsCheckBoxSelected(firstFlipJigCheckBox)) {
                Log.info("Filp Jig SRN CheckBox has been unchecked");
            } else {
                Assert.fail("Flip jig SRN Check Box still checked, it should be unchecked");
            }

            if (driver.IsCheckBoxSelected(firstHamiltonPlateCheckBox)) {
                driver.clickLocator(firstFlipJigCheckBox);
                driver.pauseExecutionFor(3000);
                if (!driver.IsCheckBoxSelected(firstHamiltonPlateCheckBox)) {
                    Log.info("Hamilton SRN CheckBox has been unchecked");
                } else {
                    Assert.fail("Hamilton SRN Check Box still checked, it should be unchecked");
                }
            } else {
                Assert.fail("Hamilton SRN CheckBox was not selected");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //TestCaseId: C18530
    public void validateErrorMessageForPlateStamping() throws Exception {
        Log.info("Verifying the error message using PCR barcode for Plate Stamping");
        try {
            driver.waitForLocatorToBeVisible(inputContainerBarcode);

            scanBarcode(inputContainerBarcode, testContext.getDestinationPCRPlate().get(0));

            driver.waitForLocatorToBePresent(barcodeScanMessage);
            driver.pauseExecutionFor(2000);
            String errorMessage = driver.getText(barcodeScanMessage);

            Assert.assertEquals("Displayed error message is not as expected",
                    STAMPING_ERROR_MESSAGE2, errorMessage);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateDuplicateBarcodeScan() throws Exception {
        selectMenu(btnTeAReSuspension);
        driver.pauseExecutionFor(3000);

        driver.waitForLocatorToBeVisible(srnWithMoreThanOneBarCode);
        String srnNumber = driver.getText(srnWithMoreThanOneBarCode);
        chkBox = String.format(chkBox, srnNumber);
        driver.clickLocatorByJS(By.xpath(chkBox));

        driver.waitForLocatorToBeVisible(listOfpCHPxBarcode);
        List<WebElement> listOfBarcode = driver.findElements(listOfpCHPxBarcode);
        String barcode = listOfBarcode.get(0).getText();

        List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
        if (listOfInputContainerField.size() > 1) {
            scanBarcode(listOfInputContainerField.get(0), barcode);
            scanBarcode(listOfInputContainerField.get(1), barcode);

            driver.waitForLocatorToBePresent(barcodeScanMessage);
            List<WebElement> barcodeScanMsgElements = driver.findElements(barcodeScanMessage);
            Assert.assertEquals("Valid barcode message is not correct: ", "",
                    barcodeScanMsgElements.get(0).getAttribute("innerHTML").trim());

            List<WebElement> barcodeScanHandElements = driver.findElements(barcodeScanHandIcon);
            Assert.assertTrue("Duplicate barcode error hand icon class did not match: ",
                    barcodeScanHandElements.get(0).getAttribute("className").contains(validHandIconClass));

            barcodeScanMsgElements = driver.findElements(barcodeScanMessage);
            Assert.assertEquals("Duplicate barcode error message is not correct: ", "A container with barcode <strong>" +
                            barcode + "</strong> is already a source container.",
                    barcodeScanMsgElements.get(1).getAttribute("innerHTML"));

            barcodeScanHandElements = driver.findElements(barcodeScanHandIcon);
            Assert.assertTrue("Duplicate barcode error hand icon class did not match: ",
                    barcodeScanHandElements.get(1).getAttribute("className").contains(invalidHandIconClass));
        }
    }

    private void validateSRNBoxCheckAndUncheck(String srnNumber) throws Exception {
        selectMenu(btnTeAReSuspension);
        driver.pauseExecutionFor(3000);

        chkBox = String.format(chkBox, srnNumber);
        driver.clickLocatorByJS(By.xpath(chkBox));
        driver.waitForLocatorToBeVisible(inputContainerBarcode);

        List<WebElement> listOfInputContainerField = driver.findElements(inputContainerBarcode);
        Assert.assertTrue("Input fields should be present: ", listOfInputContainerField.size() > 0);

        driver.clickLocatorByJS(By.xpath(chkBox));
        driver.waitForLocatorToBeDeleted(inputContainerBarcode);

        listOfInputContainerField = driver.findElements(inputContainerBarcode);
        Assert.assertEquals("Input fields should not be present: ", 0, listOfInputContainerField.size());
    }

    private void validateDILScanErrorMessageAsPerAmplificationLevel(String dilABarcode, String dilBBarcode) throws Exception {
        Log.info("Validating the error message when required amplification is less than and equal to 16");
        String expectedErrorMessage = "400 Bad Request: DILA " + dilABarcode + " has material for the required order and is available in PCR Stamping bucket";
        if (testContext.getNumberOfRequiredAmplification() <= 16) {
            expectedErrorMessage = "This container is not an expected source container.";
        }

        selectMenu(teaDropdown, btnTeaMmCreation);

        driver.waitForLocatorToBeVisible(inputContainerBarcode);
        scanBarcode(driver.findElement(inputContainerBarcode), dilBBarcode);

        driver.waitForLocatorToBePresent(barcodeScanMessage);
        WebElement barcodeScanMsgElement = driver.findElement(barcodeScanMessage);
        Assert.assertEquals("Invalid barcode message is not correct: ",
                expectedErrorMessage,
                barcodeScanMsgElement.getAttribute("innerHTML").trim());

        WebElement barcodeScanHandElement = driver.findElement(barcodeScanHandIcon);
        Assert.assertTrue("Duplicate barcode error hand icon class did not match: ",
                barcodeScanHandElement.getAttribute("className").contains(invalidHandIconClass));

        verifySubmitButtonIsDisabledForMMCreation(btnSubmit);
    }

    private void validateReadyPlatesQueue() throws Exception {
        driver.waitForLocatorToBeVisible(inputContainerBarcode);

        driver.waitForLocatorToBeVisible(btnReadyPlatesQueue);
        driver.clickLocator(btnReadyPlatesQueue);
        List<String> dilPlates = testContext.getDILABarCodeList();
        driver.waitForLocatorToBeDeleted(readyPlateLoadingSpinner);
        driver.waitForLocatorToBePresent(readyPlateTable);

        if (testContext.getNumberOfRequiredAmplification() <= 16) {
            if (dilPlates != null) {
                for (String dilA : dilPlates) {
                    Assert.assertTrue("DILA plate: " + dilA + " is not present", driver.IsLocatorVisible(By.xpath("//a[text()='" + dilA + "']")));
                }

                dilPlates = testContext.getDILBBarCodeList();
                for (String dilB : dilPlates) {
                    Assert.assertFalse("DILA plate: " + dilB + " is present", driver.IsLocatorVisible(By.xpath("//a[text()='" + dilB + "']")));
                }
            }
        } else {
            dilPlates = testContext.getDILBarcodeList();
            if (dilPlates != null) {
                for (String dil : dilPlates) {
                    Assert.assertTrue("DIL plate: " + dil + " is not present", driver.IsLocatorVisible(By.xpath("//a[text()='" + dil + "']")));
                }
            }
        }

        driver.clickLocatorByJS(btnCloseReadyPlates);
    }


}




