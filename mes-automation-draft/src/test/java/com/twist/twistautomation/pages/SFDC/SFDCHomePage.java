package com.twist.twistautomation.pages.SFDC;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.components.sfdc.SfdcComponent;
import com.twist.twistautomation.pages.base.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class SFDCHomePage extends BasePage {

    @Autowired
    public SfdcComponent sfdcComponent;
}
