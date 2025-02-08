package com.zwap.api.proxy_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final Environment env;

    public WebClientConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public WebClient webClient() {
        String baseUrl = env.getProperty("POCKET_URL");
        String apiKey = env.getProperty("POCKET_KEY");
        if(baseUrl == null || apiKey == null)
            throw new IllegalArgumentException("Environment variables are required");
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", apiKey)
                .build();
    }
}
