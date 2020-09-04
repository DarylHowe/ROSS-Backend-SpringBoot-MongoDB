package com.darylhowedevs.restaurantordersystemmongo.menu;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;

/**
 * Menu - A menu holds a list of items.
 */
@Document(collection = "Menus")
public class Menu {

	@Id
	protected String menuId;
	protected String menuName;
	protected ArrayList<Item> menuItemList = new ArrayList<>();

	// MongoDB will auto assign a 'menuId'
	public Menu(String menuName) {
		this.menuName = menuName;
	}

	public void addItemToMenu(Item item) {
		menuItemList.add(item);
	}

	public void removeItemFromMenuByName(String itemName) {
		for (int i = 0; i < menuItemList.size(); i++) {
			if (itemName.equals(menuItemList.get(i).getItemName())) {
				menuItemList.remove(i);
			}
		}
	}

	public Item getItemByIndex(int index) {
		return menuItemList.get(index);
	}

	public Item getItemByName(String itemName) {
		Item item = null;
		
		for (int i = 0; i < menuItemList.size(); i++) {

			if (itemName.equalsIgnoreCase(menuItemList.get(i).getItemName())) {
				item = menuItemList.get(i);
			}
		}
		return item;
	}

	public int getMenuSize() {
		return menuItemList.size();
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

	public ArrayList<Item> getMenuItemList() {
		return menuItemList;
	}

}
