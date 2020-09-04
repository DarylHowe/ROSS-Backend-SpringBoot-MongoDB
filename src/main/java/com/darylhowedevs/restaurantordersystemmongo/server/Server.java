package com.darylhowedevs.restaurantordersystemmongo.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.darylhowedevs.restaurantordersystemmongo.order.Order;

/**
 * Server - A server holds 3 lists. 1. 'Active Orders'. An active order has not
 * yet been closed(ie has not yet been paid for).
 * 
 * 2. 'Closed Orders'. After an order has been closed (ie paid) the order will
 * be removed from the 'Active Orders' list and added to the 'Closed Orders'
 * list.
 * 
 * 3. 'Tables List'. A list of all the active table numbers.
 *
 */
@Document(collection = "Servers")
public class Server {

	@Id
	private String serverId;
	private String serverName;
	private static int staticId;
	private ArrayList<Order> activeOrders = new ArrayList<>();
	private ArrayList<Order> closedOrders = new ArrayList<>();
	private ArrayList<Integer> tablesList = new ArrayList<>();

	public Server(String serverName) {
		this.serverName = serverName;
		this.serverId = Integer.toString(staticId++);
	}

	/**
	 * A method to add an order to the severs 'activeOrders' list. The table number
	 * will also be added to the servers 'tablesList'.
	 * 
	 * @param order
	 */
	public void createOrder(Order order) {
		activeOrders.add(order);
		addTableToList(order.getTableNumber());
	}

	public List<Integer> addTableToList(int tableNumber) {
		boolean isAlreadyOnTableList = false;

		for (int i = 0; i < tablesList.size(); i++) {
			if (tablesList.get(i) == tableNumber) {
				isAlreadyOnTableList = true;
			}
		}

		if (!isAlreadyOnTableList) {
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

	/**
	 * A method which removes a table from the 'activeOrders' list and adds it to
	 * the 'closedOrders' list. The table is also removed from the 'tablesList' as
	 * it is no longer active.
	 * 
	 * @param tableNumber
	 */
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
