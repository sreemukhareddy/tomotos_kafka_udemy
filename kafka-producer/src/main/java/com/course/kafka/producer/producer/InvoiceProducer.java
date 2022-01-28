package com.course.kafka.producer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void send(Invoice invoice) throws Exception {
		var json = objectMapper.writeValueAsString(invoice);
		kafkaTemplate.send("t_invoice", invoice.getNumber(), json);
	}
}
