package com.darylhowedevs.restaurantordersystemmongo.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class MenuRepositoryImpl implements MenuRepositoryCustom {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu getMenuByName(String menuName) {
		Menu menu = null;

		List<Menu> menuList = menuRepository.findAll();
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getMenuName().equalsIgnoreCase(menuName)) {
				menu = menuList.get(i);
			}
		}

		return menu;
	}

}
