package com.example.orderservice.query;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "order_read_model")   // MongoDB document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryModel {
    @Id
    private Long orderId;  // Now treated as the primary key (_id) in MongoDB. This is mapped to MongoDB's _id field of type Long
    private String productId;
    private String customerId;
    private Integer quantity;
    private Double price;
    private String status;
    private LocalDateTime orderCreatedAt;
}
