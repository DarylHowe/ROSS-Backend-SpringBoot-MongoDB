package com.darylhowedevs.restaurantordersystemmongo.server;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ServerRepository extends MongoRepository<Server, String> {

}
