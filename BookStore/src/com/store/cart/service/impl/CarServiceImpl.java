package com.store.cart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.store.book.domain.Book;
import com.store.cart.service.CarService;

public class CarServiceImpl implements CarService{
	private static HashMap<String, Book> hm = new HashMap<String, Book>();

	@Override
	public void add(String id, Book book, int buyNums) {
		//判断购物车中是否已经存在此书籍,存在取出书籍，进行购买数量的修改
		if (hm.containsKey(id)) {
			book = hm.get(id);
			book.setShopNums(book.getShopNums() + buyNums);
			hm.put(id, book);
		} else {
			//不存在书籍时，设置书籍初始购买数量，并添加此书籍
			book.setShopNums(buyNums);
			hm.put(id, book);
		}
	}

	@Override
	public void delete(String id) {
		hm.remove(id);
	}

	@Override
	public void update(String id, int nums) {
		Book book = hm.get(id);
		book.setShopNums(nums);
		hm.put(id, book);
	}

	@Override
	public void clear() {
		hm.clear();
	}

	@Override
	public ArrayList<Book> findAllBook() {
		ArrayList<Book> list = new ArrayList<Book>();
		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			String id = iterator.next();
			Book book = hm.get(id);
			list.add(book);
		}
		return list;
	}

}
