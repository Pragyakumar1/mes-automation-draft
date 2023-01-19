package com.twist.twistautomation.components.sampletracker.TEP;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.twist.twistautomation.constants.SampleTrackerMenuItems.*;

@Component
public class TEP extends SampleTrackerComponent {
    SoftAssert softAssert= new SoftAssert();
    private By hamiltonPlatesSRNCheckBox = By.xpath("//*[contains(text(),'Hamilton Plates')]//following::td[contains(text(),'%s')]");
    private By btnTEPPoolingPlanning = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-TE dropdown open']//a[@class='ng-binding'][normalize-space()='TEP Pooling Planning']");
    private By teaDropdown = By.id("single-button19");
    private By divPackingSpinner = By.xpath("//div[@class='twst-spinner-inner']");
    private By btnViewedSavedSpec = By.xpath("//button[contains(text(),'View Saved Spec')]");
    private By btnRecordAnotherStep = By.xpath("//button[contains(text(),'Record Another Step')]");
    private By btnPoolNow = By.xpath("//button[contains(text(),'Pool Now')]");
    private By btnStartHamiltonWizard = By.xpath("//button[contains(text(),'Start Hamilton Wizard')]");
    private By inputInstrumentBarcode = By.xpath("//input[@placeholder='Scan barcode']");
    private By btnSourceScanComplete = By.xpath("//button[contains(text(),'Source Scan Complete')]");
    private By bndhFlpBarcodelist = By.xpath("//td[contains(text(),'Destinations')]//following::span[contains(text(),'BNDH') or contains(text(),'FLP')]");
    private By btnTubePlacementComplete = By.xpath("//button[contains(text(),'Tube Placement Complete')]");
    private By btnDestinationScanComplete = By.xpath("//button[contains(text(),'Destination Scan Complete')]");
    private By btnDownloadWorkList = By.xpath("//span[contains(text(),'Download')]");
    private By btnRunFinished = By.xpath("//button[contains(text(),'Run Finished')]");
    private By addSinglePlate = By.xpath("//span[contains(text(),'Use standard template')]//following::button[2]");
    private By flpBarcodeList = By.xpath("//td[contains(text(),'Destinations')]//following::span[contains(text(),'FLP')]");
    private By associateDestinationContainerFLPBarCodeField = By.xpath("//p[contains(text(),'Destination Container Barcodes:')]//following::input[@type='text']");
    private By inputContainerBarcode = By.xpath("//input[@type='text']");
    private By headerHamiltonPlates = By.xpath("//h4[contains(text(),'Hamilton Plates')]");
    private By tabUseStandardTemplate = By.xpath("//span[contains(text(),'Use standard template')]");
    private By associatedDestinationContainerBarCodeField = By.xpath("//p[contains(text(),'Destination Container Barcode:')]//following::input[1]");
    private By txtTEPPoolingOnFlipjig = By.xpath("//h1[contains(text(),'TEP Pooling on Flipjig')]");
    private By btnClearSelection = By.xpath("//button[text()='clear selection']");

    public TEP TEPPoolingPlanning() {
        Log.info(OPERATING+SPACE+TEP_POOLING_PLANNING);
        try {
            //testContext.getDestinationPCRPlate().add("5763PCR14OPR01");
            //testContext.getDestinationPCRPlate().add("6672PCR01I9R01");
            //testContext.getDestinationPCRPlate().add("6678PCR01OPR03");
            //testContext.setClusterCount(32);
            // Above code is for temporary basis
            selectMenu(teaDropdown, btnTEPPoolingPlanning);
            //driver.waitForLocatorToBeDeleted(divPackingSpinner);
            driver.waitForLocatorToBeVisible(headerHamiltonPlates);
            driver.pauseExecutionFor(4000);
            String srnNumber = "//*[contains(text(),'%s')]//preceding::td[1]";
            String srnCheckBox = "//*[contains(text(),'%s')]";

            ArrayList listFromSet = new ArrayList(testContext.getSrnSet());
            for (int i = 0; i < listFromSet.size(); ++i) {
                srnCheckBox = String.format(srnCheckBox, listFromSet.get(i));
                driver.scrollTillElement(By.xpath(String.format(srnNumber, listFromSet.get(i))));
                driver.clickLocator(By.xpath(srnCheckBox));
            }
            driver.pauseExecutionFor(4000);
            scrollAndClickSubmit();
            if (testContext.getClusterCount() < 384) {
                TEPPoolingonHamilton();
            } else {
                TEPPoolingonFlipjig();
            }

        } catch (Exception e) {
            Assert.fail("TEP Pooling Planning failed due to" + e);
        }
        Log.info("TEP Pooling Planning submitted");
        return this;
    }


