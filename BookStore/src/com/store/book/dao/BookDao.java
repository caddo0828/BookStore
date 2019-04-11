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
 * 业务逻辑类，用来处理书籍的业务
 * 对应数据库表books
 * @author 老腰
 */
public class BookDao {
	private static QueryRunner qr = new TxQueryRunner();
	private static int pageSize = 12; //每页显示多少条数据
	
	/**
	 * 查询书籍总显示页数
	 * @return books表按照每页显示12条数据，共有多少页
	 */
	public static int getPageCount() {
		int pageCount =0;  //总页数
		int totalRecounds = 0;  //数据库总记录
		String sql = "SELECT COUNT(*) from books";
		Object obj;
		try {
			obj = qr.query(sql, new ScalarHandler());
			Number number = (Number)obj;
			totalRecounds = number.intValue();
			//算出共有多少页
			pageCount = (totalRecounds-1)/pageSize+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}
		
	/**将数据库书籍进行分页处理
	 * @param pageNumber 当前的页数
	 * @return 对应页数的所有书籍集合
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
	 * 根据id号找到对应的书籍
	 * @param  书籍id号
	 * @return 对应书籍
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
	 * 根据对应的书籍修改位于数据库中的书籍库存量
	 * @param book 要进行库存量修改的书籍
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
	 * 根据书籍id删除对应的书籍
	 * @param bid 书籍id
	 * @return 是否删除成功
	 */
	public static boolean deleteBookById(String bid) {
		//DELETE FROM 表名  WHERE 条件
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
	 * 根据书籍id号修改书籍的信息
	 * @param book 要修改的书籍
	 * @return 是否修改成功过
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
	 * 在数据库表books表中添加一条数据
	 * @param book
	 * @return  是否插入成功
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
	 * 根据书籍的id或者书籍名称进行模糊匹配，查询对应的书
	 * @param bookId   书籍id号
	 * @param bookName  书籍名称
	 * @return
	 */
	public static ArrayList<Book> searchByIdOrName(String bookId,String bookName) {
		//判断是哪种方式查询匹配信息
		String sql = "";
		ArrayList<Book> list = null;
		if(bookId!= null && bookName=="") {
			//根据书籍id进行匹配
			sql ="select * from books where id = ?";
			try {
				list = (ArrayList<Book>) qr.query(sql, new BeanListHandler<>(Book.class), bookId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(bookId == "" && bookName != null) {
			//根据书籍名称进行模糊匹配    "%" + bookName + "%"
			sql = "select * from books where name like ? ";
			try {
				list = (ArrayList<Book>) qr.query(sql, new BeanListHandler<>(Book.class),"%" + bookName + "%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(bookId !=null && bookName!=null) {
			//根据书籍id和姓名同时进行模糊匹配
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
