package com.kamoun.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
