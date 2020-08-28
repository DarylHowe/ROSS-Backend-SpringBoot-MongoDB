package com.darylhowedevs.restaurantordersystemmongo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.darylhowedevs.restaurantordersystemmongo.order.Order;

@Document(collection = "Servers")
public class Server {


	@Id
	private String serverId;
	private String serverName;
	// Static can't be sent as JSON.
	private static int staticId;
	private ArrayList<Order> activeOrders = new ArrayList<>();
	private ArrayList<Order> closedOrders = new ArrayList<>();
	private ArrayList<Integer> tablesList = new ArrayList<>();
	
	
	public Server(String serverName) {
		this.serverName = serverName;
		this.serverId = Integer.toString(staticId++);
	}

	public void createOrder(Order order) {
		activeOrders.add(order);
		addTableToList(order.getTableNumber());
	}

	public List<Integer> addTableToList(int tableNumber) {
		boolean isOnTableList = false;

		for (int i = 0; i < tablesList.size(); i++) {
			if (tablesList.get(i) == tableNumber) {
				isOnTableList = true;
			}
		}

		if (!isOnTableList) {
			tablesList.add(tableNumber);
		}

		return tablesList;
	}

	public List<Order> getOrdersByTableNumber(int tableNumber) {

		List<Order> orderList = new ArrayList<>();

		for (int i = 0; i < activeOrders.size(); i++) {
			if (activeOrders.get(i).getTableNumber() == tableNumber) {
				orderList.add(activeOrders.get(i));
			}
		}
		return orderList;
	}

	public void closeOrderByTableNumber(int tableNumber) {
		for (int i = 0; i < activeOrders.size(); i++) {
			if (activeOrders.get(i).getTableNumber() == tableNumber) {
				closedOrders.add(activeOrders.get(i));
				activeOrders.remove(i);
				
				// Also remove from table list.
				removeTableFromTableList(tableNumber);
			}
		}
	}

	public List<Integer> removeTableFromTableList(int tableNumber) {
		for (int j = 0; j < tablesList.size(); j++) {
			if (tablesList.get(j) == tableNumber) {
				tablesList.remove(j);
			}
		}

		return tablesList;
	}

	public List<Order> getAllActiveOrders() {

		List<Order> orderList = new ArrayList<>();

		for (int i = 0; i < activeOrders.size(); i++) {
			orderList.add(activeOrders.get(i));
		}
		return orderList;
	}

	public List<Order> getAllClosedOrders() {

		List<Order> orderList = new ArrayList<>();

		for (int i = 0; i < closedOrders.size(); i++) {
			orderList.add(closedOrders.get(i));
		}

		return orderList;
	}

	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<>();

		for (int i = 0; i < activeOrders.size(); i++) {
			orderList.add(activeOrders.get(i));
		}

		for (int i = 0; i < closedOrders.size(); i++) {
			orderList.add(closedOrders.get(i));
		}

		return orderList;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String name) {
		this.serverName = name;
	}

	public void setServerId(String id) {
		this.serverId = id;
	}

	public String getServerId() {
		return serverId;
	}

	public ArrayList<Integer> getTablesList() {
		return tablesList;
	}

	public void setTablesList(ArrayList<Integer> tablesList) {
		this.tablesList = tablesList;
	}
}
