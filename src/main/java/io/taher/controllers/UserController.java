package io.taher.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.taher.models.User;
import io.taher.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public List<User> test() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.POST)
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.PUT)
	public void editUser(@RequestBody User user,@PathVariable int id) {
		userService.editUser(user, id);
	}
	
	@RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody User user) {
		userService.deleteUser(user);
	}
	
	
	
	
}
