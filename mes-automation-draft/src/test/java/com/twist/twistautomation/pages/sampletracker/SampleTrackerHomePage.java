package com.twist.twistautomation.pages.sampletracker;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.components.sampletracker.base.SampleTrackerComponent;
import com.twist.twistautomation.pages.base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class SampleTrackerHomePage extends BasePage {

    @Autowired
    SampleTrackerComponent sampleTrackerComponent;

    public SampleTrackerComponent getSampleTrackerComponent(){
        return sampleTrackerComponent;
    }
}
