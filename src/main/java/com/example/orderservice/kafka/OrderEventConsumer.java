package com.example.orderservice.kafka;

import com.example.orderservice.event.OrderEvent;
import com.example.orderservice.query.OrderQueryModel;
import com.example.orderservice.query.OrderQueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

    private final OrderQueryRepository orderQueryRepository;


//    @KafkaListener(topics = "order-events", groupId = "order-consumer-group")
//    public void listenToOrderEvents(String orderEventJson) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            OrderEvent orderEvent = objectMapper.readValue(orderEventJson, OrderEvent.class);
//
//            // Handle the event and update the read model
//            if ("CREATED".equals(orderEvent.getStatus())) {
//                // Create or update the order in the read model (MongoDB)
//                OrderQueryModel orderQueryModel = new OrderQueryModel(
//                        orderEvent.getOrderId(), orderEvent.getProductId(),
//                        orderEvent.getQuantity(), orderEvent.getPrice(),
//                        orderEvent.getStatus(), orderEvent.getOrderCreatedAt());
//
//                orderQueryRepository.save(orderQueryModel);  // Save the updated order to MongoDB
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();  // Handle deserialization exceptions
//        }
//    }



    @KafkaListener(topics = "order-events", groupId = "order-consumer-group")
    public void listenToOrderEvents(OrderEvent orderEvent) {

        log.info("Order event consumed: {}", orderEvent);

        // Handle the event and update the read model
        if ("CREATED".equals(orderEvent.getStatus())) {
            // Create or update the order in the read model (MongoDB)
            OrderQueryModel orderQueryModel = new OrderQueryModel(
                    orderEvent.getOrderId(), orderEvent.getProductId(), orderEvent.getCustomerId(),
                    orderEvent.getQuantity(), orderEvent.getPrice(),
                    orderEvent.getStatus(), orderEvent.getOrderCreatedAt());

            log.info("OrderQueryModel: {}", orderQueryModel);

            orderQueryRepository.save(orderQueryModel);  // Save the updated order to MongoDB

            log.info("Order saved successfully.....");
        }

    }
}

