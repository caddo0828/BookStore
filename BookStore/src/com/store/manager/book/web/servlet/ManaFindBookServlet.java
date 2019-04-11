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
 * ���������ж��ǵ����Ľ��з�ҳ��ʾ�鼮�����ݣ�����ͨ����ѯ�ķ�ʽ��ʾ����
 * @author ����
 */
@WebServlet("/ManaFindBookServlet.do")
public class ManaFindBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pageNumber = 1; //�����ʼ��ʾ��ҳ��Ϊ��һҳ
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ��룬�����ѯʱ��ѯ����������
		request.setCharacterEncoding("utf-8");
		//���մ�������,�ж��ǴӲ�ѯtype=search�����ģ����ǵ�����ʾ���� 
		String type = request.getParameter("type");
		//Ҫ���в�ѯ���鼮id , �鼮��
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		
		ArrayList<Book> list = null;
		//��ѯ�鼮������ʾ
		if(type!=null&&type.equals("search")) {
			list = BookDao.searchByIdOrName(bookId, bookName);
		}else {
			//������ʾ����ҳ��
			int  pageCount = BookDao.getPageCount();
			//�������ݴӱ��д��ݵ�ҳ���������鼮��ҳ��ʾ
			String page = request.getParameter("pageNumber");
			if(page!=null) {
				int current =  Integer.parseInt(page);
				if(current>=1&&current<=pageCount) {
					pageNumber = current;
				}else if(current>pageCount) {
					//������ݵ�ҳ���������ҳ���ص���ǰ��ҳ��
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
