package com.store.manager.book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;
import com.store.book.domain.Book;

/**
 * 根据类型判断是单纯的进行分页显示书籍的数据，还是通过查询的方式显示数据
 * @author 老腰
 */
@WebServlet("/ManaFindBookServlet.do")
public class ManaFindBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pageNumber = 1; //定义初始显示的页面为第一页
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码，避免查询时查询名中文乱码
		request.setCharacterEncoding("utf-8");
		//接收处理类型,判断是从查询type=search过来的，还是单纯显示数据 
		String type = request.getParameter("type");
		//要进行查询的书籍id , 书籍名
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		
		ArrayList<Book> list = null;
		//查询书籍，并显示
		if(type!=null&&type.equals("search")) {
			list = BookDao.searchByIdOrName(bookId, bookName);
		}else {
			//数据显示的总页数
			int  pageCount = BookDao.getPageCount();
			//单纯根据从表单中传递的页数，进行书籍分页显示
			String page = request.getParameter("pageNumber");
			if(page!=null) {
				int current =  Integer.parseInt(page);
				if(current>=1&&current<=pageCount) {
					pageNumber = current;
				}else if(current>pageCount) {
					//如果传递的页数大于最大页，回到当前的页数
					pageNumber = current-1;
				}
			}
			list = BookDao.findAllBook(pageNumber);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageCount", pageCount);
		}

		request.setAttribute("bookList", list);
		request.getRequestDispatcher("Manager/book.jsp").forward(request, response);
		return ;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
