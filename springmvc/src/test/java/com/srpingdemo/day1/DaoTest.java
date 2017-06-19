package com.srpingdemo.day1;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srpingdemo.day1.dao.IUserDao;
import com.srpingdemo.day1.entity.User;

public class DaoTest{
//	@Resource
//	private IUserDao userDao;
	
	@Test
	public void testDao1(){
		System.out.println("--------------------");
//		User user = userDao.queryUserById(3);
//		System.out.println(user);
		
		ClassPathXmlApplicationContext cxt=new ClassPathXmlApplicationContext("root-context.xml");
		
		IUserDao userDao=cxt.getBean(IUserDao.class);
		
		System.out.println(userDao.getClass());
		
		User user=userDao.queryUserById(1);
		
		System.out.println(user);
	}
}
