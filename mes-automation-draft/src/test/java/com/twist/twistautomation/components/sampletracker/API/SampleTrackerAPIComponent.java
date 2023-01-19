package com.twist.twistautomation.components.sampletracker.API;

import com.twist.twistautomation.props.STEndpoints;
import com.twist.twistautomation.utils.CsvUtils;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.twist.twistautomation.constants.IConstants.*;

@Lazy
@Component
public class SampleTrackerAPIComponent {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    Environment environment;

    @Autowired
    PropertyUtil propertyUtil;


    String plateInfo;
    String transformPreviewResponse;
    String csvContent;

    public String invokeTransformId() {
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.TRANSFORM_IDS, null, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("SampleTracker TransformId API is failed with status: " + responseEntity.getStatusCode());
        String transformId = responseEntity.getBody().trim();
        return transformId;
    }

    public void verifyBarcodeValidThisStep(String barcode) {
        String endpoint = STEndpoints.CHECK_PLATE_FOR_STEP.replace("barcode", barcode);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getSampleTrackerUrl() + endpoint, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Barcode is Not valid for this step, Error from server is: " + responseEntity.getBody());
    }

    public void fetchBasicPlateInfo(String barcode) {
        String endpoint = STEndpoints.BASIC_PLATE_INFO.replace("barcode", barcode);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(propertyUtil.getSampleTrackerUrl() + endpoint, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("BasicPlateInfo API returned error: " + responseEntity.getBody());
        plateInfo = responseEntity.getBody();
        Log.info("PlateInfo: " + plateInfo);
    }

    public String invokeTransformPreview(String barcode, String sampleTrackerStage, List<String> tubeIds) {

        String request = null;
        ResponseEntity<String> responseEntity = null;
        if (Arrays.asList(ECR_PCR_UPLOAD_QUANT_DATA, PCA_PCR_UPLOAD_QUANT_DATA, PLS_VECTOR_PLATE_RETRIEVAL).contains(sampleTrackerStage)) {
            request = environment.getProperty(sampleTrackerStage.equalsIgnoreCase(PCA_PCR_UPLOAD_QUANT_DATA) ? TRANSFORM_PREVIEW_PCA_REQUEST : TRANSFORM_PREVIEW_ECR_REQUEST);
            if(sampleTrackerStage.equalsIgnoreCase(PLS_VECTOR_PLATE_RETRIEVAL))
                request = environment.getProperty(TRANSFORM_PREVIEW_PLS_REQUEST_TEMPLATE_130);
            request = JsonUtils.updateJSONString(request, "$.sources[0].details.id", barcode);
            request = JsonUtils.updateJSONString(request, "$.sources[0].details.parentPlates", JsonUtils.extractJSON(plateInfo, "$.parentPlates"));
            request = JsonUtils.updateJSONString(request, "$.sources[0].details.plateDetails", JsonUtils.extractJSON(plateInfo, "$.plateDetails"));
            request = JsonUtils.updateJSONString(request, "$.sources[0].items", JsonUtils.extractJSON(plateInfo, "$.wells"));
        }
        if (Arrays.asList(PLS_TUBE_PLACEMENT_CONFIRMATION, VIN_REGISTER_TUBES_TO_PLATE).contains(sampleTrackerStage)) {
            request = environment.getProperty(sampleTrackerStage.equalsIgnoreCase(VIN_REGISTER_TUBES_TO_PLATE) ? TRANSFORM_PREVIEW_VIN_REQUEST : TRANSFORM_PREVIEW_PLS_REQUEST);
            request = JsonUtils.updateJSONString(request, "$.destinations[0].details.id", barcode);
            csvContent = CsvUtils.updatePlateScanFileAndGetData(barcode, tubeIds);
            request = request.replace("replace_file_content", csvContent);
        }
        request = request.replace("replace_barcode", barcode);
        Log.info("TransformPreview Request: " + request);
        if (sampleTrackerStage.equalsIgnoreCase(ECR_PCR_UPLOAD_QUANT_DATA)) {
            responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.ECR_TRANSFORM_PREVIEW, request, String.class);
        }
        if (sampleTrackerStage.equalsIgnoreCase(PCA_PCR_UPLOAD_QUANT_DATA)) {
            responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.TRANSFORM_PREVIEW, request, String.class);
        }
        if (sampleTrackerStage.equalsIgnoreCase(VIN_REGISTER_TUBES_TO_PLATE)) {
            responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.VIN_TRANSFORM_PREVIEW, request, String.class);
        }
        if (sampleTrackerStage.equalsIgnoreCase(PLS_TUBE_PLACEMENT_CONFIRMATION)) {
            responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.PLS_TRANSFORM_PREVIEW, request, String.class);
        }
        if (sampleTrackerStage.equalsIgnoreCase(PLS_VECTOR_PLATE_RETRIEVAL)) {
            responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.PLS_TRANSFORM_PREVIEW_TEMPLATE_130, request, String.class);
        }
        if (!sampleTrackerStage.equalsIgnoreCase(PLS_VECTOR_PLATE_RETRIEVAL) && responseEntity.getStatusCodeValue() != 200)
            Assert.fail("TransformPreview API returned error: " + responseEntity.getBody());
        transformPreviewResponse = responseEntity.getBody();
        Log.info("TransformPreview Response: " + transformPreviewResponse);
        return transformPreviewResponse;
    }

    public void invokeTransformSpecs(String barcode, String sampleTrackerStage) {
        String transformId = invokeTransformId();
        String barCodePrefix = barcode.substring(0, 5);
        String request = null;
        if (Arrays.asList(ECR_PCR_UPLOAD_QUANT_DATA, PCA_PCR_UPLOAD_QUANT_DATA).contains(sampleTrackerStage)) {
            request = environment.getProperty(sampleTrackerStage.equalsIgnoreCase(PCA_PCR_UPLOAD_QUANT_DATA) ? TRANSFORM_SPECS_PCA_REQUEST : TRANSFORM_SPECS_ECR_REQUEST);
            request = JsonUtils.updateJSONString(request, "$.plan.sources[0].details.id", barcode);
            request = JsonUtils.updateJSONString(request, "$.plan.sources[0].details.plateDetails", JsonUtils.extractJSON(plateInfo, "$.plateDetails"));
            request = JsonUtils.updateJSONString(request, "$.plan.operations", JsonUtils.extractJSON(transformPreviewResponse, "$.data"));
        }
        if (Arrays.asList(PLS_TUBE_PLACEMENT_CONFIRMATION, VIN_REGISTER_TUBES_TO_PLATE).contains(sampleTrackerStage)) {
            request = environment.getProperty(sampleTrackerStage.equalsIgnoreCase(VIN_REGISTER_TUBES_TO_PLATE) ? TRANSFORM_SPECS_VIN_REQUEST : TRANSFORM_SPECS_PLS_REQUEST);
            request = JsonUtils.updateJSONString(request, "$.plan.destinations[0].details.id", barcode);
            request = request.replace("replace_file_content", csvContent);
        }
        request = JsonUtils.updateJSONString(request, "$.plan.spec_id", transformId);
        request = request.replace("replace_barcode", barcode);
        Log.info("TransformSpec Request: " + request);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(propertyUtil.getSampleTrackerUrl() + STEndpoints.TRANSFORM_SPECS, request, String.class);
        if (responseEntity.getStatusCodeValue() != 201)
            Assert.fail("TransformSpec API returned error: " + responseEntity.getBody());
        Log.info("TransformSpec Response: " + responseEntity.getBody());
    }

}
