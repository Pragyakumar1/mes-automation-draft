package com.twist.twistautomation.stepdef;

import com.twist.twistautomation.config.AppConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = AppConfig.class)
public class CucumberSpringConfiguration {
}
