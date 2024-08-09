package com.masaro.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaro.springmongo.domain.Post;
import com.masaro.springmongo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}

}
