package com.twist.twistautomation.components;

import com.twist.twistautomation.components.mes.API.MESAPIComponent;
import com.twist.twistautomation.components.sampletracker.API.SampleTrackerAPIComponent;
import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.utils.PropertyUtil;
import com.twist.twistautomation.DO.TestContext;
import com.twist.twistautomation.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractComponent implements IConstants {
    @Autowired
    public WebDriverUtil driver;
    @Autowired
    public TestContext testContext;
    @Autowired
    protected PropertyUtil propertyUtil;
    @Autowired
    protected SampleTrackerAPIComponent sampleTrackerAPIComponent;
    @Autowired
    protected MESAPIComponent mesapiComponent;
    public By spinner = By.xpath("//div[@class='twst-spinner-inner']");
    public By spinnerSampleTracker = By.xpath("//div[@class='twst-spinner twst-spec-updating-spinner ng-scope ng-isolate-scope']");

}
