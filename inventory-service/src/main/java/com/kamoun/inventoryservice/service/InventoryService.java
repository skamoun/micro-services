package com.kamoun.inventoryservice.service;

import com.kamoun.inventoryservice.dto.InventoryResponse;
import com.kamoun.inventoryservice.model.Inventory;
import com.kamoun.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;
@Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
      return repository.findBySkuCodeIn(skuCode).stream().map(inventory -> {
          return InventoryResponse.builder()
                  .skuCode(inventory.getSkuCode())
                  .isInStock(inventory.getQuantity()>0)
                  .build();

      }).toList();



    }
}
