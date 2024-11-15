package com.alucontrol.saleservice.service.business;

import com.alucontrol.saleservice.entity.Sale;
import com.alucontrol.saleservice.exceptions.ResourceNotFoundException;
import com.alucontrol.saleservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadSalesService {

    private final SaleRepository saleRepository;

    @Autowired
    public ReadSalesService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    // This will find a sale by using the "ID" as reference,
    public Sale findSaleById(Long id) {

        Optional<Sale> optionalSale = saleRepository.findById(id);
        if(optionalSale.isPresent()){
            return optionalSale.get();
        }
        throw new ResourceNotFoundException("Venda Id: " + id + " não encontrada");
    }


    // This will return all sales that exists into DB
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }
}
