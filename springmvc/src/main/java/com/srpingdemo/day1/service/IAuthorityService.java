package com.srpingdemo.day1.service;


/**
 * 验证权限的服务
 * @author bwfadmin
 *
 */
public interface IAuthorityService {
	/**
	 * 验证登录的权限
	 * @param cookieUser 获取的cookie中的user代表的值
	 * @param sessionUser session中的user代表的值
	 * @return
	 */
	boolean validateLoginAuthority(String cookieUser,Object sessionUser);
}
