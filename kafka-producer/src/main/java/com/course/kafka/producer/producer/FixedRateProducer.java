package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FixedRateProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private int i=0;

	//@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		log.info("Producing the message Fixed Rate " + i);
		kafkaTemplate.send("t_fixedrate", "Fixed Rate " + i);
		i++;
	}

}
