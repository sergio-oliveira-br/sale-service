package com.alucontrol.saleservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // This configure will make possible the WebConfig
    // that it can be injected into any class that needs to make HTTP requests.
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
