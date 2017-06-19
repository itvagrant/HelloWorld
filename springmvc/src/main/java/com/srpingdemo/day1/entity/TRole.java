package com.srpingdemo.day1.entity;

import org.apache.ibatis.type.Alias;

@Alias("tRole")
public class TRole {
	private int id;
	private String rolename;
	
	public TRole() {
		super();
	}
	
	public TRole(String rolename) {
		super();
		this.rolename = rolename;
	}

	public TRole(int id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
}
