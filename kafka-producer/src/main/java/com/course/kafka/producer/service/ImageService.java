package com.course.kafka.producer.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Image;

@Service
public class ImageService {

	private static int counter = 0;

	public Image generateImage(String type) {
		counter++;
		var name = "image-" + counter;
		var size = ThreadLocalRandom.current().nextLong(100, 10_000);
		return new Image(name, size, type);
	}
}
