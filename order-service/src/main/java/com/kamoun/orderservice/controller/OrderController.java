package com.kamoun.orderservice.controller;

import com.kamoun.orderservice.dto.OrderRequest;
import com.kamoun.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService service;
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
   public String placeOrder(@RequestBody OrderRequest orderRequest){
    service.placeOrder(orderRequest);
     return " order saved succesfully :)";

   }
}
