package com.example.orderservice.kafkaProducer;

import com.example.orderservice.eventClass.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {

        kafkaTemplate.send("order-created", event.getOrder());
    }
}
