package com.course.kafka.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.kafka.command.action.DiscountAction;

@Service
public class DiscountService {

	@Autowired
	private DiscountAction action;

	public void createDiscount(com.course.kafka.api.server.request.DiscountRequest request) {
		action.publishToKafka(request);
	}

}
