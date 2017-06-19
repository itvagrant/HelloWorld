package com.srpingdemo.day1.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.srpingdemo.day1.entity.TUser;
import com.srpingdemo.day1.service.ITUserService;

/**
 * 配置自定义的shiro领域bean
 * @author bwfadmin
 */
public class MyRealm extends AuthorizingRealm{
	private static Logger logger = LoggerFactory.getLogger(MyRealm.class);
	
	@Autowired
	private ITUserService tuserService;
	/**
	 * 用于权限的验证
	 * 权限包括：角色、操作权限等等
	 */
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String username = pc.getPrimaryPrincipal().toString();
		/*
		 * 用来保存角色和权限的简单的POJO类
		 */
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Set<String> roles = tuserService.findRoles(username);
		
		Set<String> permissions = tuserService.findPermissions(username);
		
		logger.info("roles:"+roles+"\t"+"permissions:"+permissions);
		
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		
		//返回保存权限信息和角色信息的POJO类
		return info;
	}
	
	/**
	 * 用来验证登录管理的
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
		/*
		 * atoken.getCredentials()获取的是令牌中的密码
		 * atoken.getPrincipal()获取的是令牌中的用户名
		 */
		String username = atoken.getPrincipal().toString();
		
		TUser user = tuserService.findTUserByUsername(username);
		System.out.println(user);
		
		if(user != null){
			//将user信息封装到pojo中
			AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), 
					user.getPassword(), "abc");
//			System.out.println(user.getUsername()+"的角色名:"+user.getRole());
			return info;
		}
		
		return null;
	}

	public ITUserService getTuserService() {
		return tuserService;
	}

	public void setTuserService(ITUserService tuserService) {
		this.tuserService = tuserService;
	}
	
	
}
