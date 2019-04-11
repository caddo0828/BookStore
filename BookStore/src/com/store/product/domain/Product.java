package com.store.product.domain;

import java.io.Serializable;

/**
 * 百货商品类
 * @author 老腰
 */
public class Product implements Serializable{
	private String id; //商品id
	private String name;//商品名
	private String imgurl; //商品图片路径
	private double price; //商品价格
	private int nums; //商品库存
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
