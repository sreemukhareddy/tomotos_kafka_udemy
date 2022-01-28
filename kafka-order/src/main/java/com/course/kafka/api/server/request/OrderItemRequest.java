package com.course.kafka.api.server.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemRequest {
	private String itemName;
	private int price;
	private int quantity;
}
