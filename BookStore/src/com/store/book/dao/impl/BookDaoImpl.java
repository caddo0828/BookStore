package com.store.book.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.book.dao.BookDao;
import com.store.book.domain.Book;
import com.store.web.utils.TxQueryRunner;

public class BookDaoImpl implements BookDao{
	private static QueryRunner qr = new TxQueryRunner();
	private static int pageSize = 10; //ÿҳ��ʾ����������
	
	@Override
	public int getPageCount() {
		int pageCount =0;  //��ҳ��
		int totalRecounds = 0;  //���ݿ��ܼ�¼
		String sql = "SELECT COUNT(*) from books";
		Object obj;
		try {
			obj = qr.query(sql, new ScalarHandler());
			Number number = (Number)obj;
			totalRecounds = number.intValue();
			//������ж���ҳ
			pageCount = (totalRecounds-1)/pageSize+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}

	@Override
	public ArrayList<Book> findAllBook(int pageNumber) {
		ArrayList<Book> list = null;
		int pages = pageSize*(pageNumber-1);
		String sql ="select * from books order by id limit"+" "+pages+","+pageSize;
		try {
			list = (ArrayList<Book>) qr.query(sql,new BeanListHandler<>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book findById(String id) {
		String sql = "select * from books where id=?";
		Book book = null;
		try {
			book = qr.query(sql,new BeanHandler<>(Book.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public void updateNums(Book book) {
		String sql = "update books set nums=? where id=?";
		Object[] params = {book.getNums(),book.getId()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteBookById(String bid) {
		//DELETE FROM ����  WHERE ����
		String sql = "delete from books where id=?";
		int num = 0;
		try {
			num = qr.update(sql, bid);
			if(num>=1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		String sql = "update books set name=?, price=?,category=?,nums=?,imgurl=?,description=?,author=? where id=?";
		Object[] params = {book.getName(),book.getPrice()
					,book.getCategory(),book.getNums(),book.getImgurl()
					,book.getDescription(),book.getAuthor()
					,book.getId()
				};
		int num = 0;
		try {
			 num = qr.update(sql, params);
			 if(num>=1) {
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertBook(Book book) {
		String sql = "insert into books values(?,?,?,?,?,?,?,?)";
		Object[] params = {book.getId(),book.getName(),book.getPrice()
					,book.getCategory(),book.getNums(),book.getImgurl()
					,book.getDescription(),book.getAuthor()
				};
		int num = 0;
		try {
			 num = qr.update(sql, params);
			 if(num>=1) {
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Book> searchByIdOrName(String bookId, String bookName) {
		//�ж������ַ�ʽ��ѯƥ����Ϣ
		String sql = "";
		ArrayList<Book> list = null;
		if(bookId!= null && bookName=="") {
			//�����鼮id����ƥ��
			sql ="select * from books where id = ?";
			try {
				list = (ArrayList<Book>) qr.query(sql, new BeanListHandler<>(Book.class), bookId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(bookId == "" && bookName != null) {
			//�����鼮���ƽ���ģ��ƥ��    "%" + bookName + "%"
			sql = "select * from books where name like ? ";
			try {
				list = (ArrayList<Book>) qr.query(sql, new BeanListHandler<>(Book.class),"%" + bookName + "%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(bookId !=null && bookName!=null) {
			//�����鼮id������ͬʱ����ģ��ƥ��
			sql = "select * from books where name like ? and id =? ";
			try {
				Object[] params = {"%" + bookName + "%",bookId};
				list = (ArrayList<Book>) qr.query(sql, new BeanListHandler<>(Book.class),params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
