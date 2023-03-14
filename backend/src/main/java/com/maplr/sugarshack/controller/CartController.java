package com.maplr.sugarshack.controller;

import com.maplr.sugarshack.dto.CartLineDto;
import com.maplr.sugarshack.mapper.CartLineMapper;
import com.maplr.sugarshack.repository.CartLineRepository;
import com.maplr.sugarshack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartLineRepository cartLineRepository;

    @Autowired
    CartLineMapper cartLineMapper;

    @Autowired
    CartService cartService;

    @GetMapping
    public List<CartLineDto> getCart() {
        return cartLineMapper.toDtos(cartLineRepository.findAll());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addToCart(@RequestParam String productId) {
        cartService.addToCart(productId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeFromCart(@RequestParam String productId) {
        cartService.removeFromCart(productId);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeQty(@RequestParam String productId, @RequestParam int newQty) {
        cartService.changeQty(productId, newQty);
    }
}
