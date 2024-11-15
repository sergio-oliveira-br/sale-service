package com.alucontrol.saleservice.controller;

import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.service.business.CreateSalesService;
import com.alucontrol.saleservice.service.client.InventoryClient;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/sales")
public class CreateSalesController {

    private final CreateSalesService createSalesService;
    private final InventoryClient inventoryClient;
    private final View error;

    @Autowired
    public CreateSalesController(CreateSalesService createSalesService, InventoryClient inventoryClient, View error) {
        this.createSalesService = createSalesService;
        this.inventoryClient = inventoryClient;
        this.error = error;
    }

    @PostMapping("/product/{productId}")
    public Mono<ResponseEntity<String>> createSale(@PathVariable("productId") Long productId,
                                                   @RequestParam("requestedQuantity") int requestedQuantity,
                                                   @RequestBody Sale sale) {

        LogUtil.info("Iniciando o processo de venda");

        return inventoryClient.hasStock(productId, requestedQuantity)
            .flatMap(hasStock -> {
                if (hasStock) {

                    return inventoryClient.reduceStock(productId, requestedQuantity).onErrorResume(error -> {
                        LogUtil.error("Error reducing stock: " + error.getMessage());
                        // Optionally, log the error or take alternative actions
                        return Mono.empty(); // Continue with sale creation despite stock reduction error (may need further handling)
                    })
                    .then(Mono.defer(() -> { // Defer execution of saveSale until stock reduction completes
                        createSalesService.saveSale(sale);
                        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("Venda criada com sucesso."));
                    }));

//                    createSalesService.saveSale(sale);
//                    return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("Venda criada com sucesso."));
                }
                else {
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar a venda. Estoque insuficiente."));
                }
            }
        );
    }
}
