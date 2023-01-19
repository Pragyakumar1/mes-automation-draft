package com.twist.twistautomation.stepdef;

import com.jayway.jsonpath.JsonPath;
import com.twist.twistautomation.config.CustomInterceptor;
import com.twist.twistautomation.pages.mes.MESHomePage;
import com.twist.twistautomation.pages.mes.MESLoginPage;
import com.twist.twistautomation.stepdef.base.BaseTest;
import com.twist.twistautomation.utils.JsonUtils;
import com.twist.twistautomation.utils.Log;
import com.twist.twistautomation.DO.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.twist.twistautomation.props.TAPIEndpoints.*;

public class SynthesisSteps extends BaseTest {


    @Autowired
    Environment environment;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    TestContext testContext;

    @Autowired
    private MESLoginPage mesLoginPage;

    @Autowired
    private MESHomePage mesHomePage;

    String request;
    String response;

    Integer responseStatus;

    @Autowired
    CustomInterceptor interceptor;
    protected Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
        interceptor.scenario = scenario;
    }

    @Given("user has create synthesis run request")
    public void userHasCreateSynthesisRequest() {
        request = environment.getProperty("createSynthesisRunRequest");
        Map<String, Object> requestValues = new ConcurrentHashMap<>();
        requestValues.put("$.plan_id", testContext.getSynthesis_sp_id());
        requestValues.put("$.request_id", testContext.getSynthesis_srq_id());
        requestValues.put("$.chip_barcode", testContext.getSynthesis_chip_id());
//        requestValues.put("$.chip_type_id", testContext.getChip_type_id());
//        requestValues.put("$.recipe_name", testContext.getRecipe_name());
//        requestValues.put("$.machine_id", testContext.getMachine_id());
        request = JsonUtils.updateJSONString(request, requestValues);
        scenario.log(request);
    }


    @When("user invoke create synthesis run api")
    public void userHasInvokeSynthesisRunAPI() {
        Log.info("Invoking synthesis run API");
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSynthUrl() + CREATE_SYNTHESIS_RUN_RESOURCE, request, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        testContext.setSrn_id(JsonPath.read(response, "['id']").toString());
        testContext.setHuman_id(JsonPath.read(response, "['human_id']").toString());
        testContext.setLayer_count(Integer.parseInt(JsonPath.read(response, "['layer_count']").toString()) - 1);
        scenario.log(response);
    }

    /*@Then("verify status code")
    public void verifyResponse() {
        Assert.assertEquals(responseStatus, java.util.Optional.of(201));
    }
*/
    @Given("user has created request for add started event")
    public void userHasCreatedAddStartedEventRequest() {
        request = environment.getProperty("createAddStartedRunToEventRequest");
        scenario.log(request);
    }

    @When("user invoke create add started event api")
    public void userHasInvokedAddStartedEventApi() {
        Log.info("Invoking Add Started event API");
        String createAddStartedEvenResource = CREATE_ADD_STARTED_EVENT_RESOURCE.replace("srn_id", testContext.getSrn_id());
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSynthUrl() + createAddStartedEvenResource, request, String.class);
        //response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
    }

   /* @Then("verify started event has successfully created")
    public void verifyEventSuccessfullyCreatedd() {
        Assert.assertEquals(responseStatus, java.util.Optional.of(201));
    }*/

    @Given("user has created request for layer completion")
    public void userHasCreatedLayerCompletionRequest() {
        request = environment.getProperty("createLayerCompletionRequest");
        scenario.log(request);
    }

    @When("user invoke create layer completion api")
    public void userHasInvokedLayerCompletionApi() {
        Log.info("Invoking Layer completion API");
        String createLayerCompletionResource = CREATE_LAYER_COMPLETION_RESOURCE.replace("srn_id", testContext.getSrn_id());
        createLayerCompletionResource = createLayerCompletionResource.replace("layer_count", String.valueOf(testContext.getLayer_count()));
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSynthUrl() + createLayerCompletionResource, request, String.class);
        //response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
    }

    @Given("user has created request for add completed event")
    public void userHasCreatedAddCompletedEventRequest() {
        request = environment.getProperty("createAddCompletedEventRequest");
        scenario.log(request);
    }

    @When("user invoke for create add completed event")
    public void userHasInvokedAddCompletedEventApi() {
        Log.info("Invoking Add Completed event API");
        String createAddCompleteEventResource = CREATE_ADD_COMPLETED_EVENT_RESOURCE.replace("srn_id", testContext.getSrn_id());
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSynthUrl() + createAddCompleteEventResource, request, String.class);
        //response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
    }

    @When("user invoke get ready plates for CHP Deprotection")
    public void userHasInvokedGetCHPDeprotectionApi() {
        Log.info("Invoking get ready plates API");
        ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getSampleTrackerUrl() + GET_CHP_DEPROTECTION_READY_PLATES_RESOURCE, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        testContext.setChp_dep_barCode(JsonPath.read(response, "plates[0].['barcode']").toString());
        String barCode = JsonPath.read(response, "plates[0].['barcode']").toString();
        scenario.log(response);
    }

    @Given("user has created request for CHP deprotectioin submission")
    public void userHasCreatedChpDeprotectionRequest() {
        request = environment.getProperty("createChpDeprotectionRequest");
        scenario.log(request);
    }


    public void beforeMES() {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(propertyUtil.getMesUrl());
    }

    @Given("user invoke get plate number api")
    public void getPlatePosition() {
        Log.info("Invoking GET PLATE_POSITION API");
        List<Integer> plateNumberList = new ArrayList<>();
        String getPlatePositionResource = GET_PLATE_POSITION_RESOURCE.replace("srq_number", testContext.getChip_plan_srq_id());
        ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + getPlatePositionResource, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        List<Map<Object, Object>> platesList = JsonPath.read(response, "$.plan.plates");
        for (Map<Object, Object> plate : platesList
        ) {
            List<String> rows = JsonPath.read(plate, "$.rows");
            if (rows.size() > 0) {
                plateNumberList.add(JsonPath.read(plate, "$.plate_number"));
            }
        }
        testContext.setPlatesList(plateNumberList);
        scenario.log(response);
        Log.info("****Plate number******");
        for (Integer pp : plateNumberList) {
            Log.info(String.valueOf(pp));
        }
    }

    @Given("user has created request for generate extraction plate bar code")
    public void userHasCratedGenerateExtractionPlateRequest() {
        Map<Integer, String> updateBarCodeMap = new LinkedHashMap<>();
        request = environment.getProperty("createGenerateExtractionPlateRequest");
        for (Integer plateNumber : testContext.getPlatesList()) {
            Map<String, Object> reqValuesMap = new HashMap<>();
            reqValuesMap.put("$.chip_id", testContext.getChp_dep_barCode());
            reqValuesMap.put("$.plate_number", plateNumber);
            request = JsonUtils.updateJSONString(request, reqValuesMap);

            scenario.log(request);

            ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + GENERATE_EXTRACTION_PLATE_BAR_CODE_RESOURCE, request, String.class);
            response = responseEntity.getBody().toString();
            responseStatus = responseEntity.getStatusCodeValue();
            updateBarCodeMap.put(plateNumber, JsonPath.read(response, "$.plate_barcode"));
            scenario.log(response);
        }
        testContext.setBarCodeMap(updateBarCodeMap);
    }

    @When("user invoke generate extraction plate api")
    public void userHasInvokedGenerateExtractionPlateAPI() {
        Log.info("Invoking extraction plate API ");
        request = environment.getProperty("createExtractionSetToExtractionPlateRequest");
        for (Map.Entry<Integer, String> entry : testContext.getBarCodeMap().entrySet()) {
            Map<String, Object> reqValuesMap = new HashMap<>();
            reqValuesMap.put("$.chip_id", testContext.getChp_dep_barCode());
            reqValuesMap.put("$.plate_barcode", entry.getValue());
            reqValuesMap.put("$.extraction_set", String.valueOf(entry.getKey()));
            request = JsonUtils.updateJSONString(request, reqValuesMap);
            if(!Arrays.asList(4,8,12).contains(entry.getKey()))
                testContext.getPlatesBarCodeList().add(entry.getValue());
            scenario.log(request);
            ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + EXTRACTION_SET_EXTRACTION_PLATE_BAR_CODE_RESOURCE, request, String.class);
            response = responseEntity.getBody().toString();
            responseStatus = responseEntity.getStatusCodeValue();
            scenario.log(response);

        }
    }

    @And("User has moved the plate from transportation to done lane")
    public void userMovedPlateFromTransportationToDoneLane() throws Exception {
        beforeMES();

        // mesHomePage = mesLoginPage.doLogin(propertyUtil.getMesUserName(), propertyUtil.getMesPassword());
        mesHomePage.getSynthesisAdminComponent().movePlateFromTransporationToDone().verifyPlateHasModedInDoneLane();

    }

    @Given("User invoked plate info api")
    public void getPlateInfo() {
        Set<String>srnSet=new HashSet<>();
        for(String barCode:testContext.getPlatesBarCodeList()){
            String getPlatePositionResource = GET_PLATE_INFO_RESOURCE.replace("bar_code", barCode);
            ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getSampleTrackerUrl() + getPlatePositionResource, String.class);
            response = responseEntity.getBody().toString();
            responseStatus = responseEntity.getStatusCodeValue();
            String srnId=JsonPath.read(response,"plateDetails.srns[0].humanId");
            Log.info("SRN Number------>" + srnId);
            Integer numberOfSamples=JsonPath.read(response,"plateDetails.srns[0].well_count");
            testContext.setNumberOfSamples(numberOfSamples);
            testContext.getSrnSet().add(srnId);
            Log.info("Number of Samples----->" +numberOfSamples);
        }
        Log.info(testContext.getSrnSet().toString());
    }

}
