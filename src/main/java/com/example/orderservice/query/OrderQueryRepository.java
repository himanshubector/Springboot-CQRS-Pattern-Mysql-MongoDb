package com.example.orderservice.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderQueryRepository extends MongoRepository<OrderQueryModel, Long> {

    // Find by orderId (field), not by _id
    Optional<OrderQueryModel> findByOrderId(Long orderId);


    /*
    Since the MongoDB document stores orderId as a field (not as the _id field), you cannot query it using findById(orderId)
    because that method searches by the _id field.

    Instead, you need to define a custom query method in your OrderQueryRepository to search by the orderId field explicitly.

    The key point here is that you cannot use findById because your orderId is stored as a regular field in MongoDB,
    not as _id. By using findByOrderId(Long orderId), Spring Data MongoDB will query the correct field (orderId) instead.

     */
}

