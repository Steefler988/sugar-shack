package com.maplr.sugarshack.dto;

import lombok.Data;

@Data
public class CartLineDto {
    private String productId;
    private String name;
    private String image;
    private double price;
    private int qty;

}
