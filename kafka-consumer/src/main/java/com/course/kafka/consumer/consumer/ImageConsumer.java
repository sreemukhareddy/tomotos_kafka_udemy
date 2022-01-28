package com.course.kafka.consumer.consumer;

import java.net.http.HttpConnectTimeoutException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.kafka.consumer.entity.Image;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ImageConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@KafkaListener(topics = "t_image", containerFactory = "imageRetryContainerFactory")
	public void listenAll( String data) throws Exception {
		Image image = objectMapper.readValue(data, Image.class);
		
		if(image.getType().equalsIgnoreCase("svg"))
			throw new HttpConnectTimeoutException("Consider the timeout has come man..!!");
		
		log.info("The image processed is {}",  image.toString());
	}	
}
