package com.srpingdemo.day1.entity;

import org.apache.ibatis.type.Alias;

@Alias("tPermission")
public class TPermission {
	private int id;
	private String permissionname;
	private TRole role;
	
	
	public TPermission() {
		super();
	}

	public TPermission(String permissionname) {
		super();
		this.permissionname = permissionname;
	}
	
	public TPermission(int id, String permissionname, TRole role) {
		super();
		this.id = id;
		this.permissionname = permissionname;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public TRole getRole() {
		return role;
	}
	public void setRole(TRole role) {
		this.role = role;
	}
	
	
}
