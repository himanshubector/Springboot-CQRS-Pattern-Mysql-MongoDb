package com.example.orderservice.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/orders/query")  // Base endpoint for query operations
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }


    // Get all orders
    @GetMapping
    public List<OrderQueryResponseDTO> getAllOrders() {
        return orderQueryService.getAllOrders();  // Call service to fetch all orders
    }


    // Get order by orderId
    @GetMapping("/{orderId}")
    public OrderQueryResponseDTO getOrderById(@PathVariable Long orderId) {
        return orderQueryService.getOrderById(orderId);  // Call service to fetch order by ID
    }
}

