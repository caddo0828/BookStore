package com.store.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.store.book.domain.Book;

/**
 * ���ﳵ�࣬�Լ��빺�ﳵ�����ݽ��д��������鼮��ӣ�ɾ�����޸ģ���չ��ﳵ���Լ���ʾ���ﳵ�е���������
 * @author ����
 */
public class MyCar {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();

	/**
	 * �ڹ��ﳵ������鼮
	 * @param id  map�������������鼮id��Ϊ����
	 * @param book  Ҫ��ӵ����ﳵ���鼮
	 * @param butNums  ������鼮����
	 */
	public void add(String id, Book book,int buyNums) {
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

	/**
	 * �����鼮��id�ţ�ɾ�����ﳵ�ж�Ӧ���鼮
	 * @param id   �鼮id,Ҳ��map���ϵļ�
	 */
	public void delete(String id) {
		hm.remove(id);
	}

	/**
	 * �޸Ĺ��ﳵ�ж�Ӧ�����鼮�Ĺ�������
	 * @param id �鼮��id��Ҳ��map���ϵļ�ֵ
	 * @param nums �޸ĺ�Ĺ�������
	 */
	public void update(String id,int nums) {
		Book book = hm.get(id);
		book.setShopNums(nums);
		hm.put(id, book);
		
	}
	
	/**
	 * ��չ��ﳵ
	 */
	public void clear() {
		hm.clear();
	}
	
	/**
	 * ��ѯ���ﳵ�е������鼮
	 * @return
	 */
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
