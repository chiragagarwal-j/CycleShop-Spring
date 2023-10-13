package com.cycles.rest.dto;

import lombok.Data;

@Data
public class CartUpdateRequest {
    private long cycleId;
    private int newQuantity;
}
