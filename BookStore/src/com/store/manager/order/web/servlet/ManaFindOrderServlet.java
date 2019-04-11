package com.store.manager.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;
import com.store.order.dao.OrderDao;
import com.store.order.domain.Order;

/**
 * ���������ж��ǵ����Ľ��з�ҳ��ʾ�������ݣ�����ͨ����ѯ�ķ�ʽ��ʾ����
 * @author ����
 */
@WebServlet("/ManaFindOrderServlet.do")
public class ManaFindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pageNumber = 1; //��ʼҳ��
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//���մ�������,�ж��ǴӲ�ѯtype=search�����ģ����ǵ�����ʾ���� 
		String type = request.getParameter("type");
		//����Ҫ���в�ѯ�Ķ����ţ�����������
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		
		ArrayList<Order> list = null; 
		if(type!=null&&type.equals("search")) {
			list = OrderDao.searchByIdOrName(orderId, orderName);
		}else {
			//�������ݰ���ҳ��ʾ����ҳ��
			int pageCount = OrderDao.getCount();
			//���մӱ��д��ݵ�ҳ��
			String page = request.getParameter("pageNumber");
			if(page!=null) {
				int current =  Integer.parseInt(page);
				if(current>=1&&current<=pageCount) {
					pageNumber = current;
				}else if(current>pageCount) {
					pageNumber = current-1;
				}
			}
			list = OrderDao.findPageOrder(pageNumber);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageCount", pageCount);
		}
		request.setAttribute("orderList", list);
		request.getRequestDispatcher("Manager/order.jsp").forward(request, response);
		return;		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
