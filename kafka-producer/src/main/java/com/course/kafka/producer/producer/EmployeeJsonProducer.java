package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeJsonProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	public void sendMessage(Employee emp) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(emp);
		kafkaTemplate.send("t_employee", json);
	}
}
