package com.alucontrol.saleservice.client;

import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "customer-service", url = "http://localhost:8082")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    String findCustomerNameById(@PathVariable Long id);


    @GetMapping()
    List<CustomerDTO> findCustomerName();

    }

