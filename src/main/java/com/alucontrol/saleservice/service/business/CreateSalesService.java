package com.alucontrol.saleservice.service.business;

import com.alucontrol.saleservice.repository.SaleRepository;
import com.alucontrol.saleservice.entity.Sale;
//import com.alucontrol.saleservice.tracking.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSalesService {

    private final SaleRepository SaleRepository;

    @Autowired
    public CreateSalesService(SaleRepository saleRepository) {
        this.SaleRepository = saleRepository;
    }


    public Sale saveSale(Sale sale){

//        LogUtil.logDatabaseOperation("Venda salva com sucesso");
        return SaleRepository.save(sale);
    }
}
