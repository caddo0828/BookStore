package com.store.book.web.servlet;


import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.book.service.impl.BookServiceImpl;
import com.store.cart.service.CarService;
import com.store.web.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet{
	private BookService service = new BookServiceImpl();
	private static int pageNumber = 1; //�����ʼ����ʾ�ӵ�һҳ���ݿ�ʼ��ʾ
	
	public String showAllBook(HttpServletRequest request,HttpServletResponse response) {
		int pageCount = service.getPageCount();
		//���մӱ��д��ݵ�ҳ��
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		ArrayList<Book> list = service.findAllBook(pageNumber);
		//��������ҳ������pageCount���ҳ����ص��ڵ�һҳ��
		if(pageNumber > pageCount) {
			list = service.findAllBook(1);
			pageNumber = 1;
		}
		request.setAttribute("bookList", list);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount",pageCount);
		return "book_list.jsp";
	}
	
	public String bookView(HttpServletRequest request,HttpServletResponse response) {
		//���ܴ��ݵ��鼮idֵ
		String id = request.getParameter("id");
		String pageNumber = request.getParameter("pageNumber");
		Book book = service.findById(id);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("bookView", book);
		return "book_view.jsp";
	}
	
	//�Թ��ﳵ�е����ݽ�������
	public String add(HttpServletRequest request,HttpServletResponse response) {
		//��ȡ���ﳵ����
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");
		if(mycar!=null) {
			//���մ��ݵ��鼮idֵ
		    String id = request.getParameter("id");
		    //���ճ�ʼ�Ĺ�������
			String buyNums = request.getParameter("buyNums");
	    	mycar.add(id , service.findById(id),Integer.parseInt(buyNums));
	    	return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}
	
	//ɾ�����ﳵ��Ӧ�鼮
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		//��ȡ���ﳵ����
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");
			   
		//���մ��ݵ��鼮idֵ
		String id = request.getParameter("id");
		if(mycar!=null) {
			mycar.delete(id);
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}
	
	//��չ��ﳵ�鼮
	public String clear(HttpServletRequest request,HttpServletResponse response) {
		//��ȡ���ﳵ����
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");		   
		if(mycar!=null) {
			mycar.clear();
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}
	
	//�Թ��ﳵ�鼮���������޸�
	public String updateNums(HttpServletRequest request,HttpServletResponse response) {
		//��ȡ���ﳵ����
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");		   
		if(mycar!=null) {
			//���������һ�������ֵ�ķ�ʽ�������ݻ�ȡ����������ݶ�ʧ��ֻ�ܵĵ���һ���޸ĵ��鼮�����Լ�ID��
			String[] updateID = request.getParameterValues("updateID");
			//�õ��޸ĺ������������ÿ���������
			String[] nums = request.getParameterValues("updateNums");
			for(int i=0;i<updateID.length;i++) {
				mycar.update(updateID[i], Integer.parseInt(nums[i]));
			}
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}
	
}
