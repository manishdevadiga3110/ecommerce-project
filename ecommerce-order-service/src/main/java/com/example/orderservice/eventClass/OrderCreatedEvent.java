package com.example.orderservice.eventClass;

import com.example.orderservice.entity.Order;
import lombok.Getter;

@Getter
public class OrderCreatedEvent {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

}
