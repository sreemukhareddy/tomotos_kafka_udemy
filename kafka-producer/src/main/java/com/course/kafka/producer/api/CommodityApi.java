package com.course.kafka.producer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.kafka.producer.entity.Commodity;
import com.course.kafka.producer.service.CommodityService;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

	@Autowired
	private CommodityService commodityService;
	
	@GetMapping("/all")
	public List<Commodity> generateCommodities(){
		return commodityService.createDummyCommodities();
	}
}
