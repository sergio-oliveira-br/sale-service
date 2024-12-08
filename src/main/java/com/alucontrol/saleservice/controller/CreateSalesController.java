package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.client.FinanceClient;
import com.alucontrol.saleservice.client.InventoryClient;
import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.exceptions.InsufficientStockException;
import com.alucontrol.saleservice.model.FinanceDTO;
import com.alucontrol.saleservice.service.business.CreateSalesService;
import com.alucontrol.saleservice.service.external.CustomerService;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/sales")
public class CreateSalesController {

    private final CreateSalesService createSalesService;


    @Autowired
    public CreateSalesController(CreateSalesService createSalesService) {

        this.createSalesService = createSalesService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Sale> createSale(@PathVariable Long productId,
                                           @RequestBody Sale sale) {

        return ResponseEntity.status(HttpStatus.CREATED).body(createSalesService.saveSale(sale));
    }
}
