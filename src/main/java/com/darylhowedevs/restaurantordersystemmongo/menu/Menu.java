package com.darylhowedevs.restaurantordersystemmongo.menu;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;

@Document(collection = "Menus")
public class Menu {

	@Id
	protected String menuId;
	protected String menuName;
	protected ArrayList<Item> menuList = new ArrayList<>();

	// MongoDB will auto assign a 'menuId'
	public Menu(String menuName) {
		this.menuName = menuName;
	}

	public void addItemToMenu(Item item) {
		menuList.add(item);
	}

	public void removeItemFromMenuByIndex(int index) {
		menuList.remove(index);
	}

	public void removeItemFromMenuByName(String itemName) {
		for (int i = 0; i < menuList.size(); i++) {
			if (itemName.equals(menuList.get(i).getItemName())) {
				menuList.remove(i);
			}
		}
	}

	public Item getItemByIndex(int index) {
		return menuList.get(index);
	}

	public Item getItemByName(String itemName) {

		Item item = null;

		for (int i = 0; i < menuList.size(); i++) {

			if (itemName.equalsIgnoreCase(menuList.get(i).getItemName())) {
				item = menuList.get(i);
			}
		}

		return item;
	}

	public int getMenuSize() {
		return menuList.size();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setName(String name) {
		this.menuName = name;
	}

	public String getMenuId() {
		return menuId;
	}
	
	public ArrayList<Item> getMenuList() {
		return menuList;
	}

}
