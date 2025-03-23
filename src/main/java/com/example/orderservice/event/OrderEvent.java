package com.example.orderservice.event;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private Long orderId;
    private String productId;
    private String customerId;
    private Integer quantity;
    private Double price;
    private String status;
    private LocalDateTime orderCreatedAt;
}
