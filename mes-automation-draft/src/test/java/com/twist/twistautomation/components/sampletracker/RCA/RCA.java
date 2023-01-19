package com.twist.twistautomation.components.sampletracker.RCA;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.GenericUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RCA extends SampleTrackerComponent {
    public By rcaDropdown = By.id("single-button9");
    public By btnRCASUPCentrifugation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA SUP Centrifugation']");
    public By btnRCAResuspension = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Resuspension']");
    public By btnRCASUPPlateConsolidation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA SUP Plate Consolidation']");
    public By btnRCAAliquoting = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Aliquoting']");
    public By btnRCAPelletCentrifugation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Pellet Centrifugation']");
    public By btnRCACellDilutiononCybio = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Cell Dilution on Cybio']");
    public By btnRCABoiling = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Boiling']");
    public By btnRCACellandReagentAddition = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Cell and Reagent Addition']");
    public By btnRCAIncubationStart = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Incubation Start']");
    public By btnRCAIncubationEnd = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Incubation End']");
    public By btnRCADenaturation = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Denaturation']");
    public By btnRCAFirstDilutiononMultiflo = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA First Dilution on Multiflo']");
    public By btnRCACybioDilution = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-RCA dropdown open']//a[@class='ng-binding'][normalize-space()='RCA Cybio Dilution']");

    public By destinationPlatepTBSu = By.xpath("//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pTBSu')]");
    public By destinationPlatepRCAa = By.xpath("//span[@class='twst-plate-checklist-item-barcode ng-binding' and contains(text(), 'pRCA')]");
    public By destinationPlatepRCA = By.xpath("//span[@class='twst-print-barcodes-list-item-preview-barcode ng-binding ng-scope']");

    public By btnClose = By.xpath("//span[@title='close']");
    public By positionIndicationByLocator = By.xpath("//div[contains(@class,'twst-highlighted-plate')]//following-sibling::*[@class='twst-hamilton-wizard-plate-data-index-label ng-binding ng-scope']");

    public void RCASUPCentrifugation() {
        try {
            selectMenu(rcaDropdown, btnRCASUPCentrifugation);
            int noOfBarcodes = testContext.getPlates().getTBKANBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getTBKANBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCASUPCentrifugation failed due to error");
        }
    }

    public void RCAResuspension() {
        try {
            selectMenu(rcaDropdown, btnRCAResuspension);
            int noOfBarcodes = testContext.getPlates().getTBKANBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getTBKANBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAResuspension failed due to error");
        }
    }

    public void RCASUPPlateConsolidation() {
        try {
            selectMenu(rcaDropdown, btnRCASUPPlateConsolidation);
            selectHamiltonWizard(IHAM04);
            scanCarriers(CHPC_G0, 5);
            for (String barcode :
                    testContext.getPlates().getTBKANBarcodes()) {
                scanPosition(CHPC_G0);
                driver.copyTextToClipboard(barcode);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            driver.waitForLocatorToBeVisible(destinationPlatepTBSu);

            List<WebElement> elements = driver.findElements(destinationPlatepTBSu);
            for (WebElement element :
                    elements) {
                driver.pauseExecutionFor(2000);
                scanPosition(CHPC_G0);
                String destinationPlateBarcode = driver.getText(element);
                driver.copyTextToClipboard(destinationPlateBarcode);
                testContext.getPlates().getPTBSuBarcodes().add(destinationPlateBarcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
                driver.pauseExecutionFor(1000);
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationScanComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(3000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCASUPPlateConsolidation failed due to error");
        }
    }

    public void RCAAliquoting() {
        try {
            selectMenu(rcaDropdown, btnRCAAliquoting);
            selectHamiltonWizard(IHAM04);
            scanCarriers(CHPC_G0, 5);
            for (String barcode :
                    testContext.getPlates().getPTBSuBarcodes()) {
                scanPosition(CHPC_G0);
                driver.copyTextToClipboard(barcode);
                driver.paste();
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnSourceScanComplete);
            driver.waitForLocatorToBeVisible(destinationPlatepRCAa);

            List<WebElement> elements = driver.findElements(destinationPlatepRCAa);
            for (WebElement element :
                    elements) {
                String destinationPlateBarcode = driver.getText(element);
                driver.copyTextToClipboard(destinationPlateBarcode);
                testContext.getPlates().getPRCAaBarcodes().add(destinationPlateBarcode);
                driver.pauseExecutionFor(1000);
                driver.paste();
                driver.pauseExecutionFor(2000);

                String carrier_position = GenericUtil.extractInt(driver.getText(positionIndicationByLocator));
                List<String> list = Arrays.asList(carrier_position.split(SPACE));
                String carrier = Integer.parseInt(list.get(0)) + "";
                String position = Integer.parseInt(list.get(1)) > 5 ? (Integer.parseInt(list.get(1)) - 5) + "" : Integer.parseInt(list.get(1)) + "";
                scanPosition(CHPC_G0, carrier, position);
                driver.pauseExecutionFor(1000);
            }
            driver.pauseExecutionFor(2000);
            driver.clickLocatorByJS(btnDestinationPlacementComplete);
            driver.waitForLocatorToBeVisible(btnRunFinished);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(3000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAAliquoting failed due to error");
        }
    }

    public void RCAPelletCentrifugation() {
        try {
            selectMenu(rcaDropdown, btnRCAPelletCentrifugation);
            int noOfBarcodes = testContext.getPlates().getPTBSuBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPTBSuBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAPelletCentrifugation failed due to error");
        }
    }

    public void RCACellDilutiononCybio() {
        try {
            selectMenu(rcaDropdown, btnRCACellDilutiononCybio);
            selectCybioWizard(IDSP64);
            int count = 1;
            for (String barcode : testContext.getPlates().getPRCAaBarcodes()) {
                scanPosition(IDSP64, "", count + "");
                driver.copyTextToClipboard(barcode);
                driver.paste();
                driver.pauseExecutionFor(3000);
                count++;
                String rcac = driver.getText(destinationPlatepRCA);
                testContext.getPlates().getPRCAcBarcodes().add(rcac);
                driver.clickLocator(btnClose);
                scanPosition(IDSP64, "", count + "");
                driver.pauseExecutionFor(2000);
                driver.copyTextToClipboard(rcac);
                driver.paste();
            }
            driver.clickLocatorByJS(btnPlateScanComplete);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCACellDilutiononCybio failed due to error");
        }
    }

    public void RCABoiling() {
        try {
            selectMenu(rcaDropdown, btnRCABoiling);
            int noOfBarcodes = testContext.getPlates().getPRCAcBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPRCAcBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCABoiling failed due to error");
        }
    }

    public void RCACellandReagentAddition() {
        try {
            selectMenu(rcaDropdown, btnRCACellandReagentAddition);
            selectCybioWizard(IDSP64);
            int count = 1;
            for (String barcode : testContext.getPlates().getPRCAcBarcodes()) {
                scanPosition(IDSP64, "", count + "");
                driver.copyTextToClipboard(barcode);
                driver.paste();
                driver.pauseExecutionFor(3000);
                count++;
                String rcar = driver.getText(destinationPlatepRCA);
                testContext.getPlates().getPRCArBarcodes().add(rcar);
                driver.clickLocator(btnClose);
                scanPosition(IDSP64, "", count + "");
                driver.pauseExecutionFor(2000);
                driver.copyTextToClipboard(rcar);
                driver.paste();
            }
            driver.clickLocatorByJS(btnPlateScanComplete);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCACellandReagentAddition failed due to error");
        }
    }

    public void RCAIncubationStart() {
        try {
            selectMenu(rcaDropdown, btnRCAIncubationStart);
            int noOfBarcodes = testContext.getPlates().getPRCArBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPRCArBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAIncubationStart failed due to error");
        }
    }

    public void RCAIncubationEnd() {
        try {
            selectMenu(rcaDropdown, btnRCAIncubationEnd);
            int noOfBarcodes = testContext.getPlates().getPRCArBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPRCArBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAIncubationEnd failed due to error");
        }
    }

    public void RCADenaturation() {
        try {
            selectMenu(rcaDropdown, btnRCADenaturation);
            int noOfBarcodes = testContext.getPlates().getPRCArBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPRCArBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCADenaturation failed due to error");
        }
    }

    public void RCAFirstDilutiononMultiflo() {
        try {
            selectMenu(rcaDropdown, btnRCAFirstDilutiononMultiflo);
            int noOfBarcodes = testContext.getPlates().getPRCArBarcodes().size();
            for (String barcode :
                    testContext.getPlates().getPRCArBarcodes()) {
                scanBarcode(inputPlateBarcode, barcode);
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(addPlate);
            }
            scrollAndClickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCAFirstDilutiononMultiflo failed due to error");
        }
    }

    public void RCACybioDilution() {
        try {
            selectMenu(rcaDropdown, btnRCACybioDilution);
            selectCybioWizard(IDSP64);
            int count = 1;
            for (String barcode : testContext.getPlates().getPRCArBarcodes()) {
                scanPosition(IDSP64, "", count + "");
                driver.copyTextToClipboard(barcode);
                driver.paste();
                driver.pauseExecutionFor(3000);
                count++;
                String rcad = driver.getText(destinationPlatepRCA);
                testContext.getPlates().getPRCAdBarcodes().add(rcad);
                driver.clickLocator(btnClose);
                scanPosition(IDSP64, "", count + "");
                driver.pauseExecutionFor(2000);
                driver.copyTextToClipboard(rcad);
                driver.paste();
            }
            driver.clickLocatorByJS(btnPlateScanComplete);
            driver.clickLocator(btnRunFinished);
            driver.pauseExecutionFor(2000);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("RCACybioDilution failed due to error");
        }
    }
}
