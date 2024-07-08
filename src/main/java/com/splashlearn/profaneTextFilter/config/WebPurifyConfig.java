package com.splashlearn.profaneTextFilter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebPurifyConfig {

    @Value("${web-purify.api.key}")
    private String apiKey;

    @Value("${web-purify.api.url}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
