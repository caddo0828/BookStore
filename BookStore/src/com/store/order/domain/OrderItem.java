package com.store.order.domain;

import java.io.Serializable;
/**
 * �����ӱ�orderitem��Ϣ��
 * @author ����
 */
public class OrderItem implements Serializable{
	private String id; //�����ӱ�id
	private String orderID;//��������id
	private String bookID;//�鼮id
	private int bookNum;//������鼮����
	private String bookName;//�����鼮����
	private double bookPrice;//�鼮����
	
	public OrderItem() {
	}
	public OrderItem(String id, String orderID, String bookID, int bookNum, String bookName, double bookPrice) {
		this.id = id;
		this.orderID = orderID;
		this.bookID = bookID;
		this.bookNum = bookNum;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

}
