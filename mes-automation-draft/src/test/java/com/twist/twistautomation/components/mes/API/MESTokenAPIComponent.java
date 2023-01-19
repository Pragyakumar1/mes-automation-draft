package com.twist.twistautomation.components.mes.API;

import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.props.STEndpoints;
import com.twist.twistautomation.utils.JsonUtils;
import com.twist.twistautomation.utils.PropertyUtil;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Lazy
@Component
public class MESTokenAPIComponent {

    @Autowired
    PropertyUtil propertyUtil;

    @Bean("MESToken")
    public String loginToMES() {
        RestTemplate restTemplate = new RestTemplate();
        String request = IConstants.MES_LOGIN_REQUEST;
        request = JsonUtils.updateJSONString(request, "$.identifier", propertyUtil.getSampleTrackerUserName());
        request = JsonUtils.updateJSONString(request, "$.password", propertyUtil.getSampleTrackerPassword());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(propertyUtil.getMesUrl() + STEndpoints.AUTH, request, String.class);
        if (responseEntity.getStatusCodeValue() != 200)
            Assert.fail("Login to SampleTracker is failed with status: " + responseEntity.getStatusCode());
        String response = responseEntity.getBody();
        String token = JsonUtils.extractJSON(response, "$.token").toString();
        return token;
    }
}
