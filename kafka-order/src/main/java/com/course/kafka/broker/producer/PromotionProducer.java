package com.course.kafka.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.PromotionMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PromotionProducer {

	@Autowired
	private KafkaTemplate<String, PromotionMessage> kafkaTemplate;
	
	public void publish(PromotionMessage message) {
		try {
			var sendResult = kafkaTemplate.send("t.commodity.promotion", message).get();
			log.info("Send result success for message {}", sendResult.getProducerRecord().value());
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
	}
}
