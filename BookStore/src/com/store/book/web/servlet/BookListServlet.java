package com.store.book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;
import com.store.book.domain.Book;

/** 处理购物大厅数据的分页技术以及跳转到对应页数的逻辑处理
 * @author 老腰
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int pageNumber = 1; //定义初始的显示从第一页数据开始显示

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageCount = BookDao.getPageCount();
		
		//接收从表单中传递的页数
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		
		ArrayList<Book> list = BookDao.findAllBook(pageNumber);
		//如果输入的页数大于pageCount最大页，则回到在第一页中
		if(pageNumber > pageCount) {
			list = BookDao.findAllBook(1);
			pageNumber = 1;
		}
		
		request.setAttribute("bookList", list);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount",pageCount);
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
