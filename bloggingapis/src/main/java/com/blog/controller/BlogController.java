package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.BlogEntity;
import com.blog.entity.UserEntity;
import com.blog.service.BlogService;
import com.blog.service.UserService;

@RestController
@CrossOrigin("*")
//@RequestMapping("blogger")
public class BlogController {

	@Autowired 
	private UserService userService;
	
	@Autowired 
	private BlogService blogService;
	
	
	//Get all Users
	
	@GetMapping("/users")
		public ResponseEntity<List> getUsers() {
			List result= userService.getUsers();
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
	
	//Sign Up for User
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> addUser(@RequestBody UserEntity obj1) {
		List result= userService.addUser(obj1);
		if(result == null) {
			return new ResponseEntity<>("Email already Exists !",HttpStatus.OK);
		}
		return new ResponseEntity<>("Record Added Successfully !",HttpStatus.OK);
	}
	
	//Log in For User
	
	@PostMapping("/log-in")
	public ResponseEntity<Boolean> logIn(@RequestBody UserEntity obj1){
		List<UserEntity> res=userService.logIn(obj1.getUsername(),obj1.getPassword());
		
		if(res.isEmpty()) {
			return new ResponseEntity(false,HttpStatus.OK);
		}
		else {
			return new ResponseEntity(res,HttpStatus.OK);
		}
	}
	
	//Edit User Profile
	@PutMapping("/users")
	public ResponseEntity<Boolean> updateProfile(@RequestBody UserEntity obj1){
		UserEntity res=userService.updateProfile(obj1);
			return new ResponseEntity(res,HttpStatus.OK);
	
	}
	
	
	// Get all Blogs with Join
	
	@GetMapping("/blogs")
	public ResponseEntity<List> getBlogs() {
		List<BlogEntity> result= blogService.getBlogs();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	//Get blog by User id
			@GetMapping("/users/{user_id}")
			public ResponseEntity<List> getAllInfoOfUser(@PathVariable(name = "user_id") Long user_id) {
				List result= blogService.getAllInfoOfUser(user_id);
				if(result == null) {
					return new ResponseEntity<>(null,HttpStatus.OK);
				}
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
	
	
			
			
	//Add Blog
	
	@PostMapping("/blogs")
	public ResponseEntity<String> addBlog(@RequestBody BlogEntity obj1) {
		List result= blogService.addBlog(obj1);
		return new ResponseEntity<>("Blog Added Successfully !",HttpStatus.OK);
	}
	
	//Edit Blog
	@PutMapping("/blogs")
	public ResponseEntity<String> editBlog(@RequestBody BlogEntity obj1){
		List  res=blogService.editBlog(obj1);
			return new ResponseEntity<>("Blog Edited Successfully !",HttpStatus.OK);
	
	}
	
	
	//Delete Blog by Id
	@DeleteMapping("blogs/{blog_id}")
	public ResponseEntity<String> deleteBlogByUserId(@PathVariable(name = "blog_id") Long blogId) {
		Boolean result= blogService.deleteBlogByUserId(blogId);
		if(result) {
			return new ResponseEntity<>("Blog Deleted Successfully !",HttpStatus.OK);
		}
		return new ResponseEntity<>("No data Found for this Id",HttpStatus.OK);
	}
}
