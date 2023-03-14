package com.maplr.sugarshack.dto;

import com.maplr.sugarshack.entity.Etype;
import lombok.Data;

@Data
public class MapleSyrupDto {
    private String id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int stock;
    private Etype type;

}

