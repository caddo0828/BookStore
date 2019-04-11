package com.store.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.store.book.domain.Book;

/**
 * 订单数据辅助类，用于存储最终选购的商品
 * @author 老腰
 */
public class DataService {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();
	
	//添加订单数据
	public void add(Book book) {	
	   book.setNums(book.getNums()-book.getShopNums());
	   hm.put(book.getId(), book);
	}

	//清空订单信息
	public void clear() {
		hm.clear();
	}
		
	//显示集合中存放的所有书籍,且将所有书籍存放在ArrayList集合中
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
	
	//形成订单总价
	public  double getTotalPrice(){
		double sum = 0 ;
		ArrayList<Book> list = new DataService().findAllBook();
	    if(list!=null) {
	    	for(Book book : list) {
				sum = book.getPrice()*book.getShopNums()+sum;
			}
	    }
		return sum;
	}
		
	
}
