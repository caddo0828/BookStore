package com.store.order.domain;

import java.io.Serializable;

/**
 * 订单信息类
 * @author 老腰
 */
public class Order implements Serializable{
	private String id;//订单编号
	private String userID;
	private double totalPrice;//商品总价
	private String orderDate;//订单成交时间
	private String orderName; //订购者姓名
	private String telephone ;//订购者联系电话
	private String address;//收货地址
	
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
