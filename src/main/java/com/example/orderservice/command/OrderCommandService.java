package com.example.orderservice.command;

import com.example.orderservice.event.OrderEvent;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.kafka.OrderEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCommandService {

    private final OrderCommandRepository orderCommandRepository;
    private final OrderEventPublisher orderEventPublisher;

    public void createOrder(CreateOrderRequestDTO request) throws JsonProcessingException {

        log.info("Creating order");

        OrderEntity order = new OrderEntity();

        order.setProductId(request.getProductId());
        order.setCustomerId(request.getCustomerId());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus("CREATED");
        order.setOrderCreatedAt(LocalDateTime.now());

        log.info("Order entity populated successfully");

        orderCommandRepository.save(order);


        log.info("Order saved successfully.....");


        // Publish event to Kafka after order creation
        OrderEvent orderEvent = new OrderEvent(order.getOrderId(),
                order.getProductId(), order.getCustomerId(), order.getQuantity(),
                order.getPrice(), order.getStatus(), order.getOrderCreatedAt());

        orderEventPublisher.publishOrderEvent(orderEvent);

        log.info("Order event published successfully.....");
    }


    public void updateOrder(Long orderId, UpdateOrderRequestDTO request) throws JsonProcessingException {

        OrderEntity order = orderCommandRepository
                .findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus("UPDATED");

        orderCommandRepository.save(order);

        OrderEvent orderEvent = new OrderEvent(order.getOrderId(), order.getProductId(),
                order.getCustomerId(), order.getQuantity(), order.getPrice(), order.getStatus(),
                order.getOrderCreatedAt());

        // orderEventPublisher.publishOrderEvent(orderEvent);
    }
}
