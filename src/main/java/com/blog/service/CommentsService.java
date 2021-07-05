package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.blog.entity.CommentsEntity;

@Service
public interface CommentsService {

	public List<CommentsEntity> addComments(CommentsEntity comm);
	
	public List<CommentsEntity> getAllCommentData(Long blog_id);
}
