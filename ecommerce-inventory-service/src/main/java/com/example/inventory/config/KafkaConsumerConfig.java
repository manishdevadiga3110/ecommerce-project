package com.example.inventory.config;
import com.example.inventory.dto.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.*;
@Configuration @EnableKafka
public class KafkaConsumerConfig {
 @Bean
 public ConsumerFactory<String, OrderEvent> consumerFactory() {
  JsonDeserializer<OrderEvent> deserializer = new JsonDeserializer<>(OrderEvent.class);
  deserializer.addTrustedPackages("*");
  Map<String,Object> config = new HashMap<>();
  config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
  config.put(ConsumerConfig.GROUP_ID_CONFIG,"inventory-group");
  return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),deserializer);
 }
 @Bean
 public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> kafkaListenerContainerFactory() {
  ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
  factory.setConsumerFactory(consumerFactory());
  return factory;
 }
}