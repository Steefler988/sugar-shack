package com.maplr.sugarshack.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MapleSyrupTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        MapleSyrup mapleSyrup = new MapleSyrup();
        mapleSyrup.setId("001");
        mapleSyrup.setName("Maple syrup");
        mapleSyrup.setDescription("This is a delicious maple syrup.");
        mapleSyrup.setImage("image.png");
        mapleSyrup.setPrice(10.99);
        mapleSyrup.setStock(50);
        mapleSyrup.setType(Etype.AMBER);

        // Act
        String id = mapleSyrup.getId();
        String name = mapleSyrup.getName();
        String description = mapleSyrup.getDescription();
        String image = mapleSyrup.getImage();
        double price = mapleSyrup.getPrice();
        int stock = mapleSyrup.getStock();
        Etype type = mapleSyrup.getType();

        // Assert
        assertEquals("001", id);
        assertEquals("Maple syrup", name);
        assertEquals("This is a delicious maple syrup.", description);
        assertEquals("image.png", image);
        assertEquals(10.99, price, 0.01);
        assertEquals(50, stock);
        assertEquals(Etype.AMBER, type);
    }
}