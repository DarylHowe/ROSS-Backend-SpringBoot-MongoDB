package com.darylhowedevs.restaurantordersystemmongo.kitchen;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darylhowedevs.restaurantordersystemmongo.item.Item;
import com.darylhowedevs.restaurantordersystemmongo.order.Order;

@RestController
@CrossOrigin("*")
@RequestMapping("/kitchen")
public class KitchenController {

	@Autowired
	private KitchenService kitchenSerivce;

	@Autowired
	private KitchenRepository kitchenRepository;

	// http://localhost:8080/kitchen/getallorders
	@GetMapping("/getallorders")
	public List<Order> getAllOrders() {

		return kitchenRepository.findAll();
	}

	// http://localhost:8080/kitchen/addorder
	@PostMapping("/addorder")
	public void addOrder(@RequestBody Order order) {

		kitchenRepository.save(order);
	}

	// http://localhost:8080/kitchen/getorderitemsbytablenumber/{tableNumber}
	@GetMapping("/getorderitemsbytablenumber/{tableNumber}")
	public List<Item> getOrderItemsByTableNumber(@PathVariable int tableNumber) {

		return kitchenSerivce.getOrderItemsByTableNumber(tableNumber);
	}

	// http://localhost:8080/kitchen/getorderbyid/{id}
	@GetMapping("/getorderbyid/{id}")
	public Optional<Order> getOrderById(@PathVariable String id) {

		return kitchenRepository.findById(id);
	}

	// http://localhost:8080/kitchen/orderisreadybyid/{id}
	@PostMapping("orderisreadybyid/{id}")
	public Order orderIsReadyById(@PathVariable String id) {
		Order order = kitchenSerivce.orderIsReadyById(id);
		kitchenRepository.save(order);
		return order;

	}

	// http://localhost:8080/kitchen/orderisservedbyid/{id}
	@PostMapping("orderisservedbyid/{id}")
	public boolean orderIsServedById(@PathVariable String id) {
		boolean isReadyToBeServed = kitchenSerivce.orderIsServedById(id);
		if (isReadyToBeServed) {
			kitchenRepository.deleteById(id);
		}
		return isReadyToBeServed;
	}

}
