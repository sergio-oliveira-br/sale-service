package com.alucontrol.saleservice.service.external;

import com.alucontrol.saleservice.client.CustomerClient;
import com.alucontrol.saleservice.model.CustomerDTO;
import com.alucontrol.saleservice.tracking.LogUtil;
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
    public String getCustomerNameById(Long id) {
        LogUtil.info("Calling Customer Cliente to get customer name by Id: " + id);
        return customerClient.findCustomerNameById(id);
    }

    public List<CustomerDTO> getAllCustomersName() {

        return customerClient.findCustomerName();
    }
}
