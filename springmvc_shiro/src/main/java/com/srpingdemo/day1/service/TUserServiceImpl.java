package com.srpingdemo.day1.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srpingdemo.day1.dao.ITUserDao;
import com.srpingdemo.day1.entity.TUser;

/**
 * 验证shiro的service类
 * @author 祷
 *
 */
@Service
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

	@Override
	public TUser findTUserByUsername(String username) {
		System.out.println(userDao);
		return userDao.findTUserByUsername(username);
	}

	public ITUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ITUserDao userDao) {
		this.userDao = userDao;
	}

	
}
