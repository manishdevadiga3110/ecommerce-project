package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.Enum.OrderStatus;
import com.example.orderservice.eventClass.OrderCreatedEvent;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Order createOrder(OrderDTO dto) {

        Order order = Order.builder()
                .userId(dto.getUserId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .amount(dto.getAmount())
                .status(OrderStatus.CREATED)
                .build();
        Order savedOrder = orderRepository.save(order);

        // publish event (NO Kafka here)
        eventPublisher.publishEvent(new OrderCreatedEvent(savedOrder));
        return order;
    }
}
