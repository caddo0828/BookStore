package com.store.product.domain;

import java.io.Serializable;

/**
 * �ٻ���Ʒ��
 * @author ����
 */
public class Product implements Serializable{
	private String id; //��Ʒid
	private String name;//��Ʒ��
	private String imgurl; //��ƷͼƬ·��
	private double price; //��Ʒ�۸�
	private int nums; //��Ʒ���
	public Product() {
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
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	
}
