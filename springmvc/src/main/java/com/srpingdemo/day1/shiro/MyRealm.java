package com.srpingdemo.day1.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.srpingdemo.day1.service.ITUserService;

/**
 * 配置自定义的shiro领域bean
 * @author bwfadmin
 */
public class MyRealm extends AuthorizingRealm{
	
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
		
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		
		return null;
	}
	
	/**
	 * 用来验证登录管理的
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atoken) throws AuthenticationException {
		
		
		return null;
	}

}
