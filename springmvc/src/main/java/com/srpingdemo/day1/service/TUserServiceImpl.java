package com.srpingdemo.day1.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srpingdemo.day1.dao.ITUserDao;

@Service("tuserServiceImpl")
public class TUserServiceImpl implements ITUserService {
	
	@Autowired
	private ITUserDao userDao;
	
	@Override
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

}
