package com.course.kafka.producer.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.course.kafka.producer.entity.CarLocation;
import com.course.kafka.producer.producer.CarLocationProducer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CarLocationScheduler {

	private CarLocation carOne;
	private CarLocation carTwo;
	private CarLocation carThree;
	
	
	@Autowired
	private CarLocationProducer carLocationProducer;


	public CarLocationScheduler() {
		var now = System.currentTimeMillis();
		this.carOne = new CarLocation("car-one", String.valueOf(now), 0);
		this.carTwo = new CarLocation("car-two", String.valueOf(now), 110);
		this.carThree = new CarLocation("car-three", String.valueOf(now), 95);
	}
	
	//@Scheduled(fixedRate = 10000)
	public void generateCarLocation() throws Exception {
		var now = String.valueOf(System.currentTimeMillis());
		carOne.setTimeStamp(now);
		carTwo.setTimeStamp(now);
		carThree.setTimeStamp(now);
		
		
		carOne.setDistance(carOne.getDistance() + 1);
		carTwo.setDistance(carTwo.getDistance() - 1);
		carThree.setDistance(carThree.getDistance() + 1);
		log.info("sending the car location info ti the kafka producer");
		carLocationProducer.send(carOne);
		carLocationProducer.send(carTwo);
		carLocationProducer.send(carThree);
		log.info("completed sending the car location info ti the kafka producer");
	}
	
	
}
