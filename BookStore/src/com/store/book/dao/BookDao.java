package com.store.book.dao;

import java.util.ArrayList;

import com.store.book.domain.Book;

public interface BookDao {
	/**
	 * 查询书籍总显示页数
	 * @return books表按照每页显示12条数据，共有多少页
	 */
	int getPageCount();
	
	/**将数据库书籍进行分页处理
	 * @param pageNumber 当前的页数
	 * @return 对应页数的所有书籍集合
	 */
	ArrayList<Book> findAllBook(int pageNumber);
	
	/**
	 * 根据id号找到对应的书籍
	 * @param  书籍id号
	 * @return 对应书籍
	 */
	Book findById(String id);
	
	/**
	 * 根据对应的书籍修改位于数据库中的书籍库存量
	 * @param book 要进行库存量修改的书籍
	 * @return 
	 */
	void updateNums(Book book);
	
	/**
	 * 根据书籍id删除对应的书籍
	 * @param bid 书籍id
	 * @return 是否删除成功
	 */
	boolean  deleteBookById(String bid);
	
	/**
	 * 根据书籍id号修改书籍的信息
	 * @param book 要修改的书籍
	 * @return 是否修改成功过
	 */
	boolean updateBook(Book book);
	
	/**
	 * 在数据库表books表中添加一条数据
	 * @param book
	 * @return  是否插入成功
	 */
	boolean insertBook(Book book);
	

	/**
	 * 根据书籍的id或者书籍名称进行模糊匹配，查询对应的书
	 * @param bookId   书籍id号
	 * @param bookName  书籍名称
	 * @return
	 */
	ArrayList<Book> searchByIdOrName(String bookId,String bookName) ;
	

}
