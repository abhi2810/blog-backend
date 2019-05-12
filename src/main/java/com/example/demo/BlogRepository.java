package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Post;

public interface BlogRepository extends CrudRepository<Post, Integer> {
	
	public List<Post> findByTitle(String title);
	
}
