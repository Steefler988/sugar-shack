package com.maplr.sugarshack.controller;

import com.maplr.sugarshack.dto.CatalogueItemDto;
import com.maplr.sugarshack.dto.OrderLineDto;
import com.maplr.sugarshack.dto.OrderValidationResponseDto;
import com.maplr.sugarshack.entity.CatalogueItem;
import com.maplr.sugarshack.repository.CatalogueItemRepository;
import com.maplr.sugarshack.service.OrderService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public OrderValidationResponseDto placeOrder(@RequestBody List<OrderLineDto> orderLines) {
        if (orderLines.stream().anyMatch(orderLineDto -> orderLineDto.getQty() < 1)){
            OrderValidationResponseDto responseDto = new OrderValidationResponseDto();
            responseDto.setOrderValid(false);
            responseDto.getErrors().add("Toutes les quantités doivent être positives");
            return responseDto;
        }
        return orderService.placeOrder(orderLines);
    }
}
