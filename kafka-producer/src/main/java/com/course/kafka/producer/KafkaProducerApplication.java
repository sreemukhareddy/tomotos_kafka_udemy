package com.course.kafka.producer;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.kafka.producer.producer.EmployeeJsonProducer;
import com.course.kafka.producer.producer.FoodOrderProducerClass;
import com.course.kafka.producer.producer.HelloKafkaProducer;
import com.course.kafka.producer.producer.ImageProducer;
import com.course.kafka.producer.producer.InvoiceProducer;
import com.course.kafka.producer.producer.KafkaKeyProducer;
import com.course.kafka.producer.producer.SimpleNumberProducer;
import com.course.kafka.producer.service.ImageService;
import com.course.kafka.producer.service.InvoiceService;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableScheduling
@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner{
	
	@Autowired
	private HelloKafkaProducer helloKafkaProducer;
	
	@Autowired
	private KafkaKeyProducer kafkaKeyProducer;

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;
	
	@Autowired
	private FoodOrderProducerClass foodOrderProducer;
	
	@Autowired
	private SimpleNumberProducer simpleNumberProducer;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageProducer imageProducer;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceProducer invoiceProducer;

	@SneakyThrows
	@Override
	public void run(String... args) throws Exception {
		
		IntStream.rangeClosed(1, 10)
				 .boxed()
				 .forEach(i -> {
					 var invoice = invoiceService.generateInvoice();
					 if(i >= 5) {
						 invoice.setAmount(-1);
					 }
					 try {
						invoiceProducer.send(invoice);
					} catch (Exception e) {
						
					}
				 });
		
		
		/*
		var image1 = imageService.generateImage("jpg");
		var image2 = imageService.generateImage("svg");
		var image3 = imageService.generateImage("png");
		
		imageProducer.send(image1);
		imageProducer.send(image2);
		imageProducer.send(image3);
		*/
		/*
		
		foodOrderProducer.send(new FoodOrder(3, "chicken"));
		foodOrderProducer.send(new FoodOrder(19, "mutton"));
		foodOrderProducer.send(new FoodOrder(7, "pappu-tomato"));
		
		IntStream.rangeClosed(1, 103)
				 .boxed()
				 .forEach(i -> {
					 try {
						simpleNumberProducer.send(new SimpleNumber(i));
					} catch (Exception e) {
						e.printStackTrace();
					}
				 });
				 
		*/
		
		/*
		IntStream
		.rangeClosed(1, 50)
		.boxed()
		.forEach(i -> {
			var employee = new Employee(i+"", "test-"+i, LocalDate.now().toString());
			try {
				Thread.sleep(1000);
				employeeJsonProducer.sendMessage(employee);
			} catch (Exception e) {
				System.out.println(e);
			}
		});
		*/
		
		/*
		IntStream
			.rangeClosed(1, 50)
			.boxed()
			.forEach(i -> {
				String key = "Key-"+(i%4);
				String data = "Data-"+i;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException("We are doomed..!");
				}
				log.info("sending the " + data);
				kafkaKeyProducer.send(key, data);
			});
		*/
		
		/*
		Thread.sleep(1500);
		helloKafkaProducer.sendHello("aaaaa");
		Thread.sleep(1500);
		helloKafkaProducer.sendHello("bbbbb");
		Thread.sleep(1500);
		helloKafkaProducer.sendHello("ccccc");
		Thread.sleep(1500);
		helloKafkaProducer.sendHello("ddddd");
		*/
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}
