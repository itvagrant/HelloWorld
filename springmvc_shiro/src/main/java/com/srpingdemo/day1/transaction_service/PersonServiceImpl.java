package com.srpingdemo.day1.transaction_service;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srpingdemo.day1.dao.transaction.IPersonDao;
import com.srpingdemo.day1.entity.Card;
import com.srpingdemo.day1.entity.Person;
import com.srpingdemo.day1.exception.MyException;

@Service
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private IPersonDao personDao;
	@Autowired
	private ICardService cardService;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,
	propagation=Propagation.REQUIRED,
	rollbackFor=com.srpingdemo.day1.exception.MyException.class,
	readOnly=false)
	public int savePerson(Person person) {
		int result = personDao.savePerson(person);
		
		
		Card card = person.getCard();
		card.setPerson(person);
		cardService.saveCard(card);
		
		try{
			FileInputStream fis = new FileInputStream("dddd");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("card错误！！");
		}finally{
		}
		
		return result;
	}

	public IPersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(IPersonDao personDao) {
		this.personDao = personDao;
	}
	
	
	
}
