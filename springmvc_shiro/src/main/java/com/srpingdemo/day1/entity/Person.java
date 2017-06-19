package com.srpingdemo.day1.entity;

import org.apache.ibatis.type.Alias;

@Alias("Person")
public class Person {
	private int id;
	private String name;
	private Card card;
	
	public Person() {
		super();
	}
	public Person(String name) {
		super();
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
}
