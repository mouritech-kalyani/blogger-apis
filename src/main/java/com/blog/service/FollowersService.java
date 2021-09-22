package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.FollowersEntity;

@Service
public interface FollowersService {

		public String followUser(FollowersEntity obj1);
        public List unfollowUser(Long user_user_id);
        public int noOfFollowing(Long user_id);
        public int noOfFollowers(Long user_id);
}
