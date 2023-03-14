package com.maplr.sugarshack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "catalogue_item")
public class CatalogueItem {

    @Id
    @Column(nullable = false)
    private String id;
    private String name;
    private String image;
    private int maxQty;
    private double price;
    @Enumerated(EnumType.STRING)
    private Etype type;

}


