package com.store.order.domain;

import java.io.Serializable;

/**
 * ������Ϣ��
 * @author ����
 */
public class Order implements Serializable{
	private String id;//�������
	private String userID;
	private double totalPrice;//��Ʒ�ܼ�
	private String orderDate;//�����ɽ�ʱ��
	private String orderName; //����������
	private String telephone ;//��������ϵ�绰
	private String address;//�ջ���ַ
	
	public Order() {
	}
	public Order(String orderName, String telephone, String address) {
		super();
		this.orderName = orderName;
		this.telephone = telephone;
		this.address = address;
	}
	public Order(String id, String userID, double totalPrice, String orderDate, String orderName, String telephone,
			String address) {
		this.id = id;
		this.userID = userID;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.orderName = orderName;
		this.telephone = telephone;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
