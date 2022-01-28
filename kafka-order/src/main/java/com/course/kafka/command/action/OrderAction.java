package com.course.kafka.command.action;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.kafka.api.server.request.OrderRequest;
import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.broker.producer.OrderProducer;
import com.course.kafka.entity.Order;
import com.course.kafka.entity.OrderItem;
import com.course.kafka.repository.OrderItemRepository;
import com.course.kafka.repository.OrderRepository;

import net.bytebuddy.utility.RandomString;


@Component
public class OrderAction {
	
	@Autowired
	private OrderProducer orderProducer;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private OrderItemRepository orderItemRepository;

	public Order convertToOrder(OrderRequest orderRequest) {
		
		var result = new Order();
		result.setCreditCardNumber(orderRequest.getCreditCardNumber());
		result.setOrderLocation(orderRequest.getOrderLocation());
		result.setOrderDateTime(LocalDateTime.now());
		result.setOrderNumber(RandomString.make(10).toUpperCase());
		
		var items = orderRequest.getItems().stream()
				                .map(OrderItem::new)
				                .map(item -> {
				                	item.setOrder(result);
				                	return item;
				                })
				                .collect(Collectors.toList());
		
		result.setItems(items);
		
		return result;
	}

	public void saveToDatabase(Order order) {
		orderRepository.save(order);
		order.getItems().forEach(orderItemRepository::save);
	}

	public void publishToKafka(OrderItem item) {
		var orderMessage = new OrderMessage();
		
		orderMessage.setItemName(item.getItemName());
		orderMessage.setPrice(item.getPrice());
		orderMessage.setQuantity(item.getQuantity());
		
		orderMessage.setOrderDateTime(item.getOrder().getOrderDateTime());
		orderMessage.setOrderLocation(item.getOrder().getOrderLocation());
		orderMessage.setOrderNumber(item.getOrder().getOrderNumber());
		orderMessage.setCreditCardNumber(item.getOrder().getCreditCardNumber());
		
		orderProducer.publish(orderMessage);
	}

}
