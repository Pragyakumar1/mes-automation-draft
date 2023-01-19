package com.twist.twistautomation.pages.mes;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.components.mes.ChipPlanningComponent;
import com.twist.twistautomation.components.mes.ForthComponent;
import com.twist.twistautomation.components.mes.QCReviewComponent;
import com.twist.twistautomation.components.mes.SynthesisAdminComponent;
import com.twist.twistautomation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class MESHomePage extends BasePage {


    private By mesHomeNavigationHeader = By.xpath("//button[@class='header-t-logo btn btn-secondary']");

    @Autowired
    ForthComponent forthComponent;

    @Autowired
    ChipPlanningComponent chipPlanningComponent;

    @Autowired
    SynthesisAdminComponent synthesisAdminComponent;

    @Autowired
    QCReviewComponent qcReviewComponent;

    public MESHomePage navigateToHomePage() throws Exception {
        driver.clickLocator(mesHomeNavigationHeader);
        return this;
    }

    public ForthComponent getForthComponent() {
        return forthComponent;
    }

    public ChipPlanningComponent getChipPlanningComponent() {
        return chipPlanningComponent;
    }

    public SynthesisAdminComponent getSynthesisAdminComponent() {
        return synthesisAdminComponent;
    }

    public QCReviewComponent getQcReviewComponent() {
        return qcReviewComponent;
    }
}
