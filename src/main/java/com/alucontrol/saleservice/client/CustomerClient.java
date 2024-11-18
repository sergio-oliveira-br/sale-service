package com.alucontrol.saleservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8082/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    String getCustomerNameById(@PathVariable("id") Long id);
}
