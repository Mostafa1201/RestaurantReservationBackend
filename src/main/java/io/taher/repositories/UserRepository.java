package io.taher.repositories;

import org.springframework.data.repository.CrudRepository;

import io.taher.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
	public User findTopByType(String type);
}
