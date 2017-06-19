package com.srpingdemo.day1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.ModelAndView;

import com.srpingdemo.day1.entity.User;
import com.srpingdemo.day1.service.IUserService;

/**
 * 处理user相关页面的控制器
 * @author bwfadmin
 */
@Controller
@RequestMapping(path="/user")
public class UserController {
	
	public UserController() {
		// TODO Auto-generated constructor stub
		System.out.println("UserController...."+this);
	}
	
	@Autowired
	private IUserService service;
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password){
		System.out.println("username"+username+"\t"+"password"+password);
		//service.findUser(1);
		System.out.println(">>>>>>>>>>>:\t"+this);
		//返回的视图view名称为home，让对应的视图被渲染
		return "home";
	}
	
	/**
	 * 可以直接将数据自动封装到user中
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(User user,Map<String,Object> map){
		
		boolean result = service.regist(user);
		map.put("regresult",result);
		
		return "home";
	}
	
	/**
	 * 
	 * @ResponseBody代表了该方法处理的一个ajax请求
	 * @return String代表该异步请求处理后响应的是text字符串
	 */
	@RequestMapping(path="ajaxtest1",method=RequestMethod.POST)
	@ResponseBody
	public String ajaxtest(){
		return "你好，朋友";
	}
	
	/**
	 * 异步请求返回json
	 * @return User代表异步请求的响应数据是json/xml
	 */
	@RequestMapping(value="ajaxtest2",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public User ajaxtestjson(){
		return new User("小明",35,"男");
	}
	
	/**
	 * 异步请求返回json数组/xml
	 * @return List<User>代表异步请求的响应数据是json数组/xml
	 */
	@RequestMapping(value="/ajaxtest3",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<User> ajaxtestjson2(){
		List<User> list = new ArrayList<>();
		list.add(new User("小光",15,"男"));
		list.add(new User("小黑",22,"女"));
		return list;
	}
	
	/**
	 * 在配置了jackson-dataformat-xml后，Spring的异步响应格式默认都是xml
	 * 需要设置@RequestMapping的produces={"application/json;charset=utf-8"}来显示
	 * 声明返回格式为json
	 * @return
	 */
	@RequestMapping(value="ajaxtestxml",method=RequestMethod.POST)
	@ResponseBody
	public User ajaxtestxml(){
		return new User("小明",35,"男");
	}
	
	/**
	 * 将结果保存到requestScope域中，通过ModelAndView视图模型类（不声明而传递Map集合或者Model，
	 * 会自动封装Map和Model和jsp名字到ModelAndView中）传递给view层的jsp获取并渲染
	 * 	test/test1:代表的是view目录下的test目录下的test1.jsp
	 * @return 对应的jsp
	 */
	@RequestMapping(value="/tsresult1",method=RequestMethod.POST)
	public String transformResult(Map<String,Object> map){
		map.put("user1",new User("小鹏",36,"男"));
		return "test/test1";
	}
	
	/**
	 * 传入Model，返回String
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tsresult2")
	public String transformResult2(Model model){
		model.addAttribute("user1",new User("小鹏2",36,"男"));//类似于map.put
		return "test/test1";
	}
	/**
	 * 传入ModelAndView 返回mv视图模型
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/tsresult3")
	public ModelAndView transformResult3(ModelAndView mv){
		mv.setViewName("test/test1");//设置视图的名字
		mv.addObject("user1",new User("小鹏3",36,"男"));
		return mv;
	}
	
	/**
	 * Ant风格的URL:
	 * 		?   匹配一个任意字符          /a/?b -->  可以匹配/a/ab;/a/cb。但不能匹配/a/acb之类
	 * 		*   匹配任意长度的字符    /a/ *b -->  可以匹配/a/cb;/a/acb。但不能匹配/a/cb/vb
	 * 		**  匹配多层路径               可以匹配/a/ab;/a/acb;/a/ab/abc/…/…
	 * */
	//@RequestMapping(value="/?")
	//@RequestMapping(value="/*")
	/*@RequestMapping(value="/**")
	public String testAnt(){
		
		System.out.println("----------testAnt()--------");
		
		return "home";
	}*/
}
