package com.srpingdemo.day1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srpingdemo.day1.dao.IUserDao;
import com.srpingdemo.day1.entity.User;

@Service
public class UserService implements IUserService {
	
	/**
	 * 被service依赖的dao
	 */
	@Autowired
	private IUserDao userDao;
	
	@Override
	public boolean regist(User user) {
		/*
		 * 做个简单的业务处理
		 */
		if(user == null)return false;
		
		int result = userDao.saveUser(user);
		if(result>0)return true;
		
		return false;
	}
	
	@Override
	public User findUser(int id) {
		if(id<1)return null;
		return userDao.queryUserById(id);
	}
	
	@Override
	public boolean removeUser(int id) {
		if(id<1)return false;
		int result = userDao.deleteUserById(id);
		if(result>0)return true;
		return false;
	}
	
	@Override
	public boolean moidfyUser(User user) {
		if(user == null)return false;
		
		int result = userDao.updateUser(user);
		if(result>0)return true;
		
		return false;
	}
	
	
	public IUserDao getUserDao() {
		return userDao;
	}
	/**
	 * 为被依赖的dao组件提供setter/getter，因为Spring就是通过setter来注入的
	 * @param userDao
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
