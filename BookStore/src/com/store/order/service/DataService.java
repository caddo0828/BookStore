package com.store.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.store.book.domain.Book;

/**
 * �������ݸ����࣬���ڴ洢����ѡ������Ʒ
 * @author ����
 */
public class DataService {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();
	
	//��Ӷ�������
	public void add(Book book) {	
	   book.setNums(book.getNums()-book.getShopNums());
	   hm.put(book.getId(), book);
	}

	//��ն�����Ϣ
	public void clear() {
		hm.clear();
	}
		
	//��ʾ�����д�ŵ������鼮,�ҽ������鼮�����ArrayList������
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
	
	//�γɶ����ܼ�
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
