package com.darylhowedevs.restaurantordersystemmongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.darylhowedevs.restaurantordersystemmongo.item.DrinkAlcohol;
import com.darylhowedevs.restaurantordersystemmongo.item.DrinkNonAlcohol;
import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.darylhowedevs.restaurantordersystemmongo.item.Main;
import com.darylhowedevs.restaurantordersystemmongo.item.Starter;
import com.darylhowedevs.restaurantordersystemmongo.kitchen.KitchenRepository;
import com.darylhowedevs.restaurantordersystemmongo.menu.Menu;
import com.darylhowedevs.restaurantordersystemmongo.menu.MenuRepository;
import com.darylhowedevs.restaurantordersystemmongo.order.Order;
import com.darylhowedevs.restaurantordersystemmongo.server.Server;
import com.darylhowedevs.restaurantordersystemmongo.server.ServerRepository;

/**
 * DB Seeder - This class seeds MongoDB with data. Each time the application
 * restarts all data is removed and re-seeded to the initial seed state.
 */
@Component
public class DBSeeder implements CommandLineRunner {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private ServerRepository serverRepository;
	@Autowired
	private KitchenRepository kitchenRepository;

	private ArrayList<Menu> menusList = new ArrayList<>();

	@Override
	public void run(String... args) throws Exception {

		seedMenuData();
		seedServerData();
		seedKitchenData();
	}

	/**
	 * A method which seeds 4 menus and seeds items into menu. 
	 */
	private void seedMenuData() {
		menuRepository.deleteAll();

		menusList.add(new Menu("Main"));
		menusList.get(0).addItemToMenu(new Main("Pizza", 12.95));
		menusList.get(0).addItemToMenu(new Main("Beef Burger", 15.65));
		menusList.get(0).addItemToMenu(new Main("Lasange", 13.00));
		menusList.get(0).addItemToMenu(new Main("Chicken Burger", 16.45));
		menusList.get(0).addItemToMenu(new Main("Chicken Curry", 18.99));
		menusList.get(0).addItemToMenu(new Main("Fillet Steak", 25.50));
		menusList.get(0).addItemToMenu(new Main("Seafood Paella", 21.99));

		menusList.add(new Menu("Starter"));
		menusList.get(1).addItemToMenu(new Starter("Chicken Wings", 7.50));
		menusList.get(1).addItemToMenu(new Starter("Garlic Bread", 2.85));
		menusList.get(1).addItemToMenu(new Starter("Cheese Platter", 9.50));
		menusList.get(1).addItemToMenu(new Starter("Tomato Soup", 4.99));
		menusList.get(1).addItemToMenu(new Starter("Bruschetta", 5.60));
		menusList.get(1).addItemToMenu(new Starter("Mussels", 8.95));
		menusList.get(1).addItemToMenu(new Starter("Prawn Pil Pil", 11.20));

		menusList.add(new Menu("Drinks"));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Water", 0));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Coke", 2.50));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("7UP", 2.50));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Fanta", 2.50));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Milk", 1.00));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Lemonade", 2.50));
		menusList.get(2).addItemToMenu(new DrinkNonAlcohol("Ribenna", 2.50));

		menusList.add(new Menu("Alcohol"));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Red Wine Small", 8.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Red Wine Large", 11.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Heiniken Pint", 5.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Guiness Pint", 4.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Coors Pint", 5.00));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Budweiser Pint", 5.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Jameson Single", 7.50));
		menusList.get(3).addItemToMenu(new DrinkAlcohol("Vodka Single", 6.50));

		List<Menu> menuList = Arrays.asList(menusList.get(0), menusList.get(1), menusList.get(2), menusList.get(3));
		menuRepository.saveAll(menuList);
	}

	/**
	 * A method which seeds 3 servers. Server 'Daryl Howe' seeded with 1 closed order and 1 open order. 
	 */
	private void seedServerData() {

		serverRepository.deleteAll();

		Server server01 = new Server("Daryl Howe");
		Server server02 = new Server("Sandy Luc");
		Server server03 = new Server("Paul Rodgers");

		List<Item> itemList01 = new ArrayList<>();
		itemList01.add(menusList.get(0).getItemByIndex(3));
		itemList01.add(menusList.get(1).getItemByIndex(3));
		itemList01.add(menusList.get(3).getItemByIndex(1));

		List<Item> itemList02 = new ArrayList<>();
		itemList02.add(menusList.get(0).getItemByIndex(1));
		itemList02.add(menusList.get(0).getItemByIndex(5));
		itemList02.add(menusList.get(2).getItemByIndex(5));
		itemList02.add(menusList.get(3).getItemByIndex(1));
		itemList02.add(menusList.get(3).getItemByIndex(1));

		Order order01 = new Order(10, itemList01);
		Order order02 = new Order(20, itemList02);

		server01.createOrder(order01);
		server01.closeOrderByTableNumber(10);

		server01.createOrder(order02);

		List<Server> serverList = Arrays.asList(server01, server02, server03);
		serverRepository.saveAll(serverList);
	}

	/**
	 * A method which seeds data to the kitchen. 2 orders are seeded one of which 'isReady' to be served.
	 */
	private void seedKitchenData() {

		kitchenRepository.deleteAll();

		List<Item> itemList = new ArrayList<>();
		itemList.add(menusList.get(0).getItemByIndex(4));
		itemList.add(menusList.get(0).getItemByIndex(3));
		itemList.add(menusList.get(0).getItemByIndex(3));
		itemList.add(menusList.get(3).getItemByIndex(1));
		itemList.add(menusList.get(3).getItemByIndex(5));

		List<Item> itemList01 = new ArrayList<>();
		itemList01.add(menusList.get(1).getItemByIndex(4));
		itemList01.add(menusList.get(1).getItemByIndex(3));
		itemList01.add(menusList.get(0).getItemByIndex(1));
		itemList01.add(menusList.get(3).getItemByIndex(4));
		itemList01.add(menusList.get(3).getItemByIndex(6));

		Order order01 = new Order(33, itemList);
		Order order02 = new Order(41, itemList01);

		order01.setIsReadyToBeServed(true);

		kitchenRepository.save(order01);
		kitchenRepository.save(order02);
	}
}
