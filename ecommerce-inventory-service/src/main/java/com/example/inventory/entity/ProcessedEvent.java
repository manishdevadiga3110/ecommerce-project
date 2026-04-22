package com.example.inventory.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class ProcessedEvent { @Id private Long orderId; }