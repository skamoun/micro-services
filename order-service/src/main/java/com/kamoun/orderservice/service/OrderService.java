package com.kamoun.orderservice.service;

import com.kamoun.orderservice.dto.InventoryResponse;
import com.kamoun.orderservice.dto.OrderRequest;
import com.kamoun.orderservice.model.Order;
import com.kamoun.orderservice.model.OrderLineItems;
import com.kamoun.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final WebClient.Builder webClient;
    public void placeOrder(OrderRequest orderRequest){
        var order= Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(dto-> OrderLineItems.builder()
                                .price(dto.getPrice())
                                .quantity(dto.getQuantity())
                                .skuCode(dto.getSkuCode())

                                .build()).toList())
                .build();
        // call to inventory service to check if the stock is avalaible

    final List<String> skuCodes=   order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
     InventoryResponse[] result=   webClient.build().get()
                        .uri("http://inventory-service/api/inventory",
                                uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                             .block();
     Boolean allProductInStock= Arrays.stream(result).allMatch(InventoryResponse::getIsInStock);
     if(allProductInStock)
        orderRepository.save(order);
        else throw new IllegalArgumentException(" not valaible in stock");

    }
}
