package com.alucontrol.saleservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8080/api/v1/products")
public interface InventoryClient {

    @GetMapping("/check-inventory/product-id/{productId}")
    Boolean checkInventory(@PathVariable("productId") Long productId,
                           @RequestParam("requestedQuantity") int requestedQuantity);


    @PutMapping("/decrease-stock/product-id/{productId}")
    void decreaseStock(@PathVariable("productId") Long productId,
                       @RequestParam int requestedQuantity);


    @PutMapping("/increase-sold/product-id/{productId}")
    void increaseSold(@PathVariable Long productId,
                      @RequestParam int requestedQuantity);
}
