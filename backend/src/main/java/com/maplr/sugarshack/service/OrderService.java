package com.maplr.sugarshack.service;

import com.maplr.sugarshack.dto.OrderLineDto;
import com.maplr.sugarshack.dto.OrderValidationResponseDto;
import com.maplr.sugarshack.entity.MapleSyrup;
import com.maplr.sugarshack.repository.MapleSyrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    MapleSyrupRepository mapleSyrupRepository;

    public OrderValidationResponseDto placeOrder(List<OrderLineDto> orderLines){
        OrderValidationResponseDto responseDto = new OrderValidationResponseDto();
        responseDto.setOrderValid(true);

        for (OrderLineDto orderLine : orderLines) {

            MapleSyrup mapleSyrup = mapleSyrupRepository.findById(orderLine.getProductId()).orElse(null);

            if (mapleSyrup == null){
                addError(responseDto, String.format("Le produit %s n'existe pas.", orderLine.getProductId()));
                continue;
            }

            if (mapleSyrup.getStock() < orderLine.getQty()){
                addError(responseDto, String.format("Le stock n'est pas suffisant pour %s %s", orderLine.getQty(), orderLine.getProductId()));
                continue;
            }

            mapleSyrup.setStock(mapleSyrup.getStock() - orderLine.getQty());
            mapleSyrupRepository.save(mapleSyrup);
        }
        return responseDto;
    }

    private static void addError(OrderValidationResponseDto responseDto, String message) {
        responseDto.setOrderValid(false);
        responseDto.getErrors().add(message);
    }
}
