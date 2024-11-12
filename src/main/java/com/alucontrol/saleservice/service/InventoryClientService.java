package com.alucontrol.saleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClientService {

    private final WebClient webClient;

    @Autowired
    public InventoryClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8081/api/v1/products")  // URL do inventory-service
                .build();
    }

    public Mono<Void> getInventoryByProductId(int productId) {
        return webClient.get()
                .uri("/{productId}", productId)
                .retrieve()
                .bodyToMono(void.class);
    }
}
