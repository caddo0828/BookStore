package com.store.order.service;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface DataService {
	 //添加订单数据 
	 void add(Book book);
	 
	 //清空订单信息
	 void clear();
	 
	 //显示集合中存放的所有书籍,且将所有书籍存放在ArrayList集合中
	 ArrayList<Book> findAllBook();
	 
	 //形成订单总价
	 double getTotalPrice();
}
