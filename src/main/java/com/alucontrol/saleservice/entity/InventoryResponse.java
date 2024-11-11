package com.alucontrol.saleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InventoryResponse {

    private final UUID id;
    private final String name;
    private final int qtyAvailable;
}
