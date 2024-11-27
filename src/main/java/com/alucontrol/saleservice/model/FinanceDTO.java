package com.alucontrol.saleservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class FinanceDTO {
    private BigDecimal amount;
}
