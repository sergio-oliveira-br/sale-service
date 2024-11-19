package com.alucontrol.saleservice.client;

import com.alucontrol.saleservice.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    String getCustomerNameById(@PathVariable Long id);


    @GetMapping("/api")
    List<CustomerDTO> getCustomerName();

}
