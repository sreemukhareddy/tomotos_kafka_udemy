package com.course.kafka.consumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RebalanceConsumer {

	@KafkaListener(topics = "t_rebalance", concurrency = "3")
	public void consume(ConsumerRecord<String, String> message)  {
		log.info(message.key() + " " + message.partition() + " " + message.value() + " " + message.offset());
	}
	
}
