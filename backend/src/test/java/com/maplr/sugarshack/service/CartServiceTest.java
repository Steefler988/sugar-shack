package com.maplr.sugarshack.service;

import com.maplr.sugarshack.entity.CartLine;
import com.maplr.sugarshack.entity.CatalogueItem;
import com.maplr.sugarshack.repository.CartLineRepository;
import com.maplr.sugarshack.repository.CatalogueItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartLineRepository cartLineRepository;

    @MockBean
    private CatalogueItemRepository catalogueItemRepository;

    private final String TEST_PRODUCT_ID = "test_product_id";
    private final String TEST_NAME = "test_name";
    private final String TEST_IMAGE = "test_image";
    private final double TEST_PRICE = 10.0;
    private final int TEST_MAX_QTY = 5;

    @Test
    void addToCart_whenProductExistsAndCartLineExists_thenIncrementQuantity() {
        CatalogueItem catalogueItem = new CatalogueItem();
        catalogueItem.setId(TEST_PRODUCT_ID);
        catalogueItem.setName(TEST_NAME);
        catalogueItem.setImage(TEST_IMAGE);
        catalogueItem.setPrice(TEST_PRICE);
        catalogueItem.setMaxQty(TEST_MAX_QTY);

        CartLine cartLine = new CartLine();
        cartLine.setProductId(TEST_PRODUCT_ID);
        cartLine.setQty(2);

        when(catalogueItemRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(catalogueItem));
        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(cartLine));
        when(cartLineRepository.save(any(CartLine.class))).thenReturn(null);

        cartService.addToCart(TEST_PRODUCT_ID);

        assertEquals(3, cartLine.getQty());
    }

    @Test
    void addToCart_whenProductDoesNotExist_thenThrowResponseStatusException() {
        when(catalogueItemRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> cartService.addToCart(TEST_PRODUCT_ID));
    }

    @Test
    void addToCart_whenMaxQtyIsReached_thenThrowResponseStatusException() {
        CatalogueItem catalogueItem = new CatalogueItem();
        catalogueItem.setId(TEST_PRODUCT_ID);
        catalogueItem.setName(TEST_NAME);
        catalogueItem.setImage(TEST_IMAGE);
        catalogueItem.setPrice(TEST_PRICE);
        catalogueItem.setMaxQty(TEST_MAX_QTY);

        CartLine cartLine = new CartLine();
        cartLine.setProductId(TEST_PRODUCT_ID);
        cartLine.setQty(TEST_MAX_QTY);

        when(catalogueItemRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(catalogueItem));
        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(cartLine));

        assertThrows(ResponseStatusException.class, () -> cartService.addToCart(TEST_PRODUCT_ID));
    }

    @Test
    public void testRemoveFromCart_whenCartLineExistsAndQtyIsGreaterThanOne_thenDecrementQuantity() {
        CartLine cartLine = new CartLine();
        cartLine.setProductId(TEST_PRODUCT_ID);
        cartLine.setQty(2);

        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(cartLine));
        when(cartLineRepository.save(any(CartLine.class))).thenReturn(null);

        cartService.removeFromCart(TEST_PRODUCT_ID);

        assertEquals(1, cartLine.getQty());
    }

    @Test
    public void testRemoveFromCart_whenCartLineExistsAndQtyIsEqualToOne_thenDeleteCartLine() {
        CartLine cartLine = new CartLine();
        cartLine.setProductId(TEST_PRODUCT_ID);
        cartLine.setQty(1);

        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(cartLine));

        cartService.removeFromCart(TEST_PRODUCT_ID);

        assertFalse(cartLineRepository.existsById(TEST_PRODUCT_ID));
    }

    @Test
    public void testRemoveFromCart_whenCartLineDoesNotExist_thenThrowResponseStatusException() {
        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> cartService.removeFromCart(TEST_PRODUCT_ID));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testAddToCart_whenProductExistsAndCartLineExists_thenIncrementQuantity() {

        int currentQty = 2;
        int expectedQty = currentQty + 1;

        CatalogueItem catalogueItem = new CatalogueItem();
        catalogueItem.setId(TEST_PRODUCT_ID);
        catalogueItem.setMaxQty(TEST_MAX_QTY);

        CartLine cartLine = new CartLine();
        cartLine.setProductId(TEST_PRODUCT_ID);
        cartLine.setQty(currentQty);

        when(catalogueItemRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(catalogueItem));
        when(cartLineRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(cartLine));
        when(cartLineRepository.save(any(CartLine.class))).thenReturn(null);

        cartService.addToCart(TEST_PRODUCT_ID);

        assertEquals(expectedQty, cartLine.getQty());
    }

    @Test
    public void testAddToCart_whenProductDoesNotExist_thenThrowResponseStatusException() {

        when(catalogueItemRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> cartService.addToCart(TEST_PRODUCT_ID));
    }
}