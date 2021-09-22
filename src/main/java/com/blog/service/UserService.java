package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.UserEntity;

@Service
public interface UserService {

	public List<UserEntity> getUsers();
	
	public UserEntity addUser(UserEntity obj1);
	
	public List<UserEntity> logIn(String username,String password);
	
	public UserEntity updateProfile(UserEntity obj1);

	public UserEntity getUserById(Long user_id);
	
	public UserEntity changeStatus(UserEntity obj1);


}
