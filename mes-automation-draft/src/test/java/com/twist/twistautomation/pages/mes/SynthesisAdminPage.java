package com.twist.twistautomation.pages.mes;

import com.twist.twistautomation.annotation.Page;
import com.twist.twistautomation.pages.base.BasePage;
import com.twist.twistautomation.utils.ByT;

@Page
public class SynthesisAdminPage extends BasePage {

    private ByT labelLayers = ByT.xpath("//div[contains(text(),'%s')]//p[contains(text(),'Layer')]");

    public int getLayerCount(String srnNumber) {
        int layer = 0;
        String layerText = driver.getText(labelLayers.format(srnNumber));
        if (!layerText.isEmpty()) {
            layer = Integer.parseInt(layerText.split("/")[1]) - 1;
        }
        return layer;
    }
}