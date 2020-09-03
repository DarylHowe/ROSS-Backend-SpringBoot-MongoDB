package com.darylhowedevs.restaurantordersystemmongo.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;


@Document(collection = "Kitchen")
public class Order {

	
	@Id
	private String orderId;
	private static int orderNumberStatic;
	
	private int tableNumber;
	private double totalPrice;
	private List<Item> itemList = new ArrayList<>();
	private boolean isReadyToBeServed = false;
	private String timeStamp;
	private Date date;
	
	
	public Order() {	
		orderId = Integer.toString(orderNumberStatic++);
		date = new Date();
		timeStamp = date.toString();
		updateTotal();
	}

	public Order(int tableNumber, List<Item> itemList) {
		this.tableNumber = tableNumber;
		this.itemList = itemList;
		orderId = Integer.toString(orderNumberStatic++);
		date = new Date();
		timeStamp = date.toString();
		updateTotal();
	}
	

	public Order(int tableNumber) {
		this.tableNumber = tableNumber;
		orderId = Integer.toString(orderNumberStatic++);
		date = new Date();
		timeStamp = date.toString();
		updateTotal();
	}

	public void addItem(Item item) {

		if (item != null) {
			itemList.add(item);
		}
	}

	public Item getItemByIndex(int index) {

		if (index >= 0 && index <= itemList.size() && !itemList.isEmpty()) {

			return itemList.get(index);
		} else {
			return null;
		}
	}

	public Item getItemByName(String name) {

		Item item = null;

		for (int i = 0; i < itemList.size(); i++) {

			if (itemList.get(i).getItemName().equalsIgnoreCase(name)) {
				item = itemList.get(i);
				break;
			}
		}

		return item;
	}

	public void removeItemByIndex(int index) {

		if (index >= 0 && index <= itemList.size() && !itemList.isEmpty()) {
			itemList.remove(index);
		}
	}

	public double updateTotal() {
		totalPrice = 0;

		for (int i = 0; i < itemList.size(); i++) {
			double itemPrice = itemList.get(i).getItemPrice();
			totalPrice += itemPrice;
		}
		return totalPrice;
	}

	public void displayOrder() {
		System.out.println("Table number : " + tableNumber);
		System.out.println("Total items: " + itemList.size());
		System.out.println("Status (isReadyToBeServed): " + isReadyToBeServed);

		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(itemList.get(i));
		}

		System.out.println("Total price: " + updateTotal());
	}

	public void removeLastItem() {
		itemList.remove(itemList.size() - 1);
	}

	public void clearAllItems() {
		itemList.clear();
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public double getTotalPrice() {
		totalPrice = 0;

		for (int i = 0; i < itemList.size(); i++) {
			double itemPrice = itemList.get(i).getItemPrice();
			totalPrice += itemPrice;
		}
		return totalPrice;
	}

	public void setTotalPrice(double total) {
		this.totalPrice = total;
	}

	public static int getOrderNumber() {
		return orderNumberStatic;
	}

	public boolean getIsReadyToBeServed() {
		return isReadyToBeServed;
	}

	public void setIsReadyToBeServed(boolean isReadyToBeServed) {
		this.isReadyToBeServed = isReadyToBeServed;
	}

	public int getTotalItems() {
		return itemList.size();
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> orderList) {
		this.itemList = orderList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
