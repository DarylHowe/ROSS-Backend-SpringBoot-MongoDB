package com.darylhowedevs.restaurantordersystemmongo.menu;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.mongodb.BasicDBObject;

@Service
public class MenuService {

	
	private final MongoTemplate mongoTemplate;

	public MenuService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Menu> getAllMenus() {

		Query q = new Query();
		q.fields().include("menuName");
		List<Menu> menuNamesList = mongoTemplate.find(q, Menu.class);
		return menuNamesList;
	}

	public List<Menu> getMenuByName(String menuName) {

		Query query = new Query().addCriteria(Criteria.where("menuName").is(menuName));
		return mongoTemplate.find(query, Menu.class);
	}

	public void deleteMenuByName(String menuName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("menuName").is(menuName));
		mongoTemplate.remove(query, Menu.class);
	}

	public void addNewItemToMenu(String menuName, Item item) {

		Update update = new Update();
		update.addToSet("menuList", item);
		Criteria criteria = Criteria.where("menuName").is(menuName);
		mongoTemplate.updateFirst(Query.query(criteria), update, "Menus");
	}

	public void deleteMenuItemByName(String menuName, String itemName) {

		Criteria criteria = Criteria.where("menuName").is(menuName);
		Update update = new Update().pull("menuList", new BasicDBObject("itemName", itemName));
		mongoTemplate.updateMulti(Query.query(criteria), update, "Menus");
	}

	public void deleteMenuItemById(String menuName, String itemId) {
		Criteria criteria = Criteria.where("menuName").is(menuName);
		Update update = new Update().pull("menuList", new BasicDBObject("itemId", itemId));
		mongoTemplate.updateMulti(Query.query(criteria), update, "Menus");
	}

	public List<Item> getMenuAllItems(String menuName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("menuName").is(menuName));
		Menu menu = mongoTemplate.findOne(query, Menu.class);
		return menu.getMenuItemList();
	}

}
