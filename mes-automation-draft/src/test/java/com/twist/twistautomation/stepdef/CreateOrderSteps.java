package com.twist.twistautomation.stepdef;

import com.jayway.jsonpath.JsonPath;
import com.twist.twistautomation.DO.OrderDetail;
import com.twist.twistautomation.DO.TestContext;
import com.twist.twistautomation.components.mes.API.MESAPIComponent;
import com.twist.twistautomation.config.CustomInterceptor;
import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.props.TAPIEndpoints;
import com.twist.twistautomation.utils.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

public class CreateOrderSteps implements TAPIEndpoints, IConstants {

    @Autowired
    Environment environment;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    PropertyUtil propertyUtil;

    String request;
    String response;
    Integer responseStatus;

    Gson gson = new Gson();

    @Autowired
    CustomInterceptor interceptor;
    protected Scenario scenario;

    @Autowired
    TestContext testContext;

    @Autowired
    DriverUtil driverUtil;

    @Autowired
    MESAPIComponent mesapiComponent;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
        interceptor.scenario = scenario;
    }

    public void loadPDFUrlInBrowser(String invoiceUrl) throws InterruptedException {
        driverUtil.launchBrowser();
        driverUtil.navigateTo(invoiceUrl);
        Thread.sleep(6000);
        //driverUtil.quitBrowser();
    }

    //todo
    // insertion_point_mes_uid, vector_mes_uid - try to make dynamic these fields
    @Given("user has create construct request with")
    public void userHasCreateConstructRequest(Map<String, String> map) {

        request = environment.getProperty("createConstructRequest");
        Map<String, Object> requestValues = new ConcurrentHashMap<>();
//        requestValues.put("$[0].['sequences']", testContext.getSequence());
        requestValues.put("$[0].['type']", map.get(TYPE));
        requestValues.put("$[0].['adapters_on']", Boolean.parseBoolean(map.get("adapters_on")));
        if (map.get(TYPE).equals("CLONED_GENE")) {
            requestValues.put("$[0].['insertion_point_mes_uid']", "494e5353-5ed9-62f0-1bbf-fc00073d8b1b");
            requestValues.put("$[0].['vector_mes_uid']", "VEC_5d66ed1c299eb80009282d3a");
        }

        if (map.get(TYPE).equals("NON_CLONED_GENE") || map.get(TYPE).equals("OLIGO_POOL")) {
            request = JsonUtils.deleteJSONNode(request, "$[0].['insertion_point_mes_uid']");
            request = JsonUtils.deleteJSONNode(request, "$[0].['vector_mes_uid']");
        }
        if (map.get(TYPE).equals("OLIGO_POOL")) {
            requestValues.put("$[0].['sequences']", asList("GGGGAGGAGCGCGGCGGTAG", "GGCGGTAGCGGTGAATTTTG", "GCGGTAGCGGTGAATTTTGA", "CGGTAGCGGTGAATTTTGAG", "TAGCGGTGAATTTTGAGGGG", "AGCGGTGAATTTTGAGGGGT", "GTGAATTTTGAGGGGTGGGT", "TGAATTTTGAGGGGTGGGTC", "GAATTTTGAGGGGTGGGTCG", "AATTTTGAGGGGTGGGTCGG", "GGGCGCGCACTCGCCGCCCC"));
            request = JsonUtils.deleteJSONNode(request, "$[0].['adapters_on']");
        }
        request = JsonUtils.updateJSONString(request, requestValues);
        scenario.log(request);
    }

    @When("user invoke create construct API")
    public void userInvokeCreateConstructAPI() {

        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + CREATE_CONSTRUCT_RESOURCE, request, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();

//        constructResponse = gson.fromJson(response, Construct[].class);
        scenario.log(response);
    }

    @Then("verify {int} response")
    public void verifyResponse(Integer expectedStatus) {
        Assert.assertEquals(responseStatus, expectedStatus);
    }

    @Given("user has create quote request")
    public void userHasCreateQuoteRequest(Map<String, String> map) {
        String type = map.get(TYPE);
        String order_sub_product_type = map.get(ORDER_SUB_PRODUCT_TYPE);
        String delivery_format = map.get(DELIVERY_FORMAT);
        String id = JsonPath.read(response, "$[0].['id']").toString();

        request = environment.getProperty("createQuoteRequest");
        request = JsonUtils.updateJSONString(request, "$['containers'].[0].['constructs'].[0].['id']", id);
        request = JsonUtils.updateJSONString(request, "$['external_id']", id);
        if (type.equalsIgnoreCase("TUBE")) {
            request = JsonUtils.addJSONNode(request, "$.containers[0]", TYPE, type);
            request = JsonUtils.updateJSONString(request, "$.order_settings[0].product_code", "SER_PKG_TUBE");
        }
        request = JsonUtils.updateJSONString(request, "$.order_settings[0].product_code", delivery_format);
        if (order_sub_product_type.equalsIgnoreCase("NON_CLONAL_ADAPTERS_OFF"))
            request = JsonUtils.addJSONNode(request, "$", "order_sub_product_type", order_sub_product_type);

        scenario.log(request);
    }

    @When("user invoke create quote API")
    public void userInvokeCreateQuoteAPI() throws InterruptedException {
        Thread.sleep(5000);
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + CREATE_QUOTE_RESOURCE, request, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
    }

    @Then("verify the quote status is {string}")
    public void verifyTheQuoteStatusIs(String status) throws InterruptedException {
        String id = JsonPath.read(response, "$.['id']").toString();
        String getQuoteResource = GET_QUOTE_RESOURCE.replace("quotation_id", id);
        String quoteStatus = "";
        int counter = 0;
        do {
            Thread.sleep(10000);
            ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getTapiUrl() + getQuoteResource, String.class);
            System.out.println(responseEntity.getStatusCodeValue());
            System.out.println(responseEntity.getBody());
            response = responseEntity.getBody().toString();
            quoteStatus = JsonPath.read(response, "$.['status_info'].['status']");
            counter++;
            if (counter == 20) break;
        } while (quoteStatus.equals("PENDING"));
        if (!quoteStatus.equals("SUCCESS")) {
            Assert.fail("The quote is not generated yet, the current status is: " + quoteStatus);
        }
        scenario.log(response);

    }

    @Given("user has create order request")
    public void useHasCreateOrderRequest() {
        request = environment.getProperty("createOrderRequest");
        String id = JsonPath.read(response, "$.['id']").toString();
        request = request.replace("quotation_id", id);
        scenario.log(request);
    }

    @When("user invoke create order API")
    public void userInvokeCreateOrderAPI() throws InterruptedException {
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + CREATE_ORDER_RESOURCE, request, String.class);
        responseStatus = responseEntity.getStatusCodeValue();
        if (responseStatus == 201) {
            response = responseEntity.getBody().toString();
            scenario.log(response);
            testContext.getOrderDetail().setSfdc_id(JsonPath.read(response, ID).toString());
            testContext.getSfdcList().add(JsonPath.read(response, "$.id").toString());
        } else {
            Log.info("error response: " + responseEntity.getBody());
            Assert.fail("Create order failed with status code: " + responseStatus);
        }

    }


    @When("user invoke get order-items details and check sync happened at MES")
    public void usesInvokeGetOrderItemsRequest() throws InterruptedException {
        String getOrderItemDetailsResource = GET_ORDER_ITEM_RESOURCE.replace(SFDC_ID, testContext.getOrderDetail().getSfdc_id());
        int counter = 0;
        String status = "";
        do {
            Thread.sleep(15000);
            ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getTapiUrl() + getOrderItemDetailsResource, String.class);
            responseStatus = responseEntity.getStatusCodeValue();
            if (responseStatus != 200) {
                Log.info("error response: " + responseEntity.getBody());
                Assert.fail("get order-items details API failed with status code:" + responseStatus);
            }
            response = responseEntity.getBody().toString();
            status = JsonPath.read(response, "$[0].['status']").toString();
            scenario.log(gson.toJson(response));
            triggerOrderTransformer();
            if (counter == 30) break;
            counter++;
        } while (!status.equals("RELEASED_FOR_PRODUCTION"));

        testContext.setItem_id(JsonPath.read(response, "$[0].['id']").toString());
        testContext.setItem_external_id(JsonPath.read(response, "$[0].['external_id']").toString());
    }

    @And("user fetch order details")
    public void useFetchOrderDetails() {
        Log.info(BLUE_UNDERLINED + "Fetching order details" + ANSI_RESET);
        ArrayList updatedWorIdList = new ArrayList();
        for (String sfdc_id : testContext.getSfdcList()) {
            OrderDetail orderDetail = mesapiComponent.getOrderDetailsBySFDC_id(sfdc_id);
            testContext.getOrderDetails().add(orderDetail);
            // Need to refactor below two lines
            testContext.setItem_id(orderDetail.getItem_ids().stream().findFirst().get());
            updatedWorIdList.add(orderDetail.getWor_id());
        }
        Log.info(BLUE_UNDERLINED + "WorId is mentioned below" + ANSI_RESET);
        Log.info(updatedWorIdList.toString());
        testContext.setWorIdList(updatedWorIdList);
    }

    public String fetchOrderDetails(Object objectList) {
        String getOrderDetailsResource = GET_ORDER_RESOURCE.replace(SFDC_ID, objectList.toString());
        ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getTapiUrl() + getOrderDetailsResource, String.class);
        responseStatus = responseEntity.getStatusCodeValue();
        if (responseStatus != 200) {
            Assert.fail("fetch Order details API returned status code" + responseStatus);
            scenario.log(responseEntity.getBody().toString());
        }
        return responseEntity.getBody().toString();
    }

    @Given("trigger order transformer")
    public void triggerOrderTransformer() throws InterruptedException {
        Thread.sleep(5000);
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + ORDER_TRANSFORMER_RESOURCE, "", String.class);

    }

    @Given("user has create inventory request for {string}")
    public void useHasCreateInventoryRequestFor(String type) {
        request = environment.getProperty("createInventory");
        request = StringUtils.replaceEach(request, new String[]{"item_id", "batch_id", "shipment_type"}, new String[]{testContext.getItem_id(), testContext.getItem_id(), type});

    }

    @When("user invoke create inventory API")
    public void userInvokeCreateInventoryAPI() {
        String createInventoryResource = CREATE_INVENTORY_RESOURCE.replace(SFDC_ID, testContext.getOrderDetail().getSfdc_id());
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + createInventoryResource, request, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
    }

    @Given("user has create shipment request for {string}")
    public void userHasCreateShipmentRequestFor(String type) {
        request = environment.getProperty("createShipment");
        request = StringUtils.replaceEach(request, new String[]{"sfdcId", "external_id", "shipment_type", "item_id", "batch_id"}, new String[]{testContext.getOrderDetail().getSfdc_id(), testContext.getItem_external_id(), type, testContext.getItem_id(), testContext.getItem_id()});
        scenario.log(request);
    }

    @When("user invoke create shipment API")
    public void userInvokeCreateShipmentAPI() {
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + CREATE_SHIPMENT_RESOURCE, request, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        scenario.log(response);
        testContext.setSap_delivery_id(JsonPath.read(response, "$['sap_delivery_id']").toString());
    }

    @Given("user has finalize shipment request")
    public void userHasFinalizeShipmentRequest() {
        request = environment.getProperty("finalizeShipment");
        request = StringUtils.replaceEach(request, new String[]{"tracking_id", SFDC_ID, "ship_date"}, new String[]{TRACKING_NUMBER, testContext.getOrderDetail().getSfdc_id(), DateHelper.getTodayYYMMDDTHH()});
        scenario.log(request);
    }

    @When("user invoke finalize shipment API")
    public void userInvokeFinalizeShipmentAPI() {
        String finalizeShipmentResource = FINALIZE_SHIPMENT_RESOURCE.replace("sap_delivery_id", testContext.getSap_delivery_id());
        ResponseEntity responseEntity = testRestTemplate.postForEntity(propertyUtil.getTapiUrl() + finalizeShipmentResource, request, String.class);
        responseStatus = responseEntity.getStatusCodeValue();

    }

    @Given("user has create invoice request with status {string}")
    public void userHasCreateInvoiceRequestWithStatus(String status) {
        if (status.equals("BOOKED")) {
            request = environment.getProperty("createInvoiceBooked");
            request = request.replace("tracking_id", TRACKING_NUMBER);
        } else {
            request = environment.getProperty("createInvoiceIntransit");
        }
        scenario.log(request);
    }

    @When("user invoke invoice API")
    public void userInvokeInvoiceAPI() {
        String createInvoiceResource = CREATE_INVOICE_RESOURCE.replace("sap_delivery_id", testContext.getSap_delivery_id());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity responseEntity = testRestTemplate.exchange(propertyUtil.getTapiUrl() + createInvoiceResource, HttpMethod.PATCH, entity, String.class);

        System.out.println(responseEntity);
        responseStatus = responseEntity.getStatusCodeValue();

    }

    @Then("user downloaded the invoice")
    public void userInvokeGetInvoicePDFAPI() throws IOException, InterruptedException {
        String getInvoicePDFResource = GET_PDF_INVOICE_RESOURCE.replace("sap_delivery_id", testContext.getSap_delivery_id());
        ResponseEntity responseEntity = testRestTemplate.getForEntity(propertyUtil.getTapiUrl() + getInvoicePDFResource, String.class);
        response = responseEntity.getBody().toString();
        responseStatus = responseEntity.getStatusCodeValue();
        String invoiceUrl = JsonPath.read(response, TEMPORARY_URL);
        testContext.setInvoicePDFLink(invoiceUrl);
        loadPDFUrlInBrowser(invoiceUrl);

        String pdfContent = GenericUtil.getPDFContent(invoiceUrl);
        scenario.log(pdfContent);
        Log.info(pdfContent);
    }

    @Given("user generate random sequence with")
    public void userGenerateRandomSequenceWith(Map<String, String> data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("a", "0.25");
        map.add("c", "0.25");
        map.add("t", "0.25");
        map.add("g", "0.25");
        map.add("l1", data.get("basePair"));
        map.add("l2", data.get("basePair"));
        map.add("n", data.get("numberOfSequences"));
        map.add("submit", "Generate!");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = testRestTemplate.postForEntity(SEQUENCE_GEN, request, String.class);
        Document doc = Jsoup.parse(response.getBody());
        String sequence = doc.body().select("PRE").first().text().split("bp")[1].trim();
        testContext.setSequence(sequence);
    }

    @Given("user has existing order details")
    public void userHasExistingOrderDetails(DataTable sfdcList) {
        Log.info(BLUE_UNDERLINED + "Storing sfdc in ArrayList" + ANSI_RESET);
        ArrayList updatedSfdcList = new ArrayList<>();
        List<List<String>> dataList = sfdcList.transpose().asLists(String.class);
        String[] sfdcDataArray = null;
        for (List<String> newList : dataList) {
            sfdcDataArray = newList.get(1).split(",");
        }
        for (String data : sfdcDataArray) {
            updatedSfdcList.add(data);
        }
        Log.info(updatedSfdcList.toString());
        testContext.setSfdcList(updatedSfdcList);
    }

/*    @And("user fetch order details and check Invoice generated")
    public void userFetchOrderDetailsAndCheckInvoiceGenerated() throws InterruptedException {

        List<String> invoice = null;
        int counter = 0;
        do {
            Thread.sleep(20000);
            response = fetchOrderDetails();
            invoice = JsonPath.read(response, "$.invoices");
            Log.info("invoice: " + invoice);
            counter++;
            if (counter == 20)
                break;
        } while (invoice.size() == 0);
        scenario.log("Invoice Generated successfully: \n" + gson.toJson(response));
    }*/

}
