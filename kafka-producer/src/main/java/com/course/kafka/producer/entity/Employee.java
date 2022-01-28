package com.course.kafka.producer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@JsonProperty("employee_id")
	private String employeeId;
	
	private String name;
	
	@JsonProperty("birth_date")
	private String birthDate;

}
