package com.masaro.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.dto.UserDTO;
import com.masaro.springmongo.repository.UserRepository;
import com.masaro.springmongo.services.exception.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}
	
	
	public User findById(String id) {
		Optional<? extends User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new NotFoundException("Objeto não encontrado.");
		}
		return user.get();
	}
	
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	
	public void update(User user) {
		User newUser = this.findById(user.getId());
		updateUser(user, newUser);
	}
	
	
	private void updateUser(User user, User newUser) {
		newUser.setId(user.getId());
		newUser.setName(user.getName());
		newUser.setAddress(user.getAddress());
		newUser.setEmail(user.getEmail());		
	}
	

	public void delete(String id) {
		User user = this.findById(id);
		userRepository.deleteById(user.getId());
	}

}
