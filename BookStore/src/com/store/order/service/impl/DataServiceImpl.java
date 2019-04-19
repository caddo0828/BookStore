package com.store.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.store.book.domain.Book;
import com.store.order.service.DataService;

public class DataServiceImpl implements DataService{
	private static HashMap<String, Book> hm = new HashMap<String, Book>();
	
	@Override
	public void add(Book book) {
		 book.setNums(book.getNums()-book.getShopNums());
		 hm.put(book.getId(), book);
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

	@Override
	public double getTotalPrice() {
		double sum = 0 ;
		ArrayList<Book> list = findAllBook();
	    if(list!=null) {
	    	for(Book book : list) {
				sum = book.getPrice()*book.getShopNums()+sum;
			}
	    }
		return sum;
	}

}
