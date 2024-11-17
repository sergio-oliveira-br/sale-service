package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.client.InventoryClient;
import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.service.business.CreateSalesService;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/sales")
public class CreateSalesController {

    private final CreateSalesService createSalesService;
    private final InventoryClient inventoryClient;



    @Autowired
    public CreateSalesController(CreateSalesService createSalesService, InventoryClient inventoryClient) {
        this.createSalesService = createSalesService;
        this.inventoryClient = inventoryClient;
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Sale> createSale(@PathVariable("productId") Long productId,
                                                   @RequestParam("requestedQuantity") int requestedQuantity,
                                                   @RequestBody Sale sale) {

        Boolean hasStock = inventoryClient.checkInventory(productId, requestedQuantity);
        if (hasStock && requestedQuantity > 0) {
            LogUtil.info("O produto esta disponivel na quantidade desejada");
            createSalesService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
        }
        else {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
