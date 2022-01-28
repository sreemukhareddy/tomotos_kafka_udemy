package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.consumer.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CommodityNotificationConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@KafkaListener(topics = "t_commodity", groupId = "cg-notification")
	public void consume(String message) throws Exception {
		var commodity = objectMapper.readValue(message, Commodity.class);
		log.info("Notification logic for {}", commodity);
	}
}
