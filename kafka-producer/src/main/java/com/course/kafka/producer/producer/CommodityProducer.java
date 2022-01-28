package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommodityProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	public void sendMessage(Commodity commodity) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(commodity);
		kafkaTemplate.send("t_commodity", commodity.getName(), json);
	}
}
