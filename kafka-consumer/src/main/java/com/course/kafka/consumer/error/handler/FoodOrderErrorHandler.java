package com.course.kafka.consumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/*
 * to handle the error
 */
@Log4j2
@Component
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
		log.error("Food Order Error ", exception.getMessage() + message.getPayload());
		/* to do global error handling start */
		if(exception instanceof RuntimeException ) {
			throw exception;
		}
		/* to do global error handling end */
		return null;
	}

}
