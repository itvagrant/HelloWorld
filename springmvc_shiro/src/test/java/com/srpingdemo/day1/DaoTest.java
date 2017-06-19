package com.srpingdemo.day1;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.srpingdemo.day1.dao.ITUserDao;
import com.srpingdemo.day1.entity.TUser;
import com.srpingdemo.day1.service.ITUserService;

public class DaoTest {
	@Test
	public void testDao1(){
		ClassPathXmlApplicationContext cxt=new ClassPathXmlApplicationContext("root-context.xml");
		
		ITUserDao  dao = cxt.getBean(ITUserDao.class);
		System.out.println(dao);
		ITUserService us = cxt.getBean(ITUserService.class);
		
		String username = "admin";
		TUser tuser = us.findTUserByUsername(username);
		System.out.println(tuser.getPassword());
		System.out.println(tuser.getRole().getRolename());
	}
}
