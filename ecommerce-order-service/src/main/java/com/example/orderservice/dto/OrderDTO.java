package com.example.orderservice.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double amount;
}
