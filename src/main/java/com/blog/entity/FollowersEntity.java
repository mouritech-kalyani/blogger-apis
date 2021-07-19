package com.blog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "followers")
public class FollowersEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;
	@ManyToOne
	private UserEntity user1;

	public UserEntity getUser1() {
		return user1;
	}

	public void setUser1(UserEntity user1) {
		this.user1 = user1;
	}

	public UserEntity getUser2() {
		return user2;
	}

	public void setUser2(UserEntity user2) {
		this.user2 = user2;
	}

	@ManyToOne
	private UserEntity user2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
