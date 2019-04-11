package com.store.book.domain;

import java.io.Serializable;

/**
 * 书籍信息类
 * @author 老腰
 */
public class Book implements Serializable{
	private String id;//书籍id
	private String name;//书名
	private double price;//单价
	private String category;//书籍类型
	private int nums;//书籍库存量
	private String author;//作者
	private int shopNums;//卖出的数量
	private String imgurl;//书籍图片地址
	private String description;//书籍描述
	public Book() {
		
	}
	public Book(String id, String name, double price, String category, int nums, String author, String imgurl,
			String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.nums = nums;
		this.author = author;
		this.imgurl = imgurl;
		this.description = description;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public int getShopNums() {
		return shopNums;
	}
	public void setShopNums(int shopNums) {
		this.shopNums = shopNums;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	} 
}
