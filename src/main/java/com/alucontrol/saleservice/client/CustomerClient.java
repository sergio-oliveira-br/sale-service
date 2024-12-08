package com.alucontrol.saleservice.client;

import com.alucontrol.saleservice.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "customer-service", url = "http://localhost:8082")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    String findCustomerNameById(@PathVariable Long id);

    @GetMapping("/api/v1/customers")
    List<CustomerDTO> findCustomerName();

    @PutMapping("/api/v1/customers/update/{id}")
    CustomerDTO updateCustomer(@PathVariable Long id, @RequestParam BigDecimal amount);

}

