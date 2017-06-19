package com.srpingdemo.day1.dao;

import com.srpingdemo.day1.entity.User;

public interface IUserDao {
	int saveUser(User user);
	
	User queryUserById(int id);
	
	int deleteUserById(int id);
	
	int updateUser(User user);
}
