package com.alucontrol.saleservice.controller.external;

import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.service.external.CustomerService;
import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request-customer-service")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer-name-by-id/{id}")
    public ResponseEntity<String> requestCustomerNameById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerNameById(id));
    }

    @GetMapping("/all-customer-data")
    public ResponseEntity<List<CustomerDTO>> requestAllCustomerData() {
        return ResponseEntity.ok(customerService.getAllCustomersName());
    }
}
