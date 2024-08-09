package com.masaro.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masaro.springmongo.domain.Post;

@RestController
@RequestMapping(path="/posts")
public class PostResource {
	
	@Autowired
	PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = postService.findAll();
		return ResponseEntity.ok().body(list);
	}

}
