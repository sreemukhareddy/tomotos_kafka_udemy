package com.course.kafka.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.kafka.api.server.request.OrderRequest;
import com.course.kafka.api.server.response.OrderResponse;
import com.course.kafka.command.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		var orderNumber = orderService.saveOrder(orderRequest);
		var orderResponse = new OrderResponse(orderNumber);
		return ResponseEntity.ok().body(orderResponse);
	}
	
}
