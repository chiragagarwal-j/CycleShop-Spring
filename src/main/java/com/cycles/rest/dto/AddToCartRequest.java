package com.cycles.rest.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private long id;
    private int quantity;
}
