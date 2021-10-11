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
import com.blog.entity.CommentsEntity;
import com.blog.entity.FollowersEntity;
import com.blog.entity.UserEntity;
import com.blog.service.BlogService;
import com.blog.service.CommentsService;
import com.blog.service.FollowersService;
import com.blog.service.UserService;

@RestController
@CrossOrigin("*")
public class BlogController {

	@Autowired 
	private UserService userService;
	
	@Autowired 
	private BlogService blogService;
	
	@Autowired
	private CommentsService commentService;
	
	@Autowired
	private FollowersService followersService;
	
	
	//Get all Users
	
	@GetMapping("/users")
		public ResponseEntity<List> getUsers() {
			List result= userService.getUsers();
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
	
	//Get user by user id
	@GetMapping("/user-by-id/{user_id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable(name = "user_id") Long user_id) {
		UserEntity result= userService.getUserById(user_id);
		if(result == null) {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	//Sign Up for User
	
	@PostMapping("/sign-up")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity obj1) {
		UserEntity result= userService.addUser(obj1);
		return new ResponseEntity<UserEntity>(result,HttpStatus.OK);
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
	
	//Get blogs for current user
		@GetMapping("/blogs/{user_user_id}")
		public ResponseEntity<List> getAllBlogsForLoggedInUser(@PathVariable(name="user_user_id") Long user_user_id) {
			List<BlogEntity> result= blogService.getAllBlogsForLoggedInUser(user_user_id);
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
	
	// Get all Blogs for logged in user
	
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
	@DeleteMapping("/blogs/{blog_id}")
	public ResponseEntity<String> deleteBlogByUserId(@PathVariable(name = "blog_id") Long blogId) {
		Boolean result= blogService.deleteBlogByUserId(blogId);
		if(result) {
			return new ResponseEntity<>("Blog Deleted Successfully !",HttpStatus.OK);
		}
		return new ResponseEntity<>("No data Found for this Id",HttpStatus.OK);
	}
	
	//Add comments
	@PostMapping("/comments")
	public ResponseEntity<String> addComments(@RequestBody CommentsEntity comm) {
		List result= commentService.addComments(comm);

		return new ResponseEntity<>("Comment Uploaded Successfully !",HttpStatus.OK);
	}
	
	//Get Comments,users data by passing Blog Id
	
	@GetMapping("/comments/{blog_id}")
	public ResponseEntity<List<CommentsEntity>> getAllCommentData(@PathVariable(name = "blog_id") Long blog_id) {
		List<CommentsEntity> result= commentService.getAllCommentData(blog_id);
		if(result == null) {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<List<CommentsEntity>>(result,HttpStatus.OK);
	}
	
	//Follow to the user
	@PostMapping("/follow")
	public ResponseEntity<String> followUser(@RequestBody FollowersEntity obj1) {
		String result= followersService.followUser(obj1);
		if(result != "Error")
		{
			return new ResponseEntity<>("Follow Successfully !",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Error",HttpStatus.OK);
		}
		
	}
	
	// Get all users other than following users
	
	@GetMapping("/get-all-unfollow/{user_user_id}")
	public ResponseEntity<List> unfollowUser(@PathVariable(name="user_user_id") Long user_user_id) {
	List result= followersService.unfollowUser(user_user_id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	//Get count of following
	@GetMapping("/get-following-count/{user_id}")
	public int noOfFollowing(@PathVariable(name="user_id") Long user_id) {
		int result=followersService.noOfFollowing(user_id);
		return result;
	}
	
	//Get count of Followers
	
	@GetMapping("/get-followers-count/{user_id}")
	public int noOfFollowers(@PathVariable(name="user_id") Long user_id){
		int result=followersService.noOfFollowers(user_id);
		return result;
	}
	
	//Change account status
	@PutMapping("/change-account-status")
	public ResponseEntity<UserEntity> changeStatus(@RequestBody UserEntity obj1)
	{
		UserEntity res=userService.changeStatus(obj1);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
		
	//Count Blogs
	
	@GetMapping("/get-blogs-count/{user_id}")
	public int noOfBlogs(@PathVariable(name="user_id") Long user_id) {
		int result=blogService.noOfBlogs(user_id);
		return result;
	}
	
	//Get User Details by Email Id
	
	@PostMapping("/get-by-emailId")
	public ResponseEntity<UserEntity> getUserByEmail(@RequestBody UserEntity obj1){
		UserEntity result= userService.getUserByEmail(obj1);
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
	
}
