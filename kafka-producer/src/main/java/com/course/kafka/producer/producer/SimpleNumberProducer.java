package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.SimpleNumber;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SimpleNumberProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void send(SimpleNumber number) throws Exception {
		var json = objectMapper.writeValueAsString(number);
		kafkaTemplate.send("t_simple_number", json);
	}
}
