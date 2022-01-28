package com.course.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.course.kafka.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
