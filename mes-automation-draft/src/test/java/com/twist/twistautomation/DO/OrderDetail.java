package com.twist.twistautomation.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderDetail {
    String wor_id;
    String sfdc_id;
    String type;
    String container_code;
    List<String> item_ids;
}
