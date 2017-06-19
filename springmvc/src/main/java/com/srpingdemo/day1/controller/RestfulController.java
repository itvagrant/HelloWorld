package com.srpingdemo.day1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srpingdemo.day1.entity.User;
import com.srpingdemo.day1.service.IUserService;

@Controller
@RequestMapping("/rest")
public class RestfulController {
	
	@Autowired
	private IUserService service;
	
	/**
	 * 在Restful API设计中一般通过POST请求指令来代表新增信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String saveUser(User user){
		System.out.println("post:"+user);
		service.regist(user);
		return "home";
	}
	
	/**
	 * 在Restful API设计中一般通过GET请求指令来代表查询信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public String queryUserById(@PathVariable("id") int id,Model model){
		System.out.println("get:"+id);
		User result = service.findUser(id);
		System.out.println("result:"+result);
		if(result == null)return "home";
		model.addAttribute("user",result);
		return "home";
	}
	
	/**
	 * 在Restful API设计中一般通过PUT请求指令来代表修改信息
	 * 因为表单只支持GET/POST请求，所以为了使这里能够获取到PUT请求，需要配置
	 * 一个Spring提供的请求类型处理的过滤器HiddenHttpMethodFilter
	 * 在前端表单页面提供一个hidden标签来提供一个名为"_method"的参数，值代表是put/delete，
	 * 传过来后交个上述过滤器来处理请求的类型变换
	 * 同时：如果是转发操作到home.jsp视图，因为.jsp只能处理GET/POST的请求，不能
	 * 处理PUT/DELET这种请求，
	 * 所以这里需要用显示的重定向声明"redirect:home"，来发起新的GET(POST)请求到.jsp视图，让其
	 * 能够变相的接受PUT/DELETE
	 * 
	 * ---注意：异步请求就没必要重定向了，因为没有请求的转发，而是直接返回响应结果。。
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	public String updateUser(User user){
		System.out.println("put:"+user);
		
		boolean result=service.moidfyUser(user);
		//暂且用delete的中转redirect
		return "redirect:/rest/user/redirect/"+result;
	}
	
	/**
	 * 在Restful API设计中一般通过DELET请求指令来代表删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public String removeUserById(@PathVariable("id") int id){
		System.out.println("delete:"+id);
		boolean result=service.removeUser(id);
		return "redirect:/rest/user/redirect/"+result;
	}
	/**
	 * 用来给DELETE做中转重定向的
	 * @return
	 */
	@RequestMapping(value="/user/redirect/{result}",method=RequestMethod.GET)
	public String redirectMethod(@PathVariable("result") String result,Model model){
		model.addAttribute("result",result);
		return "test/user";
	}
	
	/**
	 * 异步PUT请求
	 * @return 异步响应字符串
	 */
	@RequestMapping(value="/user/ajaxrest",method=RequestMethod.PUT)
	@ResponseBody
	public String ajaxRestful(){
		System.out.println("异步PUT请求");
		return "哈哈哈，我是异步小PUT";
	}
	
	
}
