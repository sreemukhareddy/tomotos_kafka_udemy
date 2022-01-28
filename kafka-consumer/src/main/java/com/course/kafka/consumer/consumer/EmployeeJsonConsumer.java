package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmployeeJsonConsumer {

	@KafkaListener(topics = "t_employee")
	public void consume(String message) throws Exception {
		Employee employee = new ObjectMapper().readValue(message, Employee.class);
		log.info(employee);
	}
}
