package com.course.kafka.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarLocation {

	private String carId;
	
	private String timeStamp;
	
	private int distance;
	
}
