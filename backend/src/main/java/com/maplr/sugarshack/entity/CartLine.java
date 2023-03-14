package com.maplr.sugarshack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CartLine {
    @Id
    @Column(nullable = false)
    private String productId;
    private String name;
    private String image;
    private double price;
    private int qty;

    public CartLine() {}

    public CartLine(String productId, String name, String image, double price) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
    }
}

