package com.store.manager.domain;

import java.io.Serializable;
/**
 * 管理员信息类，包含管理员id，账号，登录密码
 * @author 老腰
 */
public class Manager implements Serializable{
	private String id;
	private String name;
	private String pwd;
	public Manager() {
		
	}
	public Manager(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
