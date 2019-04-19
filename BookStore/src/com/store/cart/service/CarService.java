package com.store.cart.service;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface CarService {
	/**
	 * �ڹ��ﳵ������鼮
	 * @param id  map�������������鼮id��Ϊ����
	 * @param book  Ҫ��ӵ����ﳵ���鼮
	 * @param butNums  ������鼮����
	 */
	void add(String id, Book book,int buyNums);
	
	/**
	 * �����鼮��id�ţ�ɾ�����ﳵ�ж�Ӧ���鼮
	 * @param id   �鼮id,Ҳ��map���ϵļ�
	 */
	 void delete(String id);
	 
	 /**
	 * �޸Ĺ��ﳵ�ж�Ӧ�����鼮�Ĺ�������
	 * @param id �鼮��id��Ҳ��map���ϵļ�ֵ
	 * @param nums �޸ĺ�Ĺ�������
	 */
	 void update(String id,int nums);
	 
	 /**
	 * ��չ��ﳵ
	 */
	 void clear();
	 
	 /**
	 * ��ѯ���ﳵ�е������鼮
	 * @return
	 */
	 ArrayList<Book> findAllBook();
	 
	 
	 
	 
	
}
