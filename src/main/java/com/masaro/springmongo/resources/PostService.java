package com.masaro.springmongo.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaro.springmongo.config.URL;
import com.masaro.springmongo.domain.Post;
import com.masaro.springmongo.repository.PostRepository;
import com.masaro.springmongo.services.exception.NotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isEmpty()) {
			throw new NotFoundException("Objeto não encontrado.");
		}
		return post.get();
	}
	
	public List<Post> findByTitle(String text){
		text = URL.decoder(text);
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findText(String text){
		return postRepository.findText(text);
	}

}
