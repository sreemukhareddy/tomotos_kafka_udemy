package com.course.kafka.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.kafka.api.server.request.OrderRequest;
import com.course.kafka.command.action.OrderAction;

@Service
public class OrderService {

	@Autowired
	private OrderAction action;

	public String saveOrder(OrderRequest orderRequest) {

		var order = action.convertToOrder(orderRequest);

		action.saveToDatabase(order);

		order.getItems().forEach(action::publishToKafka);

		return order.getOrderNumber();
	}

}
