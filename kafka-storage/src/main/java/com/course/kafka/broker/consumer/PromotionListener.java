package com.course.kafka.broker.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.DiscountMessage;
import com.course.kafka.broker.message.PromotionMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@KafkaListener( topics = "t.commodity.promotion")
public class PromotionListener {

	@KafkaHandler
	public void listenPromotion(PromotionMessage message) {
		log.info("Processing promotion {}", message.toString());
	}
	
	@KafkaHandler
	public void listenDiscount(DiscountMessage message) {
		log.info("Processing discount {}", message.toString());
	}
}
