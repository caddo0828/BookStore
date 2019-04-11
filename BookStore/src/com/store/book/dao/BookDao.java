package com.store.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.store.book.domain.Book;
import com.store.order.domain.Order;
import com.store.web.utils.TxQueryRunner;

/**
 * ҵ���߼��࣬���������鼮��ҵ��
 * ��Ӧ���ݿ��books
 * @author ����
 */
public class BookDao {
	private static QueryRunner qr = new TxQueryRunner();
	private static int pageSize = 12; //ÿҳ��ʾ����������
	
	/**
	 * ��ѯ�鼮����ʾҳ��
	 * @return books����ÿҳ��ʾ12�����ݣ����ж���ҳ
	 */
	public static int getPageCount() {
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
		
	/**�����ݿ��鼮���з�ҳ����
	 * @param pageNumber ��ǰ��ҳ��
	 * @return ��Ӧҳ���������鼮����
	 */
	public static ArrayList<Book> findAllBook(int pageNumber) {
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
	
	
	/**
	 * ����id���ҵ���Ӧ���鼮
	 * @param  �鼮id��
	 * @return ��Ӧ�鼮
	 */
	public static Book findById(String id)  {
		String sql = "select * from books where id=?";
		Book book = null;
		try {
			book = qr.query(sql,new BeanHandler<>(Book.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * ���ݶ�Ӧ���鼮�޸�λ�����ݿ��е��鼮�����
	 * @param book Ҫ���п�����޸ĵ��鼮
	 * @return 
	 */
	public static void updateNums(Book book) {
		String sql = "update books set nums=? where id=?";
		Object[] params = {book.getNums(),book.getId()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����鼮idɾ����Ӧ���鼮
	 * @param bid �鼮id
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public static boolean deleteBookById(String bid) {
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
	
	/**
	 * �����鼮id���޸��鼮����Ϣ
	 * @param book Ҫ�޸ĵ��鼮
	 * @return �Ƿ��޸ĳɹ���
	 */
	public static boolean updateBook(Book book) {
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
	
	/**
	 * �����ݿ��books�������һ������
	 * @param book
	 * @return  �Ƿ����ɹ�
	 */
	public static boolean insertBook(Book book) {
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
	
	/**
	 * �����鼮��id�����鼮���ƽ���ģ��ƥ�䣬��ѯ��Ӧ����
	 * @param bookId   �鼮id��
	 * @param bookName  �鼮����
	 * @return
	 */
	public static ArrayList<Book> searchByIdOrName(String bookId,String bookName) {
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
