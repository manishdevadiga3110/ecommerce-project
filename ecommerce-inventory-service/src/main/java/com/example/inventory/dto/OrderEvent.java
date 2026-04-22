package com.example.inventory.dto;
import lombok.Data;
@Data
public class OrderEvent {
 private Long orderId; private Long userId; private Long productId;
 private Integer quantity; private Double amount; private String status;
}