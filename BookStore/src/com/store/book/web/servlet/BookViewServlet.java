package com.store.book.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;
import com.store.book.domain.Book;

/**
 * 通过用户的选择，单一的展示选中的商品的信息
 * @author 老腰
 */
@WebServlet("/BookViewServlet")
public class BookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受传递的书籍id值
		String id = request.getParameter("id");
		String pageNumber = request.getParameter("pageNumber");
		Book book = BookDao.findById(id);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("bookView", book);
		request.getRequestDispatcher("book_view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
