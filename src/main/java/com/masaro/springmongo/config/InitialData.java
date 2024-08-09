package com.masaro.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.masaro.springmongo.domain.Post;
import com.masaro.springmongo.domain.User;
import com.masaro.springmongo.dto.AuthorDTO;
import com.masaro.springmongo.repository.PostRepository;
import com.masaro.springmongo.repository.UserRepository;

@Configuration
public class InitialData implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Martha Morgan", "martha.m@email.com", "7th Avenue, 566");
		User u2 = new User(null, "John Watson", "j.watson@email.com", "5th Avenue, 988");
		User u3 = new User(null, "David Silva", "d.silva@email.com", "Avenida Correa Santos, 353");
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post p1= new Post(null, sdf.parse("07/08/2024"), "Novidades vindo por aí...", "Trago novidades na área de IA, galera...", new AuthorDTO(u1));
		Post p2= new Post(null, sdf.parse("08/08/2024"), "Novidades chegando...", "Trago novidades também para backend...", new AuthorDTO(u2));
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		u1.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(u1);
		
	}

}
