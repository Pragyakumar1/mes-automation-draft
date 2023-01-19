package com.twist.twistautomation.components.sampletracker.VIN;

import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Lazy
@Component
public class VIN extends SampleTrackerComponent {

    public By vinDropdown = By.id("single-button21");
    public By btnVINRegisterVectorToTubes = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-VIN dropdown open']//a[@class='ng-binding'][normalize-space()='VIN Register Vector To Tubes']");
    public By btnVINRegisterPlateToStorage = By.xpath("//div[@class='btn-group twst-record-step-step-options-select twst-record-step-step-options-select-VIN dropdown open']//a[@class='ng-binding'][normalize-space()='VIN Register Plate to Storage']");

    public void VINRegisterVectorToTubes() {
        try {
            Log.info("Executing VINRegisterVectorToTubes");
            selectMenu(vinDropdown, btnVINRegisterVectorToTubes);
            int noOfBarcodes = testContext.getPlates().getLtId().size();
            for (String ltId :
                    testContext.getPlates().getLtId()) {
                String tube_barcode = mesapiComponent.getVectorTubeDetailsById(ltId);
                mesapiComponent.insertPlateToVINRegisterVectorToTube(tube_barcode);
                Log.info("tube barcode inserted is: " + tube_barcode);
                Integer volumeRequired = testContext.getPlates().getVectorVolume().get(ltId) == null ? 1000 : testContext.getPlates().getVectorVolume().get(ltId);
                Integer numberOfTubesRequired = 1;
                if (volumeRequired > 1000) {
                    numberOfTubesRequired = (int) Math.ceil(volumeRequired / 1000);
                    numberOfTubesRequired++;
                }
                scanBarcode(inputPlateBarcode, tube_barcode);
                scanBarcode(inputPlateBarcode, numberOfTubesRequired + "");
                List<WebElement> elements = driver.findElements(destinationBarcode);
                for (WebElement element : elements) {
                    String tubeId = GenericUtil.getUniqueNumeric(10);
                    testContext.getPlates().getVectorTubeIds().add(tubeId);
                    scanBarcode(element, tubeId);
                }
                scrollAndClickSubmit();
                noOfBarcodes--;
                if (noOfBarcodes != 0)
                    driver.clickLocator(btnRecordAnotherStep);
            }
            Log.info("Completed VINRegisterVectorToTubes");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("VINRegisterVectorToTubes stage failed due to error ");
        }

    }

    public void VINRegisterTubestoPlate() {
        Log.info("Executing VINRegisterTubestoPlate");
        String rackID = GenericUtil.getUniqueNumeric(10);
        testContext.getPlates().setDestinationRackId(rackID);
        sampleTrackerAPIComponent.invokeTransformPreview(rackID, VIN_REGISTER_TUBES_TO_PLATE, testContext.getPlates().getVectorTubeIds());
        sampleTrackerAPIComponent.invokeTransformSpecs(rackID, VIN_REGISTER_TUBES_TO_PLATE);
        Log.info("Completed VINRegisterTubestoPlate");
    }

    public void VINRegisterPlatetoStorage() {
        try {
            Log.info("Executing VINRegisterPlatetoStorage");
            selectMenu(vinDropdown, btnVINRegisterPlateToStorage);
            scanBarcode(inputPlateBarcode, testContext.getPlates().getDestinationRackId());
            scanBarcode(destinationBarcode, ISTR59_RACK_04_01);
//            mesapiComponent.getStorageContainerBarcodes();
            scrollAndClickSubmit();
            Log.info("Completed VINRegisterPlatetoStorage");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("VINRegisterPlatetoStorage stage failed due to error ");
        }
    }

}
