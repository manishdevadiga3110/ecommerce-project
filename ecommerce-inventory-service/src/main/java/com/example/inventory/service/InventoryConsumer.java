package com.example.inventory.service;
import com.example.inventory.dto.OrderEvent;
import com.example.inventory.entity.*;
import com.example.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor @Slf4j
public class InventoryConsumer {
 private final InventoryRepository inventoryRepository;
 private final ProcessedEventRepository processedEventRepository;
 @KafkaListener(topics="order-created",groupId="inventory-group")
 public void consume(OrderEvent event){
  log.info("Received event: {}",event);
  if(processedEventRepository.existsById(event.getOrderId())){
   log.warn("Duplicate ignored: {}",event.getOrderId());
   return;
  }
  Inventory inv = inventoryRepository.findById(event.getProductId())
   .orElseThrow(()->new RuntimeException("Product not found"));
  if(inv.getStock()<event.getQuantity()){
   log.error("Insufficient stock"); return;
  }
  inv.setStock(inv.getStock()-event.getQuantity());
  inventoryRepository.save(inv);
  processedEventRepository.save(new ProcessedEvent(event.getOrderId()));
  log.info("Stock updated");
 }
}