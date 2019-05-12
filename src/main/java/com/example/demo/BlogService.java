package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Post;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public List<Post> getPost(){
		List<Post> tempList = new ArrayList<>();
		blogRepository.findAll().forEach(tempList::add);//java 8 lambda .foreach(collection::method)
		return tempList;
	}
	
	public Post getPost(int id) {
		return blogRepository.findById(id).get();
	}
	
	public List<Post> getPost(String param, String value) {
		List<Post> tempList = new ArrayList<>();
		if(param.equals("isFrom"))
			return blogRepository.findByTitle(value);
		blogRepository.findAll().forEach(tempList::add);
		return tempList;
	}
	
	public void addPost(Post parcel) {
		blogRepository.save(parcel);
	}
	
	public void deletePost(int id) {
		blogRepository.deleteById(id);
	}
	
	public void updatePost(Post parcel) {
		deletePost(parcel.getId());
		addPost(parcel);
	}
}
