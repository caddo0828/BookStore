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

/** ������������ݵķ�ҳ�����Լ���ת����Ӧҳ�����߼�����
 * @author ����
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int pageNumber = 1; //�����ʼ����ʾ�ӵ�һҳ���ݿ�ʼ��ʾ

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageCount = BookDao.getPageCount();
		
		//���մӱ��д��ݵ�ҳ��
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		
		ArrayList<Book> list = BookDao.findAllBook(pageNumber);
		//��������ҳ������pageCount���ҳ����ص��ڵ�һҳ��
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
