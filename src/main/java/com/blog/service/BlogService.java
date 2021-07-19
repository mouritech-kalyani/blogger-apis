package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.BlogEntity;

@Service
public interface BlogService {

   public List<BlogEntity> getBlogs();

	public List<BlogEntity> getAllInfoOfUser(Long user_id);

	public List<BlogEntity> addBlog(BlogEntity obj1);

	public List<BlogEntity> editBlog(BlogEntity obj1);

	public Boolean deleteBlogByUserId(Long blogId);

	public List<BlogEntity> getAllBlogsForLoggedInUser(Long user_user_id);
}
