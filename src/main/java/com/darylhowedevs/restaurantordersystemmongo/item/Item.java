package com.darylhowedevs.restaurantordersystemmongo.item;

/**
 * Item - a class to represent a generic menu item. 
 *
 */
public class Item {

	protected static int itemIdStatic;
	protected String itemId;
	protected String itemName;
	protected double itemPrice;
	protected String itemType;

	// Default constructor needed for MongoDB
	public Item() {
		this.itemId = Integer.toString(itemIdStatic++);
	}

	public Item(String itemName, double itemPrice, String itemType) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemType = itemType;
		this.itemId = Integer.toString(itemIdStatic++);
	}

	public String getItemName() {
		return itemName;
	}

	public void setName(String name) {
		this.itemName = name;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setPrice(double price) {
		this.itemPrice = price;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public String toString() {
		return "Item name: " + itemName + "\n" + "Item price: " + itemPrice;
	}

}
