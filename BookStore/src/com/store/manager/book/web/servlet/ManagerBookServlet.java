package com.store.manager.book.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.book.service.impl.BookServiceImpl;
import com.store.web.servlet.BaseServlet;
@WebServlet("/ManagerBookServlet.do")
public class ManagerBookServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private BookService service = new BookServiceImpl();;
	
	//ͨ����ѯ�ķ�ʽ��ʾ����
	public String find(HttpServletRequest request ,HttpServletResponse response) {
		//Ҫ���в�ѯ���鼮id , �鼮��
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		
		//���ݲ�ѯ������ȡ�����
		ArrayList<Book> list  = service.searchByIdOrName(bookId, bookName);	
		request.setAttribute("bookList", list);
		return "Manager/book.jsp";
	}
	
	//��ҳ��ʾ�鼮
	public String showBook(HttpServletRequest request ,HttpServletResponse response) {
		//�����ʼ��ʾ��ҳ��Ϊ��һҳ
		int pageNumber = 1;
		//������ʾ����ҳ��
		int  pageCount = service.getPageCount();
		//�������ݴӱ��д��ݵ�ҳ���������鼮��ҳ��ʾ
		String page = request.getParameter("pageNumber");
		if(page!=null) {
			pageNumber =  Integer.parseInt(page);
		}
		ArrayList<Book> list = service.findAllBook(pageNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("bookList", list);
		return  "Manager/book.jsp";
	}
	
	//ɾ���鼮
	public String delBook(HttpServletRequest request,HttpServletResponse response) {
		//����Ҫ����ɾ����ͼ���id��
		String bid = request.getParameter("bid");
		boolean flag = service.deleteBookById(bid);
		if(!flag) {
			request.setAttribute("error", "ɾ��ͼ�����ʧ��,�����²���!");
			return "/ManagerBookServlet.do?method=find";
		}else {
			//ɾ���ɹ�
			return "Manager/manage-result.html";
		}
	}
	
	//�޸��鼮��Ϣ
	public String update(HttpServletRequest request,HttpServletResponse response) {
		Book book = service.fileUploadBook(request, response);
		boolean flag = service.updateBook(book);
		if(!flag) {
			request.setAttribute("error", "�޸���Ʒ��Ϣ����ʧ��,�����²���!");
			return "ManagerBookServlet.do?method=showBook";
		}
		return "Manager/manage-result.html";
	}
	
	//����鼮
	public String add(HttpServletRequest request,HttpServletResponse response) {
		Book book = service.fileUploadBook(request, response);
		boolean flag = service.insertBook(book);
		if(!flag) {
			request.setAttribute("error", "�����Ʒ����ʧ��,�����²���!");
			return "Manager/book-add.jsp";
		}
		return "Manager/manage-result.html";
		
	}
	
}
