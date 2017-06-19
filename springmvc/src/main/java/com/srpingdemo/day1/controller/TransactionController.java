package com.srpingdemo.day1.controller;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srpingdemo.day1.entity.Card;
import com.srpingdemo.day1.entity.Person;
import com.srpingdemo.day1.exception.MyException;
import com.srpingdemo.day1.transaction_service.ICardService;
import com.srpingdemo.day1.transaction_service.IPersonService;

/**
 * 测试Spring AOP的声明式事务的传播行为
 * @author bwfadmin
 *
 */
@Controller
@RequestMapping("/tr")
public class TransactionController {
	@Autowired
	private IPersonService ps;
	@Autowired
	private ICardService cs;
	
	@RequestMapping("/tsfm")
	public String trTest(@RequestParam("pname") String pname,
			@RequestParam("cardno") String cardno){
		Person person = new Person(pname);
		
		Card card = new Card(cardno);
		person.setCard(card);
		ps.savePerson(person);
		
		/*card.setPerson(person);
		
		cs.saveCard(card);*/
		
		/*try{
			FileInputStream fis = new FileInputStream("dddd");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("card错误！！");
		}finally{
		}*/
		
		return "home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public IPersonService getPs() {
		return ps;
	}
	public void setPs(IPersonService ps) {
		this.ps = ps;
	}
	public ICardService getCs() {
		return cs;
	}
	public void setCs(ICardService cs) {
		this.cs = cs;
	}
	
	
	
}
