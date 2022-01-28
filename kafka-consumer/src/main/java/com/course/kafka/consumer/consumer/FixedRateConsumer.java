package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FixedRateConsumer {
	
	@KafkaListener(topics = "t_fixedrate")
	public void consume(String message) {
		log.info("Received " + message);
	}
	

}
