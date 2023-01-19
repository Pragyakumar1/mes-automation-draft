package com.twist.twistautomation.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertyUtil {

    @Value("${app.eCommUrl}")
    String eCommerceUrl;
    @Value("${app.eComusername}")
    String eCommerceUserName;
    @Value("${app.eCompassword}")
    String eCommercePassword;
    @Value("${auto.browser}")
    String browser;
    @Value("${auto.timeout}")
    String timeout;
    @Value("${app.mesUrl}")
    String mesUrl;
    @Value("${app.mesUsername}")
    String mesUserName;
    @Value("${app.mesPassword}")
    String mesPassword;
    @Value("${app.sfdcUrl}")
    String sfdcUrl;
    @Value("${app.sfdcUserName}")
    String sfdcUserName;
    @Value("${app.sfdcPassword}")
    String sfdcPassword;

    @Value("${app.sampleTrackerUserName}")
    String sampleTrackerUserName;

    @Value("${app.sampleTrackerUserBarCode}")
    String sampleTrackerUserBarCode;

    @Value("${app.sampleTrackerPassword}")
    String sampleTrackerPassword;

    @Value("${synth.url}")
    String synthUrl;

    @Value("${sample.tracker.url}")
    String sampleTrackerUrl;

    @Value("${tapi.url}")
    String tapiUrl;

    @Value("${mes.vector.insertion.destination.bucket.id}")
    String vectorInsertionDestinationBucketId;

    @Value("${sample.tracker.url.devplayground}")
    String devPlaygoundSampleTrackerUrl;

    /*@Value("${create.synthesis.run.resource}")
    String createSynthesisRunResource;
    @Value("${create.add.started.event.resource}")
    String createAddStartedEvenResource;
    @Value("${create.layer.completion.resource}")
    String createLayerCompletionResource;
    @Value("${create.add.completed.event.resource}")
    String createAddCompleteEventResource;
    @Value("${create.chp.deprotection.submission.resource}")
    String createChpDeprotectionSubmissionResource;
    @Value("${get.chp.deprotection.ready.plates.resource}")
    String getChpDeprotectionReadyPlateResource;*/

}
