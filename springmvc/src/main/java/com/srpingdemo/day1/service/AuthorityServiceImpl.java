package com.srpingdemo.day1.service;

import org.springframework.stereotype.Service;

@Service("authorityServiceImpl")
public class AuthorityServiceImpl implements IAuthorityService {

	@Override
	public boolean validateLoginAuthority(String cookieUser,Object sessionUser) {
		//判断是否需要sessionser来验证
		boolean isLogin = false;
		
		//解析cookieUser中的用户名和密码信息，并做登录验证
		
		System.out.println("cookieUser:"+cookieUser);
		//....
//		isLogin = true;
		
		if(isLogin == false){
			if(sessionUser == null)return false;
			
			//获取sessionUser的用户名和密码，再做一次登录验证...（也可以不做）
		}   
		return isLogin;
	}

}