    public void TEPPoolingonHamilton() {
        Log.info(OPERATING+SPACE+TEP_POOLING_ON_HAMILTON);
        try {
            int sizeOfPCRBarcodeList = testContext.getDestinationPCRPlate().size();

            Log.info("TEP Pooling on hamilton Started");
            driver.clickLocator(btnViewedSavedSpec);
            driver.pauseExecutionFor(4000);
            driver.waitForLocatorToBeDeleted(divPackingSpinner);
            driver.clickLocator(btnPoolNow);
            driver.pauseExecutionFor(6000);
            driver.clickLocator(btnStartHamiltonWizard);
            driver.sendKeys(inputInstrumentBarcode, IHAM11);
            driver.pauseExecutionFor(4000);
            scanCarriers(C384_E0, 3);
            scanCarriers(c12T_B0, 3);

            Log.info("Scanning the position barcodes");
            for (int i = 1; i <= sizeOfPCRBarcodeList; ++i) {
                scanPosition(C384_E0, "1", Integer.toString(i));
                driver.copyTextToClipboard(testContext.getDestinationPCRPlate().get(i - 1));
                driver.pauseExecutionFor(3000);
                driver.paste();
                driver.pauseExecutionFor(4000);
            }

            driver.pauseExecutionFor(3000);
            driver.scrollTillElement(tabUseStandardTemplate);
            driver.pauseExecutionFor(2000);
            driver.clickLocator(btnSourceScanComplete);
            driver.pauseExecutionFor(3000);
            driver.scrollTillElement(bndhFlpBarcodelist);
            List<WebElement> listOfbndhFlpBarcode = driver.findElements(bndhFlpBarcodelist);
            List<String> listOfBNDHBarcode = new ArrayList<>();
            List<String> listOfFLPBarcode = new ArrayList<>();
            ArrayList<String> updatedFLPBNDbarcodeslist = new ArrayList<>();
            int sizeOflistOfbndhFlpBarcode = listOfbndhFlpBarcode.size();

            for (int i = 0; i < sizeOflistOfbndhFlpBarcode; ++i) {
                updatedFLPBNDbarcodeslist.add(driver.getText(listOfbndhFlpBarcode.get(i)));
                if (driver.getText(listOfbndhFlpBarcode.get(i)).contains("BNDH")) {
                    listOfBNDHBarcode.add(driver.getText(listOfbndhFlpBarcode.get(i)));
                } else {
                    listOfFLPBarcode.add(driver.getText(listOfbndhFlpBarcode.get(i)));
                }
            }
            testContext.setDestinationFlpBndhPlate(updatedFLPBNDbarcodeslist);
            Log.info("***FLP or BNDH barcodes are following***");
            Log.info(String.valueOf(testContext.getDestinationFlpBndhPlate()));
            //--
            verifyFormatOfHamiltonFLPBNDHBarcode(testContext.getSrnSet(), testContext.getDestinationFlpBndhPlate());

            //--

            Log.info("Scanning destination barcode");
            for (int i = 1; i <= testContext.getDestinationFlpBndhPlate().size(); ++i) {
               // scanPosition(C384_E0, "2", String.valueOf(i));
                driver.copyTextToClipboard(updatedFLPBNDbarcodeslist.get(i - 1));
                driver.pauseExecutionFor(3000);
                driver.paste();
                driver.pauseExecutionFor(2000);
            }

            driver.pauseExecutionFor(3000);
            driver.scrollTillElement(tabUseStandardTemplate);
            driver.pauseExecutionFor(2000);
            driver.clickLocator(btnTubePlacementComplete);

            driver.pauseExecutionFor(4000);
            driver.clickLocator(btnDownloadWorkList);
            driver.pauseExecutionFor(3000);
            driver.clickLocator(btnRunFinished);

        } catch (Exception e) {
            Assert.fail("TEP Pooling on hamilton failed due to" + e);
        }
        Log.info("TEP Pooling on hamilton submitted");

    }


