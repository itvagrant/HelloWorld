package com.srpingdemo.day1.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.srpingdemo.day1.entity.User;
import com.srpingdemo.day1.service.IAuthorityService;
/**
 * 权限拦截器
 * @author bwfadmin
 */
@Component()
public class AuthorityInterceptor implements HandlerInterceptor{
	
	@Autowired
	private IAuthorityService authorityService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("===============preHandle================");
		
		HttpSession session = request.getSession();
		Object sessionUser = session.getAttribute("user");
		Cookie[] cookies = request.getCookies();
		String cookieUser = null;
		for (Cookie ck : cookies) {
			String key = ck.getName();
			if("user".equals(key)){
				cookieUser = ck.getValue();
			}
		}
		boolean isLogin = authorityService.validateLoginAuthority(cookieUser, sessionUser);
		System.out.println("isLogin:"+isLogin);
		//判断是否需要转发
		if(isLogin == false){
			request.getRequestDispatcher("/WEB-INF/views/test/test1.jsp").forward(request, response);
		}
		System.out.println("==============+++=============:"+isLogin);
		return isLogin;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("===============postHandle================");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("===============afterCompletion================");
	}
	
	
	
	/*
	 * 注入之setter/getter
	 */
	public IAuthorityService getAuthorityService() {
		return authorityService;
	}
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	
	
}
