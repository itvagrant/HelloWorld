package com.srpingdemo.day1.service;

import com.srpingdemo.day1.entity.User;
/**
 * 接口层
 * @author bwfadmin
 */
public interface IUserService {
	/**
	 * 注册
	 * @param user
	 * @return 返回注册结果
	 */
	boolean regist(User user);
	
	/**
	 * 查询user通过id
	 * @param id
	 * @return user结果
	 */
	User findUser(int id);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean removeUser(int id);
	
	/**
	 * 修改user信息
	 * @param user
	 * @return
	 */
	boolean moidfyUser(User user);
	
}


