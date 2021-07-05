package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.CommentsEntity;
import com.blog.jpa.CommentsJpa;
import com.blog.service.CommentsService;

@Service
public class CommentsImpl implements CommentsService {
	@Autowired CommentsJpa commentsJpa;

	@Override
	public List<CommentsEntity> addComments(CommentsEntity comm) {
		
		commentsJpa.save(comm);
		return commentsJpa.findAll();
				
	}

	@Override
	public List<CommentsEntity> getAllCommentData(Long blog_id) {
		List<CommentsEntity> res=commentsJpa.findByblog_id(blog_id);
		if(res.isEmpty()) {
			return null;
		}
		return res;
	}
	
	

}
