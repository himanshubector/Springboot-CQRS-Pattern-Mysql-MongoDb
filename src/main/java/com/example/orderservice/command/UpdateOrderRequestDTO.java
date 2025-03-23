package com.example.orderservice.command;

import lombok.Data;

@Data
public class UpdateOrderRequestDTO {
    private Integer quantity;
    private Double price;
}