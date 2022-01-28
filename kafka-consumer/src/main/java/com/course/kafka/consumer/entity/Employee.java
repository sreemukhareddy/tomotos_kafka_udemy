package com.course.kafka.consumer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	
	@JsonProperty("employee_id")
	private String employeeId;
	
	private String name;
	
	@JsonProperty("birth_date")
	private String birthDate;

}
