package com.kamoun.productservice.service;

import com.kamoun.productservice.dto.ProductRequest;
import com.kamoun.productservice.dto.ProductResponse;
import com.kamoun.productservice.model.Product;
import com.kamoun.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
   private final ProductRepository repository;
    public void createProduct(ProductRequest request){

        Product product= Product.builder().
                name(request.getName())
                        .description(request.getDescription())
                                .price(request.getPrice()).build();
        repository.save(product);
         log.info("product with id: {} is saved",product.getId());
    }

    public List<Product> findAll() {
        return repository.findAll();
    }
}
