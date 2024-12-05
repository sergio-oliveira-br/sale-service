package com.alucontrol.saleservice.service.external;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.tracking.LogUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    // This service is responsible to get a unique customer name by id
    //@CircuitBreaker(name = "customerServiceCircuitBreaker", fallbackMethod = "fallbackGetCustomerNameById")
    public String getCustomerNameById(Long id) {
        LogUtil.info("Calling Customer Cliente to get customer name by Id: " + id);
        return customerClient.findCustomerNameById(id);
    }

//    // Fallback method para o getCustomerNameById
//    public String fallbackGetCustomerNameById(Long id, Throwable t) {
//        LogUtil.error("Error occurred while calling CustomerClient: " + t.getMessage());
//        return "Default Customer Name"; // Retorna um valor default em caso de falha
//    }

    //@CircuitBreaker(name = "customerServiceCircuitBreaker", fallbackMethod = "fallbackGetAllCustomersName")
    public List<CustomerDTO> getAllCustomersName() {
        return customerClient.findCustomerName();
    }


//    // Fallback method para o getAllCustomersName
//    public List<CustomerDTO> fallbackGetAllCustomersName(Throwable t) {
//        LogUtil.error("Error occurred while calling CustomerClient: " + t.getMessage());
//        return List.of(); // Retorna uma lista vazia em caso de falha
//    }
}
