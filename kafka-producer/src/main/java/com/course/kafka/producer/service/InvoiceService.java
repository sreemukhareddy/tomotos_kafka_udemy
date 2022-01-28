package com.course.kafka.producer.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Invoice;

@Service
public class InvoiceService {

	private static int counter = 0;

	public Invoice generateInvoice() {
		counter++;
		var invoiceNumber = "INV-" + counter;
		var invoiceAmount = ThreadLocalRandom.current().nextInt(1, 1000);
		
		return new Invoice(invoiceNumber, invoiceAmount, "USD");
	}
}
