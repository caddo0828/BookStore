package com.store.book.dao;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface BookDao {
	/**
	 * ��ѯ�鼮����ʾҳ��
	 * @return books����ÿҳ��ʾ12�����ݣ����ж���ҳ
	 */
	int getPageCount();
	
	/**�����ݿ��鼮���з�ҳ����
	 * @param pageNumber ��ǰ��ҳ��
	 * @return ��Ӧҳ���������鼮����
	 */
	ArrayList<Book> findAllBook(int pageNumber);
	
	/**
	 * ����id���ҵ���Ӧ���鼮
	 * @param  �鼮id��
	 * @return ��Ӧ�鼮
	 */
	Book findById(String id);
	
	/**
	 * ���ݶ�Ӧ���鼮�޸�λ�����ݿ��е��鼮�����
	 * @param book Ҫ���п�����޸ĵ��鼮
	 * @return 
	 */
	void updateNums(Book book);
	
	/**
	 * �����鼮idɾ����Ӧ���鼮
	 * @param bid �鼮id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	boolean  deleteBookById(String bid);
	
	/**
	 * �����鼮id���޸��鼮����Ϣ
	 * @param book Ҫ�޸ĵ��鼮
	 * @return �Ƿ��޸ĳɹ���
	 */
	boolean updateBook(Book book);
	
	/**
	 * �����ݿ��books�������һ������
	 * @param book
	 * @return  �Ƿ����ɹ�
	 */
	boolean insertBook(Book book);
	

	/**
	 * �����鼮��id�����鼮���ƽ���ģ��ƥ�䣬��ѯ��Ӧ����
	 * @param bookId   �鼮id��
	 * @param bookName  �鼮����
	 * @return
	 */
	ArrayList<Book> searchByIdOrName(String bookId,String bookName) ;
	

}
