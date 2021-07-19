package com.blog.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entity.FollowersEntity;
import com.blog.entity.UserEntity;
@Repository
public interface UserJpa extends JpaRepository<UserEntity,Long>{

	@Query("Select a from UserEntity a where a.username=:username and a.password=:password")
	public List<UserEntity> logIn(String username,String password);

	@Query("Select a from UserEntity a where a.username=:username")
	public List<UserEntity> findByUsername(String username);
	
	@Query("Select u from UserEntity u where u.userId not in :unfollowersid") 
	List getUnfollowers(List<Long> unfollowersid);

}
