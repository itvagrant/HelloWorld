package com.srpingdemo.day1.entity;

import org.apache.ibatis.type.Alias;

@Alias("tUser")
public class TUser {
	private int id;
	private String username;
	private String password;
	private TRole role;
	
	public TUser() {
		super();
	}
	public TUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public TUser(int id, String username, String password, TRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public TRole getRole() {
		return role;
	}
	public void setRole(TRole role) {
		this.role = role;
	}
	
}
