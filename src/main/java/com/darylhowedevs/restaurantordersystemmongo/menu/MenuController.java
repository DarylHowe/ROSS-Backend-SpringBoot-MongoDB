package com.darylhowedevs.restaurantordersystemmongo.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.darylhowedevs.restaurantordersystemmongo.item.Main;



@RestController
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	private MenuRepository menuRepository;
	
	
	public MenuController (MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
	// http://localhost:8080/menus/testAPI
	@GetMapping("/testAPI")
	public String testAPI() {
		return "Hello World";
	}
	
	// http://localhost:8080/menus/getallmenus
	@GetMapping("/getallmenus")
	public List<Menu> getAllMenusMongo() {
		return menuRepository.findAll();
	}
	
	
	// http://localhost:8080/menus/getmenu/Main
	// http://localhost:8080/menus/getmenu/{menuName}
	@GetMapping("/getmenu/{menuName}")
	public List<Menu> getMenuByName(@PathVariable String menuName) {
		
		return menuService.getMenuByName(menuName);
	}
	
	// http://localhost:8080/menus/createemptymenu/Breakfast
	// http://localhost:8080/menus/createemptymenu/{menuName}
	@PostMapping("/createemptymenu/{menuName}")
	public Menu createEmptyMenu(@PathVariable String menuName){
		
		//Menu menu = new Menu(menuName);
		return menuRepository.insert(new Menu(menuName));
	}
	
	// http://localhost:8080/menus/deletemenubyname/Breakfast
	@DeleteMapping("/deletemenubyname/{menuName}")
	public void deleteMenuByName(@PathVariable String menuName) {
		menuService.deleteMenuByName(menuName);
	}
	
	// http://localhost:8080/menus/deletemenubyid/{menuId}
	@DeleteMapping("/deletemenubyid/{menuId}")
	public void deleteMenuById(@PathVariable String menuId) {
		menuRepository.deleteById(menuId);
	}
		
	/*
	 * {"itemName":"7UP","itemPrice":2.5,"itemType":"Drink"}
	 */
	// http://localhost:8080/menus/addnewitemtomenu/{menuName}
	@PostMapping("addnewitemtomenu/{menuName}")
	public void addNewItemToMenu(@PathVariable String menuName, @RequestBody Item item) {
		menuService.addNewItemToMenu(menuName, item);
	}
	

	// http://localhost:8080/menus/deletemenuitembyname/{menuName}/{itemName}
	@DeleteMapping("/deletemenuitembyname/{menuName}/{itemName}")
	public void deleteMenuItemByName(@PathVariable String menuName,@PathVariable String itemName) {
		menuService.deleteMenuItemByName(menuName, itemName);
	}
	
	// http://localhost:8080/menus/deletemenuitembyid/{menuName}/{itemId}
	@DeleteMapping("/deletemenuitembyid/{menuName}/{itemId}")
	public void deleteMenuItemById(@PathVariable String menuName, @PathVariable String itemId) {
		menuService.deleteMenuItemById(menuName, itemId);
	}
	
	
	
	
	// http://localhost:8080/menus/getallitems
	@GetMapping("/getallitems")
	public List<Item> getAllItems() {
		
		List<Menu> menuList = menuRepository.findAll();
				
		List<Item> allItems = null;
		for (int i = 0; i < menuList.size(); i++) {

			for (int j = 0; j < menuList.get(i).getMenuList().size(); j++) {

				allItems.add(menuList.get(i).getMenuList().get(j));
			}

		}
		return allItems;
	}
	
	
	
	// http://localhost:8080/menus/getallmenuitems/Main
	// http://localhost:8080/menus/getallmenuitems/{menuName}
	@GetMapping("/getallmenuitems/{menuName}")
	public List<Item> getAllMenuItems(@PathVariable String menuName) {
		
		return menuService.getMenuAllItems(menuName);
	}

}