package com.masaro.springmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masaro.springmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> usersList = new ArrayList<>();
		User u1 = new User(null, "Martha Morgan", "martha@email.com", "4th Avenue");
		User u2 = new User(null, "John Marston", "johnm@email.com", "7th Street, 235");
		usersList.addAll(Arrays.asList(u1, u2));
		return ResponseEntity.ok().body(usersList);
	}

}
