package com.alucontrol.saleservice.service.external;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.tracking.LogUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    // This service is responsible to get a unique customer name by id
    @CircuitBreaker(name = "customerServiceCircuitBreaker", fallbackMethod = "fallbackGetCustomerNameById")
    public String getCustomerNameById(Long id) {
        return customerClient.findCustomerNameById(id);
    }

    // Fallback method para o getCustomerNameById
    public String fallbackGetCustomerNameById(Long id, Throwable throwable) {
        LogUtil.error("Ocorreu um erro ao chamar o CustomerClient");
        return "Retornando uma mensagem genérica informando a existencia da falha no getCustomerNameById()";
    }



    @CircuitBreaker(name = "customerServiceCircuitBreaker", fallbackMethod = "fallbackGetAllCustomersName")
    public List<CustomerDTO> getAllCustomersName() {
        return customerClient.findCustomerName();
    }

    // Fallback method para o getAllCustomersName
    public String fallbackGetAllCustomersName(Throwable t) {
        LogUtil.error("Ocorreu um erro ao chamar o CustomerClient: " + t.getMessage());
        return "Retornando uma mensagem genérica informando a existencia da falha no getAllCustomersName() ";
    }



    public CustomerDTO updateCustomerTotalSpent(Long id, BigDecimal amountSpent) {
        return customerClient.updateCustomer(id, amountSpent);
    }
}
