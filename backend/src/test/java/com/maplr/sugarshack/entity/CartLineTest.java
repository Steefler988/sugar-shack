package com.maplr.sugarshack.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartLineTest {
    @Test
    public void testConstructorAndGetters() {
        String productId = "123";
        String name = "Maple Syrup";
        String image = "syrup.png";
        double price = 10.0;
        int qty = 2;

        CartLine cartLine = new CartLine(productId, name, image, price);
        cartLine.setQty(qty);

        Assertions.assertEquals(productId, cartLine.getProductId());
        Assertions.assertEquals(name, cartLine.getName());
        Assertions.assertEquals(image, cartLine.getImage());
        Assertions.assertEquals(price, cartLine.getPrice());
        Assertions.assertEquals(qty, cartLine.getQty());
    }
}
