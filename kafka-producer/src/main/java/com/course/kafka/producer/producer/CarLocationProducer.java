package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarLocationProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void send(CarLocation carLocation) throws Exception {
		var json = objectMapper.writeValueAsString(carLocation);
		kafkaTemplate.send("t_location", carLocation.getCarId(), json);
	}
}
