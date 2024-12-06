package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.service.business.ReadSalesService;
import com.alucontrol.saleservice.service.external.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class ReadSalesController {

    private final ReadSalesService readSalesService;

    @Autowired
    public ReadSalesController(ReadSalesService readSalesService, CustomerService customerService) {

        this.readSalesService = readSalesService;
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

}
