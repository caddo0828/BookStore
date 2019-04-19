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
		//�жϹ��ﳵ���Ƿ��Ѿ����ڴ��鼮,����ȡ���鼮�����й����������޸�
		if (hm.containsKey(id)) {
			book = hm.get(id);
			book.setShopNums(book.getShopNums() + buyNums);
			hm.put(id, book);
		} else {
			//�������鼮ʱ�������鼮��ʼ��������������Ӵ��鼮
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
