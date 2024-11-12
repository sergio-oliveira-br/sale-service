package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.service.InventoryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/sale")
public class InventoryClientController {

    private final InventoryClientService inventoryClientService;

    @Autowired
    public InventoryClientController(InventoryClientService inventoryClientService) {
        this.inventoryClientService = inventoryClientService;
    }

    @PostMapping("/check-inventory/{productId}")
    public Mono<ResponseEntity<String>> checkInventory(@PathVariable("productId") int productId) {
        return inventoryClientService.getInventoryByProductId(productId)
                .then(Mono.just(ResponseEntity.ok("Venda processada com Sucesso")))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }
}
