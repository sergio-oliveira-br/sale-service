package com.alucontrol.saleservice.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {

    private final WebClient webClient;

    @Autowired
    public InventoryClient(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder
            .baseUrl("http://localhost:8081/api/v1/products")  // URL do inventory-service
            .build();
    }

    //This will ensure that the quantity requested is in stock
    public Mono<Boolean> hasStock(Long productId, int requestedQuantity) {

        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/check-inventory/product-id/{productId}")
                .queryParam("requestedQuantity", requestedQuantity)
                .build(productId))
            .retrieve()
            .bodyToMono(boolean.class)
            .doOnNext(stock -> System.out.println("Resposta do InventoryService - Estoque suficiente: " + stock));
    }
}
