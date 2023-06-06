package com.example.CRUDUnitTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User postUser(User user) {
		if (user != null) userRepository.save(user);
		return user;
	}
	
	public User getUser(Long id) {
		Optional<User> userFromDB = userRepository.findById(id);
		return userFromDB.orElse(null);
	}
	
	public User putUser(Long id, User user) {
		if (userRepository.existsById(id) && user != null) {
			return userRepository.save(user);
		}
		return null;
	}
	
	public void delUser(Long id) {
		userRepository.deleteById(id);
	}
}
