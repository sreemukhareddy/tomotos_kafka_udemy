package com.course.kafka.broker.producer;

import java.util.ArrayList;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.course.kafka.broker.message.OrderMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderProducer {

	@Autowired
	private KafkaTemplate<String, OrderMessage> kafkaTemplate;
	
	private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage orderMessage) {
		var surprise = StringUtils.startsWithIgnoreCase(orderMessage.getOrderLocation(), "A") ? 25 : 15;
		var kafkaheaders = new ArrayList<Header>();
		var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surprise).getBytes());
		kafkaheaders.add(surpriseBonusHeader);
		
		return new ProducerRecord<String, OrderMessage>("t.commodity.order", null, orderMessage.getOrderNumber(), orderMessage, kafkaheaders);
	}
	
	public void publish(OrderMessage message) {
		var producerRecord = buildProducerRecord(message);
		kafkaTemplate.send(producerRecord)
					 .addCallback( new ListenableFutureCallback<SendResult<String, OrderMessage>>() {

						@Override
						public void onSuccess(SendResult<String, OrderMessage> result) {
							log.info("Order {}, item {} published successfully", message.getOrderNumber(), message.getItemName());
						}

						@Override
						public void onFailure(Throwable ex) {
							log.error("Order {}, item {} failed to publish because {}", message.getOrderNumber(), message.getItemName(), ex.getMessage());
						}

					});
	}
}
