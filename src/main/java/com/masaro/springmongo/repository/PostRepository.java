package com.masaro.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.masaro.springmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
