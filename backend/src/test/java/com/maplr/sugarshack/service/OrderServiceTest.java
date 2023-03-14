package com.maplr.sugarshack.service;

import com.maplr.sugarshack.dto.OrderLineDto;
import com.maplr.sugarshack.dto.OrderValidationResponseDto;
import com.maplr.sugarshack.entity.MapleSyrup;
import com.maplr.sugarshack.repository.MapleSyrupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private MapleSyrupRepository mapleSyrupRepository;

    private OrderService orderService;

    private final String TEST_PRODUCT_ID = "test_product_id";
    private final String TEST_NAME = "test_name";
    private final String TEST_DESCRIPTION = "test_description";
    private final String TEST_IMAGE = "test_image";
    private final double TEST_PRICE = 10.0;
    private final int TEST_STOCK = 20;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService();
        orderService.mapleSyrupRepository = mapleSyrupRepository;
    }

    @Test
    public void testPlaceOrder_whenOrderIsValid_thenUpdateMapleSyrupStock() {
        OrderLineDto orderLine = new OrderLineDto();
        orderLine.setProductId(TEST_PRODUCT_ID);
        orderLine.setQty(5);

        MapleSyrup mapleSyrup = new MapleSyrup();
        mapleSyrup.setId(TEST_PRODUCT_ID);
        mapleSyrup.setName(TEST_NAME);
        mapleSyrup.setDescription(TEST_DESCRIPTION);
        mapleSyrup.setImage(TEST_IMAGE);
        mapleSyrup.setPrice(TEST_PRICE);
        mapleSyrup.setStock(TEST_STOCK);

        List<OrderLineDto> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        when(mapleSyrupRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(mapleSyrup));
        when(mapleSyrupRepository.save(any(MapleSyrup.class))).thenReturn(null);

        OrderValidationResponseDto responseDto = orderService.placeOrder(orderLines);

        assertTrue(responseDto.isOrderValid());
        assertEquals(TEST_STOCK - orderLine.getQty(), mapleSyrup.getStock());
    }

    @Test
    public void testPlaceOrder_whenProductDoesNotExist_thenAddError() {
        OrderLineDto orderLine = new OrderLineDto();
        orderLine.setProductId(TEST_PRODUCT_ID);
        orderLine.setQty(5);

        List<OrderLineDto> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        when(mapleSyrupRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.empty());

        OrderValidationResponseDto responseDto = orderService.placeOrder(orderLines);

        assertFalse(responseDto.isOrderValid());
        assertTrue(responseDto.getErrors().contains(String.format("Le produit %s n'existe pas.", TEST_PRODUCT_ID)));
    }
}
