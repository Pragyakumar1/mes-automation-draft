 package com.twist.twistautomation.components.mes.API;

import com.jayway.jsonpath.JsonPath;
import com.twist.twistautomation.DO.OrderDetail;
import com.twist.twistautomation.props.MESEndpoints;
import com.twist.twistautomation.utils.GenericUtil;
import com.twist.twistautomation.utils.JsonUtils;
import com.twist.twistautomation.utils.Log;
import com.twist.twistautomation.utils.PropertyUtil;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.twist.twistautomation.constants.IConstants.*;

@Lazy
@Component
public class MESAPIComponent {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    Environment environment;

    @Autowired
    PropertyUtil propertyUtil;

    public Set<String> getPlateDetailsByBarcode(String barcode) {
        Log.info("Invoking PlateDetailsByBarcode API to get details");
        Map<String, String> urlValues = new ConcurrentHashMap<>();
        urlValues.put(BARCODE, barcode);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + MESEndpoints.PLATE_DETAILS, String.class, urlValues);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Get Plate Details API returned error " + responseEntity.getStatusCode());
        List<Map<String, String>> list = (List<Map<String, String>>) JsonUtils.extractJSON(responseEntity.getBody(), PLATE_DETAILS);
        Set<String> wor_ids = new HashSet<String>();
        for (Map details : list) {
            if (!details.get(CATEGORY).toString().equalsIgnoreCase(CONTROL)) {
                wor_ids.add(details.get(WORK_ORDER_ID).toString());
            }
        }
        Log.info(responseEntity.getBody());
        Log.info("End of PlateDetailsByBarcode API Invocation");
        return wor_ids;
    }

    public String getFullOrderDetailsByWOR_id(String wor_id) {
        Log.info("Invoking FullOrderDetailsByWOR_id API to get Order Type details");
        Map<String, String> urlValues = new ConcurrentHashMap<>();
        urlValues.put(WOR_ID, wor_id);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + MESEndpoints.ORDER_FULL_DETAILS, String.class, urlValues);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Get FullOrderDetailsByWOR_id API returned error " + responseEntity.getStatusCode());
        Log.info(responseEntity.getBody());
        String type = JsonUtils.extractJSON(responseEntity.getBody(), TYPE).toString();
        Log.info("End of FullOrderDetailsByWOR_id API Invocation");
        return type;
    }

    public OrderDetail getOrderDetailsBySFDC_id(String sfdc_id) {
        Log.info("Invoking getOrderDetailsBySFDC_id API to get Order Type details");
        Map<String, String> urlValues = new ConcurrentHashMap<>();
        urlValues.put(SFDC_ID, sfdc_id);

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + MESEndpoints.ORDER_DETAILS, String.class, urlValues);
        Log.info(responseEntity.getBody());
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Get getOrderDetailsBySFDC_id API returned error " + responseEntity.getStatusCode());

        String response = responseEntity.getBody();
        List<Map<String, String>> list = (List<Map<String, String>>) JsonUtils.extractJSON(response, ORDER_ITEMS);
        List<String> item_ids = new ArrayList<String>();
        for (Map details : list) {
            item_ids.add(details.get(SFDC_ID).toString());
        }
        OrderDetail orderDetail = new OrderDetail(JsonPath.read(response, ID), sfdc_id, JsonPath.read(response, TYPE), JsonPath.read(response, CONTAINER_CODE), item_ids);
        Log.info("End of getOrderDetailsBySFDC_id API Invocation");
        return orderDetail;
    }

    public String getVectorTubeDetailsById(String vectorTubeId) {
        Log.info("Invoking Search vector nodes API");
        String request = environment.getProperty(SEARCH_VECTOR_REQUEST);
        request = JsonUtils.updateJSONString(request, IDENTIFIER, vectorTubeId);
        Log.info("Request: " + request);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(propertyUtil.getMesUrl() + MESEndpoints.SEARCH_VECTOR_NODES, request, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Invoking Search vector nodes API returned error " + responseEntity.getStatusCode());
        String parent_id = responseEntity.getBody().toString().replace("\"", "");
        Log.info("Completed Invoking Search vector nodes API");

        Log.info("Invoking Insert Vector Tube API");

        String InsertRequest = environment.getProperty(INSERT_VECTOR_TUBE_REQUEST);
        String sampleId = "Test_" + GenericUtil.getUniqueNumeric(10);
        InsertRequest = JsonUtils.updateJSONString(InsertRequest, SAMPLE_NAMES, Arrays.asList(sampleId));
        Log.info("Insert VectorTube Request: " + InsertRequest);
        Map<String, String> urlValues = new ConcurrentHashMap<>();
        urlValues.put(PARENT_ID, parent_id);
        responseEntity = testRestTemplate.postForEntity(propertyUtil.getMesUrl() + MESEndpoints.INSERT_VECTOR_TUBE, InsertRequest, String.class, urlValues);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Invoking Insert Vector Tube API returned error " + responseEntity.getStatusCode());
        Log.info(responseEntity.getBody());
        List<Map<String, String>> list = (List<Map<String, String>>) JsonUtils.extractJSON(responseEntity.getBody(), "$");
        String tube_barcode = null;
        for (Map tube :
                list) {
            if (tube.get("sample_name").toString().equalsIgnoreCase(sampleId)) {
                tube_barcode = tube.get(TUBE_BARCODE).toString();
            }
        }

        /*Log.info("Invoking Get Vector Details API");
        Map<String, String> urlValues = new ConcurrentHashMap<>();
        urlValues.put(PARENT_ID, parent_id);
        responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + MESEndpoints.VECTOR_NODE_DETAILS, String.class, urlValues);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Invoking Get Vector Details API returned error " + responseEntity.getStatusCode());
        Log.info(responseEntity.getBody());
        List<Map<String, String>> list = (List<Map<String, String>>) JsonUtils.extractJSON(responseEntity.getBody(), TUBE_LOTS);
        String tube_barcode = null;
        for (Map tube :
                list) {
            tube_barcode = tube.get(TUBE_BARCODE).toString();
        }*/
        return tube_barcode;
    }

    public void insertPlateToVINRegisterVectorToTube(String tube_barcode) {
        Log.info("Invoking Insert Plate To VINRegisterVectorToTube API");
        String request = environment.getProperty(INSERT_PLATE_TO_VIN_REGISTER_REQUEST);
        request = JsonUtils.updateJSONString(request, "$.items[0]" + BARCODE, tube_barcode);
        request = JsonUtils.updateJSONString(request, "$.items[0]" + REFERENCE, tube_barcode);
        request = JsonUtils.updateJSONString(request, "$.items[0]" + DESTINATION_BUCKET_ID, propertyUtil.getVectorInsertionDestinationBucketId());
        Log.info("Request: " + request);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(propertyUtil.getMesUrl() + MESEndpoints.ITEM_TRANSITIONS, request, String.class);
        if (responseEntity.getStatusCodeValue() != 201)
            Assert.fail("Invoking Insert Plate To VINRegisterVectorToTube API error " + responseEntity.getStatusCode());
        Log.info(responseEntity.getBody());
        Log.info("Invoking Insert Plate To VINRegisterVectorToTube API Completed..");
    }

    public void movePlateToPLSVectorHitpicking(String barcode) {
        Log.info("Invoking Insert Plate To VINRegisterVectorToTube API");
        String request = environment.getProperty(MOVE_PLATE_TO_PLS_VECTOR_HITPICKING);
        request = JsonUtils.updateJSONString(request, "$.items[0]" + REFERENCE, barcode);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(propertyUtil.getMesUrl() + MESEndpoints.ITEM_TRANSITIONS, request, String.class);
        if (responseEntity.getStatusCodeValue() != 201)
            Assert.fail("Invoking MovePlateToPLSVectorHitpicking API error " + responseEntity.getStatusCode());
        Log.info(responseEntity.getBody());
        Log.info("Invoking Invoking MovePlateToPLSVectorHitpicking API Completed..");
    }

    public List<String> getStorageContainerBarcodes() {
        Log.info("Invoking GET storage container barcodes API");
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getMesUrl() + MESEndpoints.GET_STORAGE_CONTAINER_BARCODES, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Invoking GET storage container barcodes API failed with error " + responseEntity.getStatusCode());
        List<String> rackBarcodes = new ArrayList<>();
        List<Map<String, String>> list = (List<Map<String, String>>) JsonUtils.extractJSON(responseEntity.getBody(), "$.root.children[0].children");
        for (Map racks :
                list) {
            List<Map<String, String>> rackList = (List<Map<String, String>>) racks.get(CHILDREN);
            for (Map rack :
                    rackList) {
                rackBarcodes.add(rack.get(BARCODE).toString());
            }
        }
        return rackBarcodes;
    }

}
