package com.example.orderservice.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderCommandRepository extends JpaRepository<OrderEntity, Long> {
}