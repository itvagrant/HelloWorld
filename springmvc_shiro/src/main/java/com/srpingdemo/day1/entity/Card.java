package com.srpingdemo.day1.entity;

import org.apache.ibatis.type.Alias;

@Alias("Card")
public class Card {
	private int id;
	private String cardno;
	private Person person;
	
	public Card() {
		super();
	}
	public Card(String cardno) {
		super();
		this.cardno = cardno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
