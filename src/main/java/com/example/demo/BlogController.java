package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Post;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/post")
	public List<Post> getAllPost(){
		return blogService.getPost();
	}
	
	@RequestMapping("/post/{id}")
	public Post getPost(@PathVariable int id, HttpServletResponse response) {
		try {
			return blogService.getPost(id);
		} catch(Exception exc) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"parcel-"+id,exc);
		}
	}
	
	@RequestMapping("/post/{param}/{value}")
	public List<Post> getPost(@PathVariable String param, @PathVariable String value) {
		try {
			return blogService.getPost(param,value);
		} catch(Exception exc) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"parcel-"+param+":"+value,exc);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/post")
	public void addPost(@RequestBody Post parcel) {
		try {
			blogService.addPost(parcel);
		} catch(Exception exc) {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN,"parcel-"+parcel.getId(),exc);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/post/id/{id}")
	public void deletePost(@PathVariable int id) {
		blogService.deletePost(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/post/id")
	public void updatePost(@RequestBody Post parcel) {
		try {
			blogService.updatePost(parcel);
		} catch(Exception exc) {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN,"parcel-"+parcel.getId(),exc);
		}
	}
	
}