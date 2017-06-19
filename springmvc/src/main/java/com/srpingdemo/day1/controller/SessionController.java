package com.srpingdemo.day1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
/**
 * 测试session域的保存信息
 * @author bwfadmin
 */
@Controller
@RequestMapping("/session")
@SessionAttributes(value={"name","age"},types={Integer.class,String.class})
public class SessionController {
	/**
	 *  @SessionAttributes(value={"phone"},types={Integer.class})
	 * value={"phone"}表示把模型中的名称为phone的数据在session域中也存上一份。
	 * types={Integer.class}表示模型中类型是Integer的数据在session域中也保存一份。
	 * 一般推荐使用value。
	 * @param name
	 * @param age
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/gs",method=RequestMethod.GET)
	public String getSession(@RequestParam(value="name",defaultValue="") String name,
			@RequestParam(value="age",defaultValue="0") Integer age,ModelMap model){
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		
		return "home";
	}
}
