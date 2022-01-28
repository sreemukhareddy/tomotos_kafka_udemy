package com.course.kafka.repository;

import org.springframework.data.repository.CrudRepository;

import com.course.kafka.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}
