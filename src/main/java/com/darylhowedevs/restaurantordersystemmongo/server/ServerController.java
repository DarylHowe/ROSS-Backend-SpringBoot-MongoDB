package com.darylhowedevs.restaurantordersystemmongo.server;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darylhowedevs.restaurantordersystemmongo.order.Order;



@RestController
@CrossOrigin("*")
@RequestMapping("/server")
public class ServerController {

	@Autowired
	private ServerService serverService;

	private ServerRepository serverRepository;

	public ServerController(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}
	
	// http://localhost:8080/server/testAPI
	@GetMapping("/testAPI")
	public String testAPI() {
		return "Hello World";
	}

	 // http://localhost:8080/server/createserver/{serverName}
	@PostMapping("/createserver/{serverName}")
	public Server createServer(@PathVariable String serverName) {
		
		Server server = new Server(serverName);
		return serverRepository.insert(server);
	}
	
	// http://localhost:8080/server/getallservers
	@GetMapping("/getallservers")
	public List<Server> getAllServers() {
		return serverRepository.findAll();
		
	}
	
	// http://localhost:8080/server/getserverbyid/{serverId}
	@GetMapping("getserverbyid/{serverId}")
	public Optional<Server> getServerById(@PathVariable String serverId) {
		return serverRepository.findById(serverId);
	}
	

	// http://localhost:8080/server/getservertables/{serverId}
	@GetMapping("getservertables/{serverId}")
	public List<Integer> getTables(@PathVariable String serverId){
		
		return serverService.getServerTables(serverId);
	}
	
		
	// http://localhost:8080/server/removetable/{serverId}/{tableNumber}
	@DeleteMapping("removetable/{serverId}/{tableNumber}")
	public List<Integer>removeTable(@PathVariable String serverId, @PathVariable int tableNumber){
		
		return serverService.removeTable(serverId, tableNumber);
	}
	
	

	// http://localhost:8080/server/createorder/{serverId}
	@PostMapping("/createorder/{serverId}")
	public void createOrder(@PathVariable String serverId, @RequestBody Order order) {
				
		serverService.createOrder(serverId, order);
	}
	
	
	// http://localhost:8080/server/getorderbytablenumber/{serverId}/{tableNumber}
	@GetMapping("/getorderbytablenumber/{serverId}/{tableNumber}")
	public List<Order> getOrderByTableNumber(@PathVariable String serverId, @PathVariable int tableNumber) {
		return serverService.getOrderByTableNumber(serverId, tableNumber);
	}
	
	
	// http://localhost:8080/server/addtabletotablelist/{serverId}/{tableNumber}
	@PostMapping("/addtabletotablelist/{serverId}/{tableNumber}")
	public List<Integer> addTableToTableList (@PathVariable String serverId, @PathVariable int tableNumber){
		return serverService.addTableToTableList(serverId, tableNumber);
	}
	
	// http://localhost:8080/server/closeorderbytablenumber/{serverId}/{tableNumber}
	@PutMapping("/closeorderbytablenumber/{serverId}/{tableNumber}")
	public Server closeOrderByTableNumber(@PathVariable String serverId, @PathVariable int tableNumber) {
		return serverService.closeOrderByTableNumber(serverId, tableNumber);
	}
	
	// http://localhost:8080/server/getallorders/{serverId}
	@GetMapping("/getallorders/{serverId}")
	public List<Order> getAllOrders(@PathVariable String serverId) {
		return serverService.getAllOrders(serverId);
	}

	// http://localhost:8080/server/getallactiveorders/{serverId}
	@GetMapping("getallactiveorders/{serverId}")
	public List<Order> getAllActiveOrders(@PathVariable String serverId) {
		return serverService.getAllActiveOrders(serverId);
	}
	
	// http://localhost:8080/server/getallclosedorders/{serverId}
	@GetMapping("/getallclosedorders/{serverId}")
	public List<Order> getAllClosedOrders(@PathVariable String serverId) {
		return serverService.getAllClosedOrders(serverId);
	}
	


}
