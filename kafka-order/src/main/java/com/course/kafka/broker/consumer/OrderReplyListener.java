package com.course.kafka.broker.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.OrderReplyMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderReplyListener {

	@KafkaListener(topics = "t.commodity.order-reply")
	public void listen(OrderReplyMessage message) {
		log.info("Reply message received {}", message.getReplyMessage());
	}
}
