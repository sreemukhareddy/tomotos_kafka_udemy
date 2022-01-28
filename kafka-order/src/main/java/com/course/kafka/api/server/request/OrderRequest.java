package com.course.kafka.api.server.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
	private String orderLocation;
	private String creditCardNumber;
	private List<OrderItemRequest> items;
}
