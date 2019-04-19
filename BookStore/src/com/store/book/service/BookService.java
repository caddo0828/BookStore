package com.store.book.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.domain.Book;

public interface BookService {
	int getPageCount();
	
	ArrayList<Book> findAllBook(int pageNumber);
	
	Book findById(String id);
	
	void updateNums(Book book);
	
	boolean  deleteBookById(String bid);
	
	boolean updateBook(Book book);
	
	boolean insertBook(Book book);
	
	ArrayList<Book> searchByIdOrName(String bookId,String bookName) ;
	
	
	
	Book fileUploadBook(HttpServletRequest  request,HttpServletResponse response);
}
