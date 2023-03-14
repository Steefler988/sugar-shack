package com.maplr.sugarshack.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderLineTest {
    private OrderLine orderLine;

    @BeforeEach
    public void setup() {
        orderLine = new OrderLine();
    }

    @Test
    public void testProductId() {
        String productId = "123";
        orderLine.setProductId(productId);
        Assertions.assertEquals(productId, orderLine.getProductId());
    }

    @Test
    public void testQty() {
        int qty = 10;
        orderLine.setQty(qty);
        Assertions.assertEquals(qty, orderLine.getQty());
    }
}