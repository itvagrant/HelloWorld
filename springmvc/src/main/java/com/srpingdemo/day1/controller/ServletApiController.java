package com.srpingdemo.day1.controller;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.srpingdemo.day1.entity.User;

@Controller
@RequestMapping("/servlet")
public class ServletApiController {
	
	/**
	 * 测试HttpServletRequest的api注入
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/request",method=RequestMethod.GET)
	public String requestApi(HttpServletRequest request){
		String parm1 = request.getParameter("name");
		String parm2 = request.getParameter("age");
		System.out.println("name:"+parm1+"\t age:"+parm2);
		User user = new User("小二",26,"男");
		request.setAttribute("user",user);
		return "home";
	}
	
	/**
	 * 测试HttpServletResponse的api注入
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/response",method=RequestMethod.GET)
	public String responseApi(HttpServletResponse response){
		Cookie cookie = new Cookie("name", "xiaowang");
		response.addCookie(cookie);
		return "home";
	}
	
	/**
	 * 测试@CookieValue注解获取cookie信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cookie",method=RequestMethod.GET)
	public String cookie(@CookieValue("JSESSIONID") String value){
		System.out.println("JSEESIONID:"+value);
		return "home";
	}
	
	/**
	 * 测试PrintWriter的api注入
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/out",method=RequestMethod.GET)
	public void printWriter(PrintWriter out){
		out.write("哈哈！！");
	}
	
	
}
