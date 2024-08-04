package com.masaro.springmongo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){

		List<User> usersList = userService.findAll();
		return ResponseEntity.ok().body(usersList);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable String id){
		Optional<User> user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

}
