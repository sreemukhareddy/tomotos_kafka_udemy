package com.course.kafka.consumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class KafkaKeyConsumer {

	@KafkaListener(topics = "t_multi_partitions")
	public void consumer(ConsumerRecord<String, String> message) throws Exception {
		log.info("Key : {}, Partition : {}, Message : {}", message.key(), message.partition(), message.value());
		Thread.sleep(1000);
	}
}
