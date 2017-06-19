package com.srpingdemo.day1.service;

import java.util.Set;

public interface ITUserService {
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
}
