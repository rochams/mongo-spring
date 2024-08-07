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
	
	
	public User update(User user, UserDTO dto) {
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setAddress(dto.address());
		return userRepository.save(user);
	}
	

	public void delete(String id) {
		User user = this.findById(id);
		userRepository.deleteById(user.getId());
	}

}
