package com.kamoun.productservice.controller;

import com.kamoun.productservice.dto.ProductRequest;
import com.kamoun.productservice.dto.ProductResponse;
import com.kamoun.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatProduct(@RequestBody ProductRequest request){
     service.createProduct(request);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
        return service.findAll().stream().map(p-> ProductResponse.builder().id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build()).toList();

    }
}
