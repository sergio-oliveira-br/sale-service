package com.alucontrol.saleservice.repository;

import com.alucontrol.saleservice.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}