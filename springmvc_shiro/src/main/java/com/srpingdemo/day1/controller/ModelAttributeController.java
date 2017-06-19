package com.srpingdemo.day1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.srpingdemo.day1.entity.User;
import com.srpingdemo.day1.service.IUserService;

/**
 * 测试 @ModelAttributes 注解的应用场景
 * 注意，一般都是单独使用@ModelAttributes和少量方法，而不是和很多方法在一起混合
 * @author bwfadmin
 *
 */
@Controller
@RequestMapping("/model-attr")
public class ModelAttributeController {
	
	@Autowired
	private IUserService service;
	
	public ModelAttributeController(){
		System.out.println("++++++++++++++++++++Controller被执行了++++++++++++++++++++"+this);
	}
	
	@ModelAttribute
	public void preCheckUser(@RequestParam("id") Integer id,Model model){
		User preCheckResult = service.findUser(id);
		System.out.println(preCheckResult);
		/*
		 * model.addAttribute("user", preCheckResult);
		 * 默认的user名字，是符合SpringMVC默认的查找User类对应的 别名的
		 * 而user22则不符合，此时，查不到，需要手动在下方设置参数注解@ModelAttribute("user22")
		 */
		if(preCheckResult !=null){
			//preCheckResult为null还传递的话，为抛异常：Attribute value must not be null。
			model.addAttribute("user22", preCheckResult);
		}
	}
	
	/**
	 * 测试在preCheckUser方法中保存在model中的preCheckResult的user结果会不会
	 * 对客户端传入的参数的关联关系
	 * 
	 * 如果这里使用@ModelAttribute修饰方法参数，表示SpringMVC将到模型中查找名称为good的User对象，
	 * 如果没有这个对象，SpringMVC就要新建User，并且把提交的请求参数设置给User,
	 * 反之，如果模型中有good，那么就会使用模型中存放的User对象，并设置对应的属性为传递的参数的值（调用setter，当然
	 * 没传的属性肯定就不会调用setter了）
	 * 如果请求参数没有设置@ModelAttribute，那么SpringMVC默认采用User类的首字母小写user到模型中查找对象，
	 * 如果模型中有user,那么就能找到对象然后把提交参数设置给user对象。
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addUserNow(@ModelAttribute("user22") User user){
		
		System.out.println(user);
		
		return "home";
	}
	
	
	
	
	
	
	
	
	/*
	 * 为依赖提供setter/getter
	 */
	public IUserService getService() {
		return service;
	}
	public void setService(IUserService service) {
		this.service = service;
	}
	
	
}
