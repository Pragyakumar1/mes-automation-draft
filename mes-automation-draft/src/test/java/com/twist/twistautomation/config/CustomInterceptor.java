package com.twist.twistautomation.config;

import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.props.STEndpoints;
import com.twist.twistautomation.utils.PropertyUtil;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Lazy
@Component
public class CustomInterceptor implements ClientHttpRequestInterceptor {

    public Scenario scenario;

    @Autowired
    @Qualifier("MESToken")
    String sampleTrackerToken;

    @Autowired
    PropertyUtil propertyUtil;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        if (!httpRequest.getURI().toString().contains(IConstants.RANDOM_SEQUENCE_GENERATOR)) {
            httpRequest.getHeaders().remove(HttpHeaders.CONTENT_TYPE);
            httpRequest.getHeaders().remove(HttpHeaders.ACCEPT);
            httpRequest.getHeaders().add(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
            httpRequest.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, IConstants.JWT_TOKEN);
        }
        if (httpRequest.getURI().toString().contains(propertyUtil.getSampleTrackerUrl())) {
            httpRequest.getHeaders().remove(HttpHeaders.AUTHORIZATION);
            httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, IConstants.BEARER + sampleTrackerToken);
        }
        if (httpRequest.getURI().toString().contains(STEndpoints.TRANSFORM_SPECS)) {
            httpRequest.getHeaders().add(STEndpoints.TRANSFORM_EXECUTION_HEADER_KEY, STEndpoints.TRANSFORM_EXECUTION_HEADER_VALUE);
        }

        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        if (null != scenario) {
            scenario.log("URL to Invoke the service: " + httpRequest.getURI());
            scenario.log("Headers used to Invoke the service: " + httpRequest.getHeaders());

        }
        return response;
    }
}
