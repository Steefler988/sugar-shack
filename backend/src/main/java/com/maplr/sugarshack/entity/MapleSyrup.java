package com.maplr.sugarshack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MapleSyrup {
    @Id
    @Column(nullable = false)
    private String id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int stock;
    @Enumerated(EnumType.STRING)
    private Etype type;

}

