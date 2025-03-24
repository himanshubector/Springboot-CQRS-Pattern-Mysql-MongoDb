package com.example.orderservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {

    private final OrderCommandService orderCommandService;


    @PostMapping("/command")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createOrder(@Valid @RequestBody CreateOrderRequestDTO request) throws Exception {
//        try {
//            orderCommandService.createOrder(request);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
//        }

        orderCommandService.createOrder(request);
        return ResponseEntity.ok("Order created successfully");
    }

    

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderRequestDTO request) throws JsonProcessingException {
        orderCommandService.updateOrder(orderId, request);
        return ResponseEntity.ok("Order updated successfully");
    }
}
