package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.kafka.consumer.entity.SimpleNumber;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SimpleNumberConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = "t_simple_number")
	public void consume(String message) throws Exception {
		var simplenumber = objectMapper.readValue(message, SimpleNumber.class);
		if(simplenumber.getNumber() % 2 != 0) {
			throw new RuntimeException("Odd number..!");
		}
		log.info(simplenumber);
	}
}
