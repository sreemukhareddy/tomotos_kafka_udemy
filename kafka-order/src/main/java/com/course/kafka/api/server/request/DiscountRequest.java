package com.course.kafka.api.server.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscountRequest {
	private String discountCode;
	private int discountPercentage;
}
