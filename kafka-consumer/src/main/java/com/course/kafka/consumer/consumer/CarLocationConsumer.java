package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.kafka.consumer.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CarLocationConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@KafkaListener(topics = "t_location", groupId = "cg-all-location")
	public void listenAll(String message) throws Exception {
		CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
		log.info("ListeningAll {}", carLocation);
	}
	
	@KafkaListener(topics = "t_location", groupId = "cg-far-location", containerFactory = "farLocationContainerFactory")
	public void listenFar(String message) throws Exception {
		CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
		log.info("ListeningFar {}", carLocation);
	}
	
}
