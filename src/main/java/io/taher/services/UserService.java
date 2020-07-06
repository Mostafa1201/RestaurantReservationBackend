package io.taher.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.taher.models.User;
import io.taher.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>(); 
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User getUser(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public void editUser(User user, int id) {
		userRepository.save(user);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User getAdminUser() {
		return userRepository.findTopByType("admin");
	}
}
