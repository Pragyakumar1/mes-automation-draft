package com.twist.twistautomation.stepdef;

import com.jayway.jsonpath.JsonPath;
import com.twist.twistautomation.pages.mes.MESHomePage;
import com.twist.twistautomation.pages.mes.MESLoginPage;
import com.twist.twistautomation.pages.sampletracker.SampleTrackerHomePage;
import com.twist.twistautomation.pages.sampletracker.SampleTrackerLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import com.twist.twistautomation.DO.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.lang.reflect.Method;
import java.util.*;

public class SampleTrackerSteps extends BaseTest {

    @Autowired
    private SampleTrackerLoginPage loginPage;
    @Autowired
    private SampleTrackerHomePage homePage;
    @Autowired
    TestContext testContext;

    @Autowired
    Environment environment;

    @Autowired
    MESLoginPage mesLoginPage;
    MESHomePage mesHomePage;

    public void beforeSampleTrackerTest() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getSampleTrackerUrl());
    }

    public void navigateToMES() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getMesUrl());
    }

    @Then("user should able to perform chp deprotection submission")
    public void userAbleToCreateChpDeprotectioinSubmission() {
        beforeSampleTrackerTest();
        try {
            //homePage=loginPage.doLogin(propertyUtil.getSampleTrackerUserBarCode());
            homePage = loginPage.doLogin(propertyUtil.getSampleTrackerUserName(), propertyUtil.getSampleTrackerPassword());
            homePage.getSampleTrackerComponent().submitChpDeprotection(testContext.getChp_dep_barCode(), INSTRUMENT_ID, RESPONSE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    ApplicationContext context;

    @Given("operator login to sample tracker")
    public void operatorLoginToSampleTracker() throws Exception {
        beforeSampleTrackerTest();
        loginPage.doLogin(propertyUtil.getSampleTrackerUserName(), propertyUtil.getSampleTrackerPassword());
    }

    @Given("operator has chip barcode")
    public void userHasExistingOrderDetails(List<Map<String, String>> list) {
        String chip = list.stream().findFirst().get().get("ChipBarcode");
        if (!chip.equalsIgnoreCase(NA))
            testContext.getPlates().setDestinationPlatePPRn(Arrays.asList(chip.split(",")));
//        testContext.getPlates().setContainerpECRsBarCode("pECRs1026B279801B1");
//        testContext.getPlates().setDestinationPlatepECRe(Arrays.asList("pECRe1026B280082B1"));
        testContext.getPlates().setPPLSiPlateList(Arrays.asList(chip.split(",")));
        testContext.getPlates().setPCHPxBarcode(Arrays.asList(chip.split(",")));
        testContext.setPlatesBarCodeList(Arrays.asList(chip.split(",")));
    }

    @Given("operator submit {string}")
    public void operatorSubmit(String step) throws Exception {
        if (step.equalsIgnoreCase(PCA_PCR_NORMALIZATION)||step.equalsIgnoreCase(ECR_PCR_STAMP_INTO_ECHO_PLATE))
            beforeSampleTrackerTest();
//        testContext.setPlatesBarCodeList(Arrays.asList("pCHPx0809B234406ab"));
//        testContext.setContainerBarcode("pPCAs0827B404661I9");
        Object bean = context.getBean(Arrays.stream(step.split(" ")).findFirst().get());
        Method method = bean.getClass().getMethod(step.replace(" ", ""));

        if (method != null) {
            method.invoke(bean);
        }

    }


    @Given("operator has chip barcode file {string}")
    public void operatorHasChipBarcodeFile(String fileName) {

        String list = environment.getProperty("barcodesList");
        ArrayList<String> chpxBarcodes = new ArrayList<>(JsonPath.read(list, "chpx"));
        ArrayList<String> pEPRtBarcodes = new ArrayList<>(JsonPath.read(list, "pEPRt"));
        ArrayList<String> TBKANBarcodes = new ArrayList<>(JsonPath.read(list, "TBKAN"));
//        HashSet<String> pPLSiBarcodes = new HashSet<>(JsonPath.read(list, "pPLSi"));
//        String pPLSvBarcodes = JsonPath.read(list, "pPLSv");
        testContext.setPlatesBarCodeList(chpxBarcodes);
        testContext.getPlates().setDestinationPlatePEPRt(pEPRtBarcodes);

//        testContext.getPlates().getDestinationPlatePPLSi().add("pPLSi1117B482602I9");
//        testContext.getPlates().setDestinationPlatePPLSv("pPLSv1117B483011I9");
//        testContext.getPlates().getVectorPlateBarcode().addAll(Arrays.asList("7000369326","7000370216","7000369514","8037518110","7000409242","7000420152","7000420262","4510768859","7000369493","7506230279","7000445977","7000409239"));
//        testContext.getPlates().setVectorTubeBarcodePLS(Arrays.asList("1487402564","8048539262","8043930217","8048539297","8045653404","8043930122","8043946863","8049040587","8043930491","8043952178","8045677367","8049048114","2364980279","3134752735","1600708254"));
//        testContext.getPlates().setDestinationRackId("7189964808");
//        testContext.getPlates().setContainerBarcode("pPCAs1117B404999I9");
//        testContext.setDestinationPlatePPRq(Arrays.asList("pPPRq0908B401729I9"));
//        testContext.setDestinationPlatePPRp(Arrays.asList("pPPRp1001B377582I9","pPPRp1001B377585I9"));
//        testContext.getPlates().setVectorTubeBarcode(Arrays.asList("LT_170118_02"));
//        testContext.getPlates().setTBKANBarcodes(TBKANBarcodes);
//        testContext.getPlates().getPTBSuBarcodes().add("pTBSu1029B366885I9");
//        testContext.getPlates().getPRCAaBarcodes().add("pRCAa1101B536769I9");
//        testContext.getPlates().getPRCAcBarcodes().add("pRCAc1102B467598I9");
//        testContext.getPlates().getPRCArBarcodes().add("pRCAr1102B480695I9");

    }

    @And("operator QC review and release barcode")
    public void operatorQCReviewAndReleaseBarcode() {
        try {
            if (ObjectUtils.allNull(mesHomePage)) {
                navigateToMES();
                mesHomePage = mesLoginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());
            }
            for (String barcode :
                    testContext.getPlates().getDestinationPlatePPRp()) {
                mesHomePage.navigateToHomePage()
                        .getQcReviewComponent()
                        .launchQCReviewComponent()
                        .reviewAndReleasePCAPCR(barcode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("QC review Failed due to error: " + e);
        }


    }

    @And("operator QC review and release ECR barcode")
    public void operatorQCReviewAndReleaseECRBarcode() {

        try {

            webDriverUtil.pauseExecutionFor(8000);
            navigateToMES();
            mesHomePage = mesLoginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());

            for (String barcode :
                    testContext.getPlates().getDestinationPlatePEPRp()) {
                mesHomePage.navigateToHomePage()
                        .getQcReviewComponent()
                        .launchQCReviewComponent()
                        .reviewAndReleaseECRPCR(barcode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("QC review Failed due to error: " + e);
        }


    }

}
