package com.maplr.sugarshack.controller;

import com.maplr.sugarshack.dto.CatalogueItemDto;
import com.maplr.sugarshack.dto.MapleSyrupDto;
import com.maplr.sugarshack.entity.Etype;
import com.maplr.sugarshack.mapper.CatalogueItemMapper;
import com.maplr.sugarshack.mapper.MapleSyrupMapper;
import com.maplr.sugarshack.repository.CatalogueItemRepository;
import com.maplr.sugarshack.repository.MapleSyrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    CatalogueItemRepository catalogueItemRepository;

    @Autowired
    CatalogueItemMapper catalogueItemMapper;

    @Autowired
    MapleSyrupRepository mapleSyrupRepository;

    @Autowired
    MapleSyrupMapper mapleSyrupMapper;

    @GetMapping
    public List<CatalogueItemDto> getCatalogue(@RequestParam(required = false) Etype type) {
        return catalogueItemMapper.toDtos(catalogueItemRepository.findByType(type));
    }

    @GetMapping("/{productId}")
    public MapleSyrupDto getProductInfo(@PathVariable String productId) {
        return mapleSyrupMapper.toDto(mapleSyrupRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}

