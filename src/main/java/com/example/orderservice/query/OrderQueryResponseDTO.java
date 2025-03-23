package com.example.orderservice.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryResponseDTO {

    private Long orderId;
    private String productId;
    private String customerId;
    private Integer quantity;
    private Double price;
    private String status;
    private LocalDateTime orderCreatedAt;
}
