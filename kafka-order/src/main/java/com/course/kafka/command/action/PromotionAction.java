package com.course.kafka.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.kafka.api.server.request.PromotionRequest;
import com.course.kafka.broker.message.PromotionMessage;
import com.course.kafka.broker.producer.PromotionProducer;


@Component
public class PromotionAction {
	
	@Autowired
	private PromotionProducer promotionProducer;
	

	public void publishToKafka(PromotionRequest promotionRequest) {
		var promotionMessage = new PromotionMessage(promotionRequest.getPromotionCode());
		promotionProducer.publish(promotionMessage);
	}

}
