package com.srpingdemo.day1.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srpingdemo.day1.entity.TUser;
import com.srpingdemo.day1.service.ITUserService;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	@Resource
	private ITUserService tuserService;
	
	@RequestMapping("/login")
	public String shiroLogin(TUser user , Model model){
		/*
		 * 当前的提交的用户状态信息
		 */
		Subject currentUser  = SecurityUtils.getSubject();
		
		//创建验证令牌
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
				user.getPassword());
		try{
			//没找到，则会抛出一个IllegalStateException的运行时异常
			currentUser.login(token);
			return "home";
		}catch(Exception e){
			model.addAttribute("error","用户名或密码错误！");
			return "../../login";
		}
	}
	@RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/student")
    public String student(){
        return "admin" ;
    }

    @RequestMapping("/teacher")
    public String teacher(){
        return "admin" ;
    }
	public ITUserService getUserService() {
		return tuserService;
	}
	public void setUserService(ITUserService userService) {
		this.tuserService = userService;
	}
    
    
}
