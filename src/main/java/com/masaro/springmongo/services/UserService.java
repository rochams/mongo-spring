package com.masaro.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.repository.UserRepository;
import com.masaro.springmongo.services.exception.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}
	
	public Optional<User> findById(String id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new NotFoundException("Objeto não encontrado.");
		}
		
		return user;
	}

}
