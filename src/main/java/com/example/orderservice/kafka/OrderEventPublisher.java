package com.example.orderservice.kafka;

import com.example.orderservice.event.OrderEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {

    // private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String orderTopic = "order-events";

    public void publishOrderEvent(OrderEvent orderEvent) throws JsonProcessingException {

        log.info("Publishing order event to Kafka topic");
        kafkaTemplate.send(orderTopic, orderEvent);  // Send the order event to Kafka topic
    }


//    public void publishOrderEvent(OrderEvent orderEvent) throws JsonProcessingException {
//
//        String orderEventJson = new ObjectMapper().writeValueAsString(orderEvent);
//        kafkaTemplate.send(orderTopic, orderEventJson);  // Send the order event to Kafka topic
//    }
}

