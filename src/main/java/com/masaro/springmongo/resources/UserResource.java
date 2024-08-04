package com.masaro.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.dto.UserDTO;
import com.masaro.springmongo.dto.UserResponseDTO;
import com.masaro.springmongo.services.UserService;
import com.masaro.springmongo.services.security.TokenService;

@RestController
@RequestMapping(path = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	
	
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
	
	@PostMapping(path = "/add")
	public ResponseEntity<UserResponseDTO> insert(@RequestBody UserDTO requestBody){
		User user = new User();
		user.setName(requestBody.name());
		user.setEmail(requestBody.email());
		user.setAddress(requestBody.address());
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		String token = tokenService.generateToken(user);
		return ResponseEntity.created(uri).body(
				new UserResponseDTO(token));
	}

}
