package com.example.orderservice.entity;

import com.example.orderservice.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;

    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}