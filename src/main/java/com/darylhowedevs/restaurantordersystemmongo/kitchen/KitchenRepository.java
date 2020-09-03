package com.darylhowedevs.restaurantordersystemmongo.kitchen;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.darylhowedevs.restaurantordersystemmongo.order.Order;

public interface KitchenRepository extends MongoRepository<Order, String>{

}
