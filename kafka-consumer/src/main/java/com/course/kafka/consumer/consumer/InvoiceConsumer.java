package com.course.kafka.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.course.kafka.consumer.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class InvoiceConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@KafkaListener(topics = "t_invoice", containerFactory = "invoiceDltContainerFactory")
	public void listenAll(String message) throws Exception {
		Invoice invoice = objectMapper.readValue(message, Invoice.class);
		
		if(invoice.getAmount() < 1) {
			throw new RuntimeException("sfsafsafsafsafsafsaf");
		}
		
		log.info("Record is --> {}", invoice.toString());
	}
	
}
