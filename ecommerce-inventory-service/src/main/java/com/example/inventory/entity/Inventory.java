package com.example.inventory.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Inventory { @Id private Long productId; private Integer stock; }