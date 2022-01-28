package com.course.kafka.producer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.course.kafka.producer.entity.Commodity;

@Service
public class CommodityService {
	
	private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();
	private static final String COPPER = "copper";
	private static final String GOLD = "gold";
	
	// max adjustment for price ( base price * max adjustment )
	private static final double MAX_ADJUSTMENT = 1.05;
	
	// max adjustment for price ( base price * min adjustment )
	private static final double MIN_ADJUSTMENT = 0.95;
		
	static {
		var  timeStamp = System.currentTimeMillis();
		COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 1_402.5, "ounce", timeStamp));
		COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 5_900.57, "tonne", timeStamp));
	}
	
	public Commodity createDummyCommodity(String name) {
		if(!COMMODITY_BASE.containsKey(name)) {
			throw new IllegalArgumentException("safafdadasdasd");
		}
		var commodity = COMMODITY_BASE.get(name);
		var baseprice = commodity.getPrice();
		var newPrice = baseprice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);
		
		commodity.setPrice(newPrice);
		commodity.setTimeStamp(System.currentTimeMillis());

		return commodity;
	}
	
	public List<Commodity> createDummyCommodities() {
		var result = new ArrayList<Commodity>();
		COMMODITY_BASE.keySet().forEach(k -> {
			result.add(createDummyCommodity(k));
		});
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
