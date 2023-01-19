package com.twist.twistautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;


@CucumberOptions(
        glue = "com.twist.twistautomation.stepdef",
        features = "classpath:features",
        plugin = {
                "pretty", "html:target/cucumber-html-reports",
                "json:target/cucumber-reports/cucumber-html-reports/cucumber.json",
                "junit:target/junit-cucumber-reports/cukejunit.xml"
        }
)
public class TestRunner  extends AbstractTestNGCucumberTests {

    private static long duration;

    @BeforeClass
    public static void before() {
        duration = System.currentTimeMillis();
        System.out.println("Thread Id  | Scenario Num       | Step Count");
    }

    @AfterClass
    public static void after() {
        duration = System.currentTimeMillis() - duration;
        System.out.println("DURATION - " + duration);
    }
}
