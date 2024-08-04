package com.masaro.springmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.repository.UserRepository;

@Configuration
public class InitialData implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User u1 = new User(null, "Martha Morgan", "martha.m@email.com", "7th Avenue, 566");
		User u2 = new User(null, "John Watson", "j.watson@email.com", "5th Avenue, 988");
		User u3 = new User(null, "David Silva", "d.silva@email.com", "Avenida Correa Santos, 353");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
	}

}
