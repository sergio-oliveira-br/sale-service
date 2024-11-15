package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.service.business.CreateSaleService;
import com.alucontrol.saleservice.service.client.InventoryClient;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/product/{productId}")
    public Mono<ResponseEntity<String>> createSale(@PathVariable("productId") Long productId,
                                                   @RequestParam("requestedQuantity") int requestedQuantity,
                                                   @RequestBody Sale sale) {

        LogUtil.info("Iniciando o processo de venda");

        return inventoryClient.hasStock(productId, requestedQuantity)
            .flatMap(hasStock -> {
                if (hasStock) {
                    createSaleService.saveSale(sale);
                    return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("Venda criada com sucesso."));
                }
                else {
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar a venda. Estoque insuficiente."));
                }
            }
        );
    }
}
