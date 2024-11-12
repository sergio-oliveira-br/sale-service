package com.alucontrol.saleservice.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreateSaleService {

    private final WebClient webClient;

    @Autowired
    public CreateSaleService(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder
            .baseUrl("http://localhost:8081/api/v1/products")  // URL do inventory-service
            .build();
    }
}
