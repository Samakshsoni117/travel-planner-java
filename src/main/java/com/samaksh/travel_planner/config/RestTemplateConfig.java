package com.samaksh.travel_planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class to provide a RestTemplate bean.
 * This allows Spring to manage the lifecycle of the RestTemplate
 * and lets us inject it wherever we need it in our application.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
