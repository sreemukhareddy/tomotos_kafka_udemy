package com.course.kafka.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.kafka.api.server.request.PromotionRequest;
import com.course.kafka.command.service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApi {

	@Autowired
	private PromotionService promotionService;
	
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody PromotionRequest promotionRequest) {
		promotionService.createPromotion(promotionRequest);
		return ResponseEntity.ok().body("SENT");
	}
	
}
