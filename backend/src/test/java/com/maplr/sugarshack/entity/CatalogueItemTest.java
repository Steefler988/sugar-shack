package com.maplr.sugarshack.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogueItemTest {

    private CatalogueItem catalogueItem;

    @BeforeEach
    void setUp() {
        catalogueItem = new CatalogueItem();
        catalogueItem.setId("1");
        catalogueItem.setName("Maple Syrup");
        catalogueItem.setImage("maple_syrup.jpg");
        catalogueItem.setMaxQty(10);
        catalogueItem.setPrice(10.0);
        catalogueItem.setType(Etype.AMBER);
    }

    @Test
    void testGetId() {
        Assertions.assertEquals("1", catalogueItem.getId());
    }

    @Test
    void testGetName() {
        Assertions.assertEquals("Maple Syrup", catalogueItem.getName());
    }

    @Test
    void testGetImage() {
        Assertions.assertEquals("maple_syrup.jpg", catalogueItem.getImage());
    }

    @Test
    void testGetMaxQty() {
        Assertions.assertEquals(10, catalogueItem.getMaxQty());
    }

    @Test
    void testGetPrice() {
        Assertions.assertEquals(10.0, catalogueItem.getPrice());
    }

    @Test
    void testGetType() {
        Assertions.assertEquals(Etype.AMBER, catalogueItem.getType());
    }

}
