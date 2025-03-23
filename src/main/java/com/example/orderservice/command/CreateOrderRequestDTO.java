package com.example.orderservice.command;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Data
public class CreateOrderRequestDTO {

    private Long orderId;

    @NotNull(message = "Product ID cannot be null")
    private String productId;

    private String customerId;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    private Double price;

    private String status;

    private LocalDateTime orderCreatedAt;
}