    public void TEPPoolingonFlipjig() {
        Log.info(OPERATING+SPACE+TEP_POOLING_ON_FLIPJIG);
        try {
            int sizeOfPCRBarcodeList = 1;
            //testContext.getDestinationPCRPlate().add("6672PCR01I9R01");
            // Above code is for temporary basis


            Log.info("TEP Pooling on Flipjig Started");
            // int sizeOfPCRBarcodeList = testContext.getDestinationPCRPlate().size();
            driver.clickLocator(btnViewedSavedSpec);
            driver.waitForLocatorToBeVisible(btnPoolNow);
            driver.pauseExecutionFor(6000);
            driver.waitForLocatorToBeDeleted(divPackingSpinner);
            driver.clickLocator(btnPoolNow);
            driver.waitForLocatorToBeVisible(txtTEPPoolingOnFlipjig);
            driver.pauseExecutionFor(3000);

            List<WebElement> listofFLPDestinationContainerBarcodeField = driver.findElements(associateDestinationContainerFLPBarCodeField);
            List<WebElement> flpBarCodeList = driver.findElements(flpBarcodeList);
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < flpBarCodeList.size(); ++i) {
                arrayList.add(driver.getText(flpBarCodeList.get(i)));
            }
            Log.info("*****Flip jig's FLP barcode are following*****");
            Log.info(String.valueOf(arrayList));
            //--
            verifyFLPBarcodeIsStartingWithSRNWithFLP(arrayList);
            verifyFormatOfFlipJigFLPBarcode(testContext.getSrnSet(), arrayList);
            //--
            testContext.setFLPBarcodeList(arrayList);
            driver.pauseExecutionFor(3000);
            if (testContext.getFLPBarcodeList().size() > 1) {
                int flag = 0;
                for (WebElement element : listofFLPDestinationContainerBarcodeField) {
                    scanBarcode(element, testContext.getFLPBarcodeList().get(flag));
                    flag++;
                }
            } else {
                scanBarcode(associatedDestinationContainerBarCodeField, driver.getText(flpBarCodeList.get(0)));
            }

            testContext.setDestinatioinFLPBarcode(arrayList);
            driver.pauseExecutionFor(4000);
            scrollAndClickSubmit();
            softAssert.assertAll();

        } catch (Exception e) {
            Assert.fail("TEP Pooling on Flipjig failed due to" + e);
        }
        Log.info("TEP Pooling on Flipjig submitted");

    }

    public void scanBarcode(WebElement locator, String barcode) throws Exception {
        driver.waitForLocatorToBeVisible(locator);
        driver.pauseExecutionFor(3000);
        driver.sendKeys(locator, barcode + Keys.TAB);
        driver.waitForLocatorToBeDeleted(spinnerSampleTracker);
        Log.info(barcode + "  scanned successfully");
    }

    //TestCaseId: C18738
    public void verifyFLPBarcodeIsStartingWithSRNWithFLP(List<String> listOfFLPBarcode) {
        Log.info("Verifying FLP barcode is staring the SRN Number and FLP");
        ArrayList listFromSet = new ArrayList(testContext.getSrnSet());
        String srnNumber = listFromSet.get(0).toString().split("SRN_00")[1];

        for (int i = 0; i < listOfFLPBarcode.size(); ++i) {
            if (listOfFLPBarcode.get(i).contains(srnNumber + FLP)) {
                Log.info("FLP barcode is starting with SRN number and FLP");
            } else {
                Log.info("FLP Barcode is not starting with SRN number and FLP");
                softAssert.fail();
            }
        }
    }

    //C18747
    public void verifyFormatOfFlipJigFLPBarcode(Set<String> srnSet, List<String> listOfFLPBarcode) {
        Log.info("Verifying the format of FligJig FLP barcode");
        ArrayList listFromSet = new ArrayList(srnSet);
        String SrnNumber = (String) listFromSet.get(0);
        String flp = SrnNumber.split("SRN_00")[1] + FLP;
        // System.out.println(flpBarcode.substring(flpBarcode.length()-11,flpBarcode.length()));
        for (int i = 0; i < listOfFLPBarcode.size(); ++i) {
            boolean regExMatch = Pattern.compile("^[0-9]{2}[A-Z]{1}[0-9]{3}[-][A-Z]{1}[0-9]{2}").matcher(listOfFLPBarcode.get(i).substring(listOfFLPBarcode.get(i).length() - 10, listOfFLPBarcode.get(i).length())).matches();
            if (listOfFLPBarcode.get(i).length() == 17) {
                if (regExMatch && (flp.matches(listOfFLPBarcode.get(i).substring(0, 7)))) {
                    Log.info("Format as expected of Flipjig FLP Barcode");
                } else {
                    softAssert.fail("Incorrect format of flipjig FLP Barcode");
                }
            } else {
                softAssert.fail("Incorrect Barcode length of Flipjig FLP Barcode");
            }
        }

    }

    //C18755 and C18756 C18696
    //need to update after discussion
    public void verifyFormatOfHamiltonFLPBNDHBarcode(Set<String> srnSet, List<String> listOfFLPAndBNDHBarcode) {
        Log.info("Verifying the format of Hamilton FLP and BNDH barcode");
        ArrayList listFromSet = new ArrayList(srnSet);
        String SrnNumber = (String) listFromSet.get(0);
        String flp = SrnNumber.split("SRN_00")[1] + FLP;
        String bndh = SrnNumber.split("SRN_00")[1] + BNDH;

        //System.out.println(flpHamiltonBarcode.substring(flpHamiltonBarcode.length()-12,flpHamiltonBarcode.length()));
        for (int i = 0; i < listOfFLPAndBNDHBarcode.size(); ++i) {
            if (listOfFLPAndBNDHBarcode.get(i).contains(FLP)) {

                boolean regExMatch = Pattern.compile("^[0-9]{2}[a-z]{1}[A-Z]{1}[0-9]{1}[A-Z]{1}[0-9]{3}[-][A-Z]{1}[0-9]{2}").matcher(listOfFLPAndBNDHBarcode.get(i).substring(listOfFLPAndBNDHBarcode.get(i).length() - 13, listOfFLPAndBNDHBarcode.get(i).length())).matches();
                if (regExMatch && (flp.matches(listOfFLPAndBNDHBarcode.get(i).substring(0, 7))) && listOfFLPAndBNDHBarcode.get(i).length() == 20) {
                    Log.info("Format as expected of Hamilton FLP Barcode");
                } else {
                    softAssert.fail("Incorrect format of Hamilton FLP Barcode");
                }
            } else if (listOfFLPAndBNDHBarcode.get(i).contains(BNDH)) {

                boolean regExMatch = Pattern.compile("^[0-9]{2}[a-z]{1}[0-9]{1}[A-Z]{1}[0-9]{3}[-][A-Z]{1}[0-9]{2}").matcher(listOfFLPAndBNDHBarcode.get(i).substring(listOfFLPAndBNDHBarcode.get(i).length() - 12, listOfFLPAndBNDHBarcode.get(i).length())).matches();
                if (regExMatch && (bndh.matches(listOfFLPAndBNDHBarcode.get(i).substring(0, 8))) && listOfFLPAndBNDHBarcode.get(i).length() == 20) {
                    Log.info("Format as expected of Hamilton BNDH Barcode");
                } else {
                    softAssert.fail("Incorrect format of Hamilton BNDH Barcode");
                }
                softAssert.fail("Incorrect Barcode length of Hamilton BNDH Barcode");
            } else {
                softAssert.fail("Incorrect barcode for either FLP Barcode or BNDH Barcode");
            }
        }
    }

    //TestCaseId: C18658
    public void verifyClearButtonresetsSelectedBarcodes() throws Exception {
        Log.info("Verifying barcode is unselected after clicking on Clear Selection Button");
        List<WebElement> listOfInputContainer = driver.findElements(inputContainerBarcode);
        if (listOfInputContainer.size() > 0) {
            Log.info("Size of Input container barcode list is-----> " + listOfInputContainer.size());
            driver.clickLocator(btnClearSelection);
            driver.pauseExecutionFor(4000);
            if (listOfInputContainer.size() == 0) {
                Log.info("Input container barcode disappeared successfully");
            } else {
                softAssert.fail("Input container barcode field did not disappeared.");
            }
        }
    }
}


