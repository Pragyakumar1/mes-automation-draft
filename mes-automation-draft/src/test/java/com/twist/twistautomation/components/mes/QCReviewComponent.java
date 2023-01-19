package com.twist.twistautomation.components.mes;

import com.twist.twistautomation.components.AbstractComponent;
import com.twist.twistautomation.components.mes.API.MESAPIComponent;
import com.twist.twistautomation.utils.ByT;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Lazy
@Component
public class QCReviewComponent extends AbstractComponent {
    private By btnQCReview = By.id("twist-app-button-qc-review-app");
    private ByT pcapcr = ByT.xpath("//a[@href='/qc-review/%s/pca-pcr']");
    private ByT ecrpcr = ByT.xpath("//a[@href='/qc-review/%s/ecr-pcr']");
    private ByT btnBarcode = ByT.xpath("//h5[normalize-space()='%s']");
    private By btnRelease = By.xpath("//button[normalize-space()='Release']");


    @Autowired
    MESAPIComponent mesapiComponent;

    public QCReviewComponent launchQCReviewComponent() throws Exception {
        driver.clickLocator(btnQCReview);
        return this;
    }

    public void reviewAndReleasePCAPCR(String barcode) throws Exception {

       Set<String> wor_id = mesapiComponent.getPlateDetailsByBarcode(barcode);
       String productType = mesapiComponent.getFullOrderDetailsByWOR_id(wor_id.stream().findFirst().get()).equalsIgnoreCase(CLONAL_GENE) ? "clonal-gene":"non-clonal-gene" ;

        driver.pauseExecutionFor(2000);
        driver.clickLocator(pcapcr.format(productType));
        driver.waitForLocatorToBeDeleted(spinner);
        driver.clickLocator(btnBarcode.format(barcode));
        driver.scrollDownByJS(2000);
        driver.clickLocator(btnRelease);
        driver.pauseExecutionFor(3000);
    }

    public void reviewAndReleaseECRPCR(String barcode) throws Exception {
        Set<String> wor_id = mesapiComponent.getPlateDetailsByBarcode(barcode);
        String productType = mesapiComponent.getFullOrderDetailsByWOR_id(wor_id.stream().findFirst().get()).equalsIgnoreCase(CLONAL_GENE) ? "clonal-gene":"non-clonal-gene" ;

        driver.pauseExecutionFor(2000);
        driver.clickLocator(ecrpcr.format(productType));
        driver.waitForLocatorToBeDeleted(spinner);
        driver.clickLocator(btnBarcode.format(barcode));
        driver.scrollDownByJS(2000);
        driver.clickLocator(btnRelease);
        driver.pauseExecutionFor(3000);
    }
}
