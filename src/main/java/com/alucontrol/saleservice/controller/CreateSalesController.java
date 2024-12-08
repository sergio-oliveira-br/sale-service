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
    private final InventoryClient inventoryClient;
    private final FinanceClient financeClient;
    private final CustomerService customerService;

    @Autowired
    public CreateSalesController(CreateSalesService createSalesService,
                                 InventoryClient inventoryClient,
                                 FinanceClient financeClient,
                                 CustomerService customerService) {

        this.createSalesService = createSalesService;
        this.inventoryClient = inventoryClient;
        this.financeClient = financeClient;
        this.customerService = customerService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Sale> createSale(@PathVariable("productId") Long productId,
                                                   @RequestParam("requestedQuantity") int requestedQuantity,
                                                   @RequestParam BigDecimal amount,
                                                   @RequestBody Sale sale) {

        // Request for inventory service: check stock availability
        Boolean hasStock = inventoryClient.checkInventory(productId, requestedQuantity);
        if (hasStock && requestedQuantity > 0) {
            LogUtil.info("O produto esta disponivel na quantidade desejada");

            // Request for inventory service: decrease stock
            inventoryClient.decreaseStock(productId, requestedQuantity);

            // Request for inventory service: increase sold qty field
            inventoryClient.increaseSold(productId, requestedQuantity);

            // Request for finance service: send the amout to finance service
            FinanceDTO financeDTO = new FinanceDTO(amount); // Criação do DTO
            financeClient.addOfIncome(financeDTO); // Enviar o DTO

            // Reqyest for customer service: send the amout to increase the customer total spent
            customerService.updateCustomerTotalSpent(sale.getCustomerId(), amount);

            // Internal service: save the details of the sale in the local database
            createSalesService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
        }
        else {
          throw new InsufficientStockException("A venda não pode ser concluída porque não há quantidade suficiente em estoque");
        }
    }
}
