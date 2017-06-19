package com.srpingdemo.day1.transaction_service;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srpingdemo.day1.dao.transaction.ICardDao;
import com.srpingdemo.day1.entity.Card;
import com.srpingdemo.day1.exception.MyException;

@Service
public class CardServiceImpl implements ICardService{

	@Autowired
	private ICardDao cardDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,
			propagation=Propagation.REQUIRED,
			rollbackFor=com.srpingdemo.day1.exception.MyException.class,
			readOnly=false)
	public int saveCard(Card card) {
		int result = cardDao.saveCard(card);
		/*try{
			FileInputStream fis = new FileInputStream("dddd");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyException("card错误！！");
		}finally{
		}*/
		
		return result;
	}

	
	public ICardDao getCardDao() {
		return cardDao;
	}
	public void setCardDao(ICardDao cardDao) {
		this.cardDao = cardDao;
	}
	
	

}
