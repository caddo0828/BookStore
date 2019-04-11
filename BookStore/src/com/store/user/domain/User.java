package com.store.user.domain;

import java.io.Serializable;

/**
 * �û���Ϣ��
 * @author ����
 */
public class User implements Serializable{
	private String id; //�û�id
	private String name;//�û���
	private String pwd;//�û�����
	private String email;//�����
	private String tel;//��ϵ�绰
	private int status;//�û�����״̬
	private String activationCode;//�û����伤����
	
	public User() {
	}
	public User(String id,String name, String pwd, String email, String tel) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.tel = tel;
	}
	public User(String id, String name, String pwd, String email, String tel, int status,
			String activationCode) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.tel = tel;
		this.status = status;
		this.activationCode = activationCode;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	
	
	
	
}
