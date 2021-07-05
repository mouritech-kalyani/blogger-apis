package com.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.UserEntity;
import com.blog.jpa.UserJpa;
import com.blog.service.UserService;

@Service
public class UserImpl implements UserService{

	@Autowired UserJpa userJpa;

	@Override
	public List<UserEntity> getUsers() {
		List<UserEntity> result=userJpa.findAll();
		return result;
	}


	@Override
	public List<UserEntity> addUser(UserEntity obj1) {
		List<UserEntity> emptyRes = null;
		List<UserEntity> res= userJpa.findByUsername(obj1.getUsername());
			if(res.isEmpty())
			{
				userJpa.save(obj1);
				return userJpa.findAll();
			}
			return emptyRes;
		
	}

	@Override
	public List<UserEntity> logIn(String username,String password) {
		List<UserEntity> result=userJpa.logIn(username,password);
		return result;
	}

	@Override
	public UserEntity updateProfile(UserEntity obj1) {
		UserEntity result=userJpa.save(obj1);
		return result;
	}


	@Override
	public UserEntity getUserById(Long user_id) {
		Optional<UserEntity> result =userJpa.findById(user_id);
	    UserEntity userdetails=result.get();
		return userdetails;
	}


}
