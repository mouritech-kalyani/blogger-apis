package com.blog.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blog.entity.FollowersEntity;

@Repository
public interface FollowersJpa  extends JpaRepository<FollowersEntity,Long>{

	@Query("select f from FollowersEntity f where f.user1.userId = :user_user_id") 
	List getUnfollowUser(@Param("user_user_id") Long user_user_id);
	
	@Query("select count(f.user2.userId) from FollowersEntity f where f.user1.userId = :user_id")
	int getFollowingCount(@Param("user_id") Long user_id);
	
	@Query("select count(f.user2.userId) from FollowersEntity f where f.user2.userId = :user_id")
	int getFollowersCount(@Param("user_id") Long user_id);
}
