package com.course.kafka.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.broker.message.OrderReplyMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderListenerTwo {

	@KafkaListener(topics = "t.commodity.order")
	@SendTo("t.commodity.order-reply")
	public OrderReplyMessage listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
		var headers = consumerRecord.headers();
		var orderMessage = consumerRecord.value();
		
		log.info("Processing order {}, item {}, credit-card-number {}", orderMessage.getOrderNumber(), orderMessage.getItemName(), orderMessage.getCreditCardNumber());
		
		log.info("Headers are");
		headers.forEach(h -> {
			log.info("Key {}, Value {}", h.key(), h.value());
		});
		
		var bonusPercentage = Double.parseDouble(new String(headers.lastHeader("surpriseBonus").value()));
		var bonusAmount = (bonusPercentage / 100 ) * orderMessage.getPrice() * orderMessage.getQuantity();
		
		log.info("Surprise bonus is {}", bonusAmount);
		
		var replymessage = new OrderReplyMessage("RECEIVED AND SENDING FROM REWARD APP");
		return replymessage;
	}
}
