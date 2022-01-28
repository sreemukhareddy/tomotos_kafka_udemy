package com.course.kafka.broker.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscountMessage {

	private String discountCode;

	private int discountPercentage;

}
