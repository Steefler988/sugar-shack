package com.maplr.sugarshack.service;

import com.maplr.sugarshack.entity.CartLine;
import com.maplr.sugarshack.entity.CatalogueItem;
import com.maplr.sugarshack.repository.CartLineRepository;
import com.maplr.sugarshack.repository.CatalogueItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService {

    @Autowired
    CartLineRepository cartLineRepository;

    @Autowired
    CatalogueItemRepository catalogueItemRepository;

    public void addToCart(String productId){
        CatalogueItem catalogueItem = getCatalogueItem(productId);

        CartLine cartLine = cartLineRepository.findById(productId).orElse(newCartLine(catalogueItem));

        if (cartLine.getQty() == catalogueItem.getMaxQty()){
            throwMaxItemException(productId, catalogueItem);
        }

        cartLine.setQty(cartLine.getQty() + 1);
        cartLineRepository.save(cartLine);

    }


    public void removeFromCart(String productId){
        CartLine cartLine = cartLineRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (cartLine.getQty() == 1){
            cartLineRepository.delete(cartLine);
            return;
        }

        cartLine.setQty(cartLine.getQty() - 1);
        cartLineRepository.save(cartLine);
    }

    public void changeQty(String productId, int newQty){
        if (newQty < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La quantité ne peut pas être négative");
        }

        CatalogueItem catalogueItem = getCatalogueItem(productId);

        if (newQty == 0){
            CartLine cartLine = cartLineRepository.findById(productId).orElse(null);
            if (cartLine != null){
                cartLineRepository.delete(cartLine);
            }
            return;
        }

        if (newQty > catalogueItem.getMaxQty()){
            throwMaxItemException(productId, catalogueItem);
        }

        CartLine cartLine = cartLineRepository.findById(productId).orElse(newCartLine(catalogueItem));
        cartLine.setQty(newQty);
        cartLineRepository.save(cartLine);
    }

    private CatalogueItem getCatalogueItem(String productId) {
        CatalogueItem catalogueItem = catalogueItemRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return catalogueItem;
    }

    private static CartLine newCartLine(CatalogueItem catalogueItem) {
        return new CartLine(catalogueItem.getId(), catalogueItem.getName(), catalogueItem.getImage(), catalogueItem.getPrice());
    }

    private static void throwMaxItemException(String productId, CatalogueItem catalogueItem) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("La quantité maximale de %s est de %s", productId, catalogueItem.getMaxQty()));
    }


}
