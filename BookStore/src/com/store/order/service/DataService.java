package com.store.order.service;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface DataService {
	 //��Ӷ������� 
	 void add(Book book);
	 
	 //��ն�����Ϣ
	 void clear();
	 
	 //��ʾ�����д�ŵ������鼮,�ҽ������鼮�����ArrayList������
	 ArrayList<Book> findAllBook();
	 
	 //�γɶ����ܼ�
	 double getTotalPrice();
}
