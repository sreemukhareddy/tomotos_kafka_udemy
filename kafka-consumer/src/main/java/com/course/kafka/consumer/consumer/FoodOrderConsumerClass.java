package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.kafka.consumer.entity.FoodOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FoodOrderConsumerClass {
	
	private ObjectMapper objectMapper = new ObjectMapper();	
	
	private static final int MAX_AMOUNT_ORDER = 7;

	@KafkaListener(topics = "t_food_order", errorHandler = "foodOrderErrorHandler")
	public void consume(String message) throws Exception {
		
		var foodOrder = objectMapper.readValue(message, FoodOrder.class);
		
		if(foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
			throw new IllegalArgumentException("Macha, bomma kanapadidhi");
		}
		log.info(foodOrder);
	}
}
