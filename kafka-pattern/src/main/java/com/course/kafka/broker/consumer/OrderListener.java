package com.course.kafka.broker.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.OrderMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderListener {

	@KafkaListener(topics = "t.commodity.order")
	public void listen(OrderMessage orderMessage) {
		
		var totalItemAmount = orderMessage.getPrice() * orderMessage.getQuantity();
		log.info("Processing order {}", orderMessage.toString());
	}
}
