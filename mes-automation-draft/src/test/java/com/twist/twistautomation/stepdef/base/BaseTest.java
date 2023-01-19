package com.twist.twistautomation.stepdef.base;

import com.twist.twistautomation.constants.IConstants;
import com.twist.twistautomation.utils.PropertyUtil;
import com.twist.twistautomation.utils.DriverUtil;
import com.twist.twistautomation.utils.WebDriverUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseTest implements IConstants {

    @Autowired
    protected DriverUtil driverUtil;
    @Autowired
    protected WebDriverUtil webDriverUtil;
    @Autowired
    protected PropertyUtil propertyUtil;


}
