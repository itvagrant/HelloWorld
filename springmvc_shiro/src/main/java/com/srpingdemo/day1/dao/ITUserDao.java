package com.srpingdemo.day1.dao;

import java.util.Set;

import com.srpingdemo.day1.entity.TUser;

/**
 * 验证shiro权限管理的dao层
 * @author bwfadmin
 */
public interface ITUserDao {
	/**
	 * 查找用户名对应的role
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);
	/**
	 * 查找用户名对应的permission
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);
	
	/**
	 * 查找用户名对应的TUser
	 * @param username
	 * @return
	 */
	TUser findTUserByUsername(String username);
}
