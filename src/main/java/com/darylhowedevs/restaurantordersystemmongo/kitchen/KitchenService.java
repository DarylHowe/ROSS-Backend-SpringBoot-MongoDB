package com.darylhowedevs.restaurantordersystemmongo.kitchen;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.darylhowedevs.restaurantordersystemmongo.order.Order;

@Service
public class KitchenService {

	private final MongoTemplate mongoTemplate;

	public KitchenService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Item> getOrderItemsByTableNumber(int tableNumber) {
		Query query = new Query().addCriteria(Criteria.where("tableNumber").is(tableNumber));
		Order order = mongoTemplate.findOne(query, Order.class);
		return order.getItemList();

	}
	
	public Order orderIsReady(int tableNumber) {
		Query query = new Query().addCriteria(Criteria.where("tableNumber").is(tableNumber));
		Order order = mongoTemplate.findOne(query, Order.class);
		order.setIsReadyToBeServed(true);
		return order;
	}
	
	public void orderIsServed(int tableNumber) {
		Query query = new Query().addCriteria(Criteria.where("tableNumber").is(tableNumber));
		mongoTemplate.remove(query, Order.class);
	}
	
	public Order orderIsReadyById(String id) {
		Order order = mongoTemplate.findById(id, Order.class);
		order.setIsReadyToBeServed(true);
		return order;
	}
	
	public boolean orderIsServedById(String id) {
		Order order = mongoTemplate.findById(id, Order.class);
		boolean isReadyToBeServed = order.getIsReadyToBeServed();
		return isReadyToBeServed;
	}
}
