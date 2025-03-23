package com.example.orderservice.query;

import com.example.orderservice.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderQueryRepository orderQueryRepository;

    public List<OrderQueryResponseDTO> getAllOrders() {
        List<OrderQueryModel> orders = orderQueryRepository.findAll();
        return orders.stream().map(this::convertToDto).toList();
    }


    public OrderQueryResponseDTO getOrderById(Long orderId) {
        Optional<OrderQueryModel> order = orderQueryRepository.findByOrderId(orderId);  // Find order by ID
        return order.map(this::convertToDto).orElseThrow(() -> new OrderNotFoundException(orderId));
    }


    private OrderQueryResponseDTO convertToDto(OrderQueryModel order) {
        OrderQueryResponseDTO response = new OrderQueryResponseDTO();
        response.setOrderId(order.getOrderId());
        response.setProductId(order.getProductId());
        response.setCustomerId(order.getCustomerId());
        response.setQuantity(order.getQuantity());
        response.setPrice(order.getPrice());
        response.setStatus(order.getStatus());
        response.setOrderCreatedAt(order.getOrderCreatedAt());
        return response;
    }
}

