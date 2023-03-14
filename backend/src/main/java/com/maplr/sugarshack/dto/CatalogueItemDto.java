package com.maplr.sugarshack.dto;

import com.maplr.sugarshack.entity.Etype;
import lombok.Data;

@Data
public class CatalogueItemDto {
    private String id;
    private String name;
    private String image;
    private int maxQty;
    private double price;
    private Etype type;
}
