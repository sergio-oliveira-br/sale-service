package com.alucontrol.saleservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8081")
public interface InventoryClient {

    @GetMapping("/api/v1/products/check-inventory/product-id/{productId}")
    Boolean checkInventory(@PathVariable Long productId,
                           @RequestParam int requestedQuantity);


    @PutMapping("/api/v1/products/decrease-stock/product-id/{productId}")
    void decreaseStock(@PathVariable Long productId,
                       @RequestParam int requestedQuantity);


    @PutMapping("/api/v1/products/increase-sold/product-id/{productId}")
    void increaseSold(@PathVariable Long productId,
                      @RequestParam int requestedQuantity);
}
