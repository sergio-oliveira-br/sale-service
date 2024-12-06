package com.alucontrol.saleservice.client;

import com.alucontrol.saleservice.model.FinanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "finance-service", url = "http://localhost:8083")
public interface FinanceClient {

    @PostMapping("/api/v1/finance/income")
    void addOfIncome (@RequestBody FinanceDTO financeDTO);
}
