package com.alucontrol.saleservice.config;

import com.alucontrol.saleservice.tracking.RequestLoggingInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer {

    // To ensure that RequestLoggingInterceptor is applied to all requests,
    // register it in the application configuration.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggingInterceptor());
    }
}
