package com.blog.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blog.entity.BlogEntity;

@Repository
public interface BlogJpa extends JpaRepository<BlogEntity,Long>{

	@Query("Select b from BlogEntity b where b.user.userId = :user_id")
	List<BlogEntity> findByuser_id(@Param("user_id") Long user_id);
	
	@Query("Select b from UserEntity a inner join BlogEntity b on a.userId = b.user")
	public List<BlogEntity> getBlogs();
	
	@Query("Select b from BlogEntity b where b.user.userId in :getAllBlogs") 
	List<BlogEntity> getAllBlogs(List<Long> getAllBlogs);

}
