package com.blog.jpa;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blog.entity.CommentsEntity;

@Repository
public interface CommentsJpa extends JpaRepository<CommentsEntity,Long>{

	@Query("Select c from CommentsEntity c where c.blogs.blogId =:blog_id")
	List<CommentsEntity> findByblog_id(@Param("blog_id") Long blog_id);
}
