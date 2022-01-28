package com.course.kafka.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Commodity {

	private String name;
	
	private double price;
	
	private String measurement;
	
	private long timeStamp;
}
