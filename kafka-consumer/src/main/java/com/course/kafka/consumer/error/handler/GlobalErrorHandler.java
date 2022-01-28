package com.course.kafka.consumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
/*
 * to handle the global error
 */
@Log4j2
@Component
public class GlobalErrorHandler implements ConsumerAwareErrorHandler {
	@Override
	public void handle(Exception exception, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
		log.error("Error & its messages are {}", exception.getMessage() + " " + data.value().toString());
	}
}
