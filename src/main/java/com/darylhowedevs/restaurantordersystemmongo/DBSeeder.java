package com.darylhowedevs.restaurantordersystemmongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.darylhowedevs.restaurantordersystemmongo.item.DrinkAlcohol;
import com.darylhowedevs.restaurantordersystemmongo.item.DrinkNonAlcohol;
import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.darylhowedevs.restaurantordersystemmongo.item.Main;
import com.darylhowedevs.restaurantordersystemmongo.item.Starter;
import com.darylhowedevs.restaurantordersystemmongo.menu.Menu;
import com.darylhowedevs.restaurantordersystemmongo.menu.MenuRepository;

@Component
public class DBSeeder implements CommandLineRunner {

	private MenuRepository menuRepository;

	public DBSeeder(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		menuRepository.deleteAll();

		Menu menu01 = new Menu("Main");
		Item item01 = new Main("Pizza", 12.95);
		Item item02 = new Main("Beef Burger", 15.65);
		Item item03 = new Main("Lasange", 13.00);
		Item item04 = new Main("Chicken Burger", 16.45);
		Item item05 = new Main("Chicken Curry", 18.99);
		Item item06 = new Main("Fillet Steak", 25.50);
		Item item07 = new Main("Seafood Paella", 21.99);

		menu01.addItemToMenu(item01);
		menu01.addItemToMenu(item02);
		menu01.addItemToMenu(item03);
		menu01.addItemToMenu(item04);
		menu01.addItemToMenu(item05);
		menu01.addItemToMenu(item06);
		menu01.addItemToMenu(item07);

		Menu menu02 = new Menu("Starter");
		Item item08 = new Starter("Chicken Wings", 7.50);
		Item item09 = new Starter("Garlic Bread", 2.85);
		Item item10 = new Starter("Cheese Platter", 9.50);
		Item item11 = new Starter("Tomato Soup", 4.99);
		Item item12 = new Starter("Bruschetta", 5.60);
		Item item13 = new Starter("Mussels", 8.95);
		Item item14 = new Starter("Prawn Pil Pil", 11.20);

		menu02.addItemToMenu(item08);
		menu02.addItemToMenu(item09);
		menu02.addItemToMenu(item10);
		menu02.addItemToMenu(item11);
		menu02.addItemToMenu(item12);
		menu02.addItemToMenu(item13);
		menu02.addItemToMenu(item14);

		Menu menu03 = new Menu("Drink No Alcohol");
		Item item15 = new DrinkNonAlcohol("Water", 0);
		Item item16 = new DrinkNonAlcohol("Coke", 2.50);
		Item item17 = new DrinkNonAlcohol("7UP", 2.50);
		Item item18 = new DrinkNonAlcohol("Fanta", 2.50);
		Item item19 = new DrinkNonAlcohol("Milk", 1.00);
		Item item20 = new DrinkNonAlcohol("Lemonade", 2.50);
		Item item21 = new DrinkNonAlcohol("Ribenna", 2.50);

		menu03.addItemToMenu(item15);
		menu03.addItemToMenu(item16);
		menu03.addItemToMenu(item17);
		menu03.addItemToMenu(item18);
		menu03.addItemToMenu(item19);
		menu03.addItemToMenu(item20);
		menu03.addItemToMenu(item21);

		Menu menu04 = new Menu("Drink Alcohol");
		Item item22 = new DrinkAlcohol("Red Wine Small", 8.50);
		Item item23 = new DrinkAlcohol("Red Wine Large", 11.50);
		Item item24 = new DrinkAlcohol("Heiniken Pint", 5.50);
		Item item25 = new DrinkAlcohol("Guiness Pint", 4.50);
		Item item26 = new DrinkAlcohol("Coors Pint", 5.00);
		Item item27 = new DrinkAlcohol("Budweiser Pint", 5.50);
		Item item28 = new DrinkAlcohol("Jameson Single", 7.50);
		Item item29 = new DrinkAlcohol("Vodka Single", 6.50);

		menu04.addItemToMenu(item22);
		menu04.addItemToMenu(item23);
		menu04.addItemToMenu(item24);
		menu04.addItemToMenu(item25);
		menu04.addItemToMenu(item26);
		menu04.addItemToMenu(item27);
		menu04.addItemToMenu(item28);
		menu04.addItemToMenu(item29);

		List<Menu> menuList = Arrays.asList(menu01, menu02, menu03, menu04);
		menuRepository.saveAll(menuList);

	}
}
