package com.store.cart.service;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface CarService {
	/**
	 * 在购物车中添加书籍
	 * @param id  map集合主键，以书籍id作为主键
	 * @param book  要添加到购物车的书籍
	 * @param butNums  购买的书籍数量
	 */
	void add(String id, Book book,int buyNums);
	
	/**
	 * 根据书籍的id号，删除购物车中对应的书籍
	 * @param id   书籍id,也是map集合的键
	 */
	 void delete(String id);
	 
	 /**
	 * 修改购物车中对应键的书籍的购买数量
	 * @param id 书籍的id，也是map集合的键值
	 * @param nums 修改后的购买数量
	 */
	 void update(String id,int nums);
	 
	 /**
	 * 清空购物车
	 */
	 void clear();
	 
	 /**
	 * 查询购物车中的所有书籍
	 * @return
	 */
	 ArrayList<Book> findAllBook();
	 
	 
	 
	 
	
}
