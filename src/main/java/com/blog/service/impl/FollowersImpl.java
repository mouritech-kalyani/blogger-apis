package com.blog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.FollowersEntity;
import com.blog.jpa.FollowersJpa;
import com.blog.jpa.UserJpa;
import com.blog.service.FollowersService;


@Service
public class FollowersImpl implements FollowersService{
	@Autowired FollowersJpa followersJpa;
	@Autowired UserJpa usersJpa;

	@Override
	public String followUser(FollowersEntity obj1) {
		if(obj1 != null) {
			followersJpa.save(obj1);
			return "Done";
		}else {
			return "Error";
		}
		
	}

	@Override
	public List unfollowUser(Long user_user_id) {
		List<FollowersEntity> unfollowusers = followersJpa.getUnfollowUser(user_user_id);
		System.out.println(unfollowusers);
		List<Long> unfollowersid= new ArrayList<Long>();
		unfollowusers.forEach((a)->{
			unfollowersid.add(a.getUser2().getUserId());
			System.out.println("id is"+a.getUser2().getUserId());
		});
		unfollowersid.add(user_user_id);
		System.out.println(unfollowersid);
		List unfollowersuser=usersJpa.getUnfollowers(unfollowersid);
		System.out.println(unfollowersuser);
		return unfollowersuser;
	}

}
