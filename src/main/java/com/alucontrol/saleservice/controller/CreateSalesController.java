package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.service.business.CreateSaleService;
import com.alucontrol.saleservice.service.client.InventoryClient;
import com.alucontrol.saleservice.tracking.LogUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/sales")
public class CreateSalesController {

    private final CreateSaleService createSaleService;
    private final InventoryClient inventoryClient;

    @Autowired
    public CreateSalesController(CreateSaleService createSaleService, InventoryClient inventoryClient) {
        this.createSaleService = createSaleService;
        this.inventoryClient = inventoryClient;
    }

    @PostMapping("/verify-availability-product-id/{productId}")
    public Mono<ResponseEntity<String>> createSale(@PathVariable("productId") Long productId,
                                                   @RequestParam("requestedQuantity") int requestedQuantity) {
        
        return inventoryClient.hasStock(productId, requestedQuantity)
                .then(Mono.just(ResponseEntity.ok("Venda processada com sucesso")))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }
}
