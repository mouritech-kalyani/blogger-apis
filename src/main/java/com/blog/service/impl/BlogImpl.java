package com.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.BlogEntity;
import com.blog.entity.FollowersEntity;
import com.blog.jpa.BlogJpa;
import com.blog.jpa.FollowersJpa;
import com.blog.service.BlogService;

@Service
public class BlogImpl implements BlogService {
	@Autowired BlogJpa blogJpa;
	@Autowired FollowersJpa followersJpa;

	@Override
	public List<BlogEntity> getBlogs() {
		List<BlogEntity> res=blogJpa.getBlogs();
		return res;
	}

	@Override
	public List<BlogEntity> getAllInfoOfUser(Long user_id) {
		List<BlogEntity> res=blogJpa.findByuser_id(user_id);
		if(res.isEmpty()) {
			return null;
		}
		return res;
	}
	
	@Override
	public List<BlogEntity> addBlog(BlogEntity obj1) {
	blogJpa.save(obj1);
	return blogJpa.findAll();
	}

	@Override
	public List<BlogEntity> editBlog(BlogEntity obj1) {
		blogJpa.save(obj1);
		return blogJpa.findAll();
	}

	@Override
	public Boolean deleteBlogByUserId(Long blogId) {
		if(blogJpa.findById(blogId) != null) {
			blogJpa.deleteById(blogId);
			return true;
		}
		return false;
		
	}

	@Override
	public List<BlogEntity> getAllBlogsForLoggedInUser(Long user_user_id) {
		List<FollowersEntity> unfollowusers = followersJpa.getUnfollowUser(user_user_id);
		List<Long> unfollowersid= new ArrayList<Long>();
		unfollowusers.forEach((a)->{
			unfollowersid.add(a.getUser2().getUserId());
		});
		List <BlogEntity> allBlogs=blogJpa.getAllBlogs(unfollowersid);
		return allBlogs;
	}


}
