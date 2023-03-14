package com.maplr.sugarshack.dto;

import lombok.Data;

@Data
public class OrderLineDto {
    private String productId;
    private int qty;
}

