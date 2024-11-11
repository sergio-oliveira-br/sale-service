package com.alucontrol.saleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class InventoryRequest {

    private final UUID id;
    private final Integer quantity;
    private final String name;
}
