package com.store.manager.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.order.dao.OrderDao;

/**
 * ���������жϣ��Զ�����Ϣ�����޸ģ�����ɾ������
 * @author ����
 */
@WebServlet("/ManaOrderServlet.do")
public class ManaOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//���մ�������
		String type = request.getParameter("type");
		//������ȡ��ǰ����һҳ���е��޸ģ�ɾ���������������ɹ�����ʧ�ܺ�ص�֮ǰҳ�棬����鿴�޸Ľ�������ٴ��޸�����
		String pageNumber = request.getParameter("pageNumber");
		
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		String totalPrice = request.getParameter("totalPrice");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		
		if(type.equals("delete")) {
			boolean flag = OrderDao.deleteOrderAndItem(orderId);
			if(!flag) {
				request.setAttribute("error", "ɾ����������ʧ�ܣ������²�����");
				request.getRequestDispatcher("ManaFindOrderServlet.do?pageNumber="+pageNumber).forward(request, response);
				return;
			}
		}else if(type.equals("update")) {
			boolean flag = OrderDao.updateOrdersById(orderId, orderName, Double.parseDouble(totalPrice), address, telephone);
			if(!flag) {
				request.setAttribute("error", "�޸Ķ�������ʧ�ܣ������²�����");
				request.getRequestDispatcher("ManaFindOrderServlet.do?pageNumber="+pageNumber).forward(request, response);
				return;
			}
		}
		request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
		return ;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
