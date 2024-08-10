package com.masaro.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masaro.springmongo.config.URL;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(path = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue="") String text){
		List<Post> postList = postService.findByTitle(text);
		return ResponseEntity.ok().body(postList);
	}
	
	@GetMapping(path = "/textsearch")
	public ResponseEntity<List<Post>> findText(@RequestParam(value="text", defaultValue="") String text){
		List<Post> postList = postService.findText(text);
		return ResponseEntity.ok().body(postList);
	}

}
