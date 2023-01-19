package com.twist.twistautomation.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.twist.twistautomation"})
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig {

    @Value("${data.config.pattern:data/*/*.yml}")
    private String dataConfigPattern;

    @Inject
    ConfigurableEnvironment env;

    @Autowired
    CustomInterceptor customInterceptor;

    @PostConstruct
    public void setup() throws IOException {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] mappingLocations = patternResolver.getResources(dataConfigPattern);
        for (Resource resource : mappingLocations) {
            env.getPropertySources().addFirst(new ResourcePropertySource(resource));
        }
    }

    @Bean
    public TestRestTemplate testRestTemplate() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getRestTemplate().getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(customInterceptor);
        restTemplate.getRestTemplate().setInterceptors(interceptors);

        return restTemplate;
    }


}
