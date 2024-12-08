package com.alucontrol.saleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor 'amount' da venda deve ser informado")
    private BigDecimal amount;

    private int quantitySold;
    private Long customerId;
    private String customerName;
    private Long productId;

    @CreationTimestamp
    private LocalDateTime createAt;

}
