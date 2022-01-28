package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Image;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ImageProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void send(Image image) throws Exception {
		var json = objectMapper.writeValueAsString(image);
		kafkaTemplate.send("t_image", image.getType(), json);
	}
}
