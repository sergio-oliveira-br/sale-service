package com.alucontrol.saleservice.service.business;

import com.alucontrol.saleservice.client.FinanceClient;
import com.alucontrol.saleservice.client.InventoryClient;
import com.alucontrol.saleservice.exceptions.InsufficientStockException;
import com.alucontrol.saleservice.model.FinanceDTO;
import com.alucontrol.saleservice.repository.SaleRepository;
import com.alucontrol.saleservice.entity.Sale;
//import com.alucontrol.saleservice.tracking.LogUtil;
import com.alucontrol.saleservice.service.external.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSalesService {

    private final SaleRepository saleRepository;
    private final InventoryClient inventoryClient;
    private final FinanceClient financeClient;
    private final CustomerService customerService;

    @Autowired
    public CreateSalesService(SaleRepository saleRepository,
                              InventoryClient inventoryClient,
                              FinanceClient financeClient,
                              CustomerService customerService) {

        this.saleRepository = saleRepository;
        this.inventoryClient = inventoryClient;
        this.financeClient = financeClient;
        this.customerService = customerService;
    }


    // This method clearly reflects the business flow,
    // while implementation details are hidden in the auxiliary methods.
    public Sale saveSale (Sale sale) {

        validateStock(sale);
        updateInventory(sale);
        recordFinance(sale);
        updateCustomer(sale);

        return persistSale(sale);
    }


    // Request for inventory service: check stock availability (auxiliar method)
    private void validateStock(Sale sale) {

        if (!inventoryClient.checkInventory(sale.getProductId(), sale.getQuantitySold())) {
            throw new InsufficientStockException("Estoque insuficiente para o produto: " + sale.getProductId());
        }
    }


    // Request for inventory service: decrease stock and increase sold qty
    private void updateInventory (Sale sale) {
        inventoryClient.decreaseStock(sale.getProductId(), sale.getQuantitySold());
        inventoryClient.increaseSold(sale.getProductId(), sale.getQuantitySold());
    }


    // Request for finance service: send the amout to finance service
    private void recordFinance (Sale sale) {

        FinanceDTO financeDTO = new FinanceDTO(sale.getAmount()); //build an object
        financeClient.addOfIncome(financeDTO); //sent the DTO
    }


    // Request for customer service: send the amout to increase the customer total spent
    private void updateCustomer (Sale sale) {
        customerService.updateCustomerTotalSpent(sale.getCustomerId(), sale.getAmount());
    }


    // Create a new line in DB using CRUD
    private Sale persistSale (Sale sale) {
        return saleRepository.save(sale);
    }
}
