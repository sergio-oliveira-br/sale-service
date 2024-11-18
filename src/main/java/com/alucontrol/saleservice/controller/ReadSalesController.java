package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.service.business.ReadSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class ReadSalesController {

    private final ReadSalesService readSalesService;
   private final CustomerClient customerClient;

    @Autowired
    public ReadSalesController(ReadSalesService readSalesService,
                               CustomerClient customerClient) {

        this.readSalesService = readSalesService;
        this.customerClient = customerClient;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") Long id) {

        Sale sale = readSalesService.findSaleById(id);
        return ResponseEntity.ok(sale);
    }


    @GetMapping()
    public ResponseEntity<List<Sale>> getAllSales() {

        List<Sale> sales = readSalesService.findAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/customer-name/{id}")
    public ResponseEntity<String> getCustomerNameById(@PathVariable("id") Long id) {

        String customerName = customerClient.requestCustomerNameById(id);
        return ResponseEntity.ok().body(customerName);
    }
}
