package com.example.orderservice.config;

import com.example.orderservice.event.OrderEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConfig {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String CONSUMER_GROUP_ID = "order-consumer-group";


    /***
     * Producer Configuration
     *
     */
    @Bean
    public ProducerFactory<String, OrderEvent> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        // Additional producer configurations (optional)
//        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
//        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);  // Retry config for reliability
//        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1);  // Small delay for batching

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /***
     * Kafka Template
     *
     */
    @Bean
    public KafkaTemplate<String, OrderEvent> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }


    /***
     * Consumer Configuration
     *
     */
    @Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // Set to "false" if you want to commit offsets manually
//        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to "latest" to read only new messages



//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
//                new JsonDeserializer<>(OrderEvent.class).trustedPackages("*"));

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(OrderEvent.class));
    }


    /***
     * Kafka Listener Container Factory
     *
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

//        factory.setConcurrency(3);  // Number of threads for parallel processing
//        factory.setBatchListener(false);  // Set to true if you want to process Kafka messages in batches

        return factory;
    }
}

