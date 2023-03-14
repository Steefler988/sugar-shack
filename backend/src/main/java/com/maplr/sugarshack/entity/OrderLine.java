package com.maplr.sugarshack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderLine {
    @Id
    @Column(nullable = false)
    private String productId;
    private int qty;
}

