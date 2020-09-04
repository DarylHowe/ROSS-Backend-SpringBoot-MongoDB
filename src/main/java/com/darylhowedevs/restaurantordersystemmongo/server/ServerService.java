package com.darylhowedevs.restaurantordersystemmongo.server;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.darylhowedevs.restaurantordersystemmongo.order.Order;

@Service
public class ServerService {

	private final MongoTemplate mongoTemplate;

	public ServerService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Integer> getServerTables(String serverId) {

		Server server = mongoTemplate.findById(serverId, Server.class);
		return server.getTablesList();
	}

	public List<Integer> removeTable(String serverId, int tableNumber) {

		Server server = mongoTemplate.findById(serverId, Server.class);
		server.removeTableFromTableList(tableNumber);

		Query query = new Query();
		query.addCriteria(Criteria.where("serverId").is(serverId));
		mongoTemplate.save(server);

		return server.getTablesList();
	}

	public void createOrder(String serverId, Order order) {

		Server server = mongoTemplate.findById(serverId, Server.class);
		server.createOrder(order);

		Query query = new Query();
		query.addCriteria(Criteria.where("serverId").is(serverId));
		mongoTemplate.save(server);
	}

	public List<Order> getOrderByTableNumber(String serverId, int tableNumber) {

		Server server = mongoTemplate.findById(serverId, Server.class);
		return server.getOrdersByTableNumber(tableNumber);
	}

	public List<Integer> addTableToTableList(String serverId, int tableNumber) {

		Server server = mongoTemplate.findById(serverId, Server.class);
		server.addTableToList(tableNumber);

		Query query = new Query();
		query.addCriteria(Criteria.where("serverId").is(serverId));
		mongoTemplate.save(server);
		return server.getTablesList();
	}

	public Server closeOrderByTableNumber(String serverId, int tableNumber) {
		
		Server server = mongoTemplate.findById(serverId, Server.class);
		server.closeOrderByTableNumber(tableNumber);

		Query query = new Query();
		query.addCriteria(Criteria.where("serverId").is(serverId));
		mongoTemplate.save(server);
		return server;
	}

	public List<Order> getAllOrders(String serverId) {
		
		Server server = mongoTemplate.findById(serverId, Server.class);
		return server.getAllOrders();
	}

	public List<Order> getAllActiveOrders(String serverId) {
		
		Server server = mongoTemplate.findById(serverId, Server.class);
		return server.getAllActiveOrders();
	}

	public List<Order> getAllClosedOrders(String serverId) {
		
		Server server = mongoTemplate.findById(serverId, Server.class);
		return server.getAllClosedOrders();
	}
}
