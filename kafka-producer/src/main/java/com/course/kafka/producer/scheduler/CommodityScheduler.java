package com.course.kafka.producer.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.course.kafka.producer.entity.Commodity;
import com.course.kafka.producer.producer.CommodityProducer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CommodityScheduler {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private CommodityProducer commodityProducer;
	
	//@Scheduled(fixedRate = 5000)
	public void fetchCommodities() {
		var commodities = restTemplate.exchange("http://localhost:8080/api/commodity/v1/all",
												HttpMethod.GET,
												null,
												new ParameterizedTypeReference<List<Commodity>>() {
												}).getBody();
		
		commodities.forEach(commodity -> {
			try {
				commodityProducer.sendMessage(commodity);
			} catch (Exception e) {
				log.warn("ISSSSSSSSSSUUUUUUUUEEEEEEEEE");
			}
		});
	}
}
