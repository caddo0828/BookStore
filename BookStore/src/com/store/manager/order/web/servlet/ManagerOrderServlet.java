package com.store.manager.order.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.order.domain.Order;
import com.store.order.service.OrderService;
import com.store.order.service.impl.OrderServiceImpl;
import com.store.web.servlet.BaseServlet;

@WebServlet("/ManagerOrderServlet.do")
public class ManagerOrderServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	
	
	//���ݶ����źͶ������Ʋ�ѯ����
	public String search(HttpServletRequest request, HttpServletResponse response) {
		//����Ҫ���в�ѯ�Ķ����ţ�����������
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		ArrayList<Order> list = service.searchByIdOrName(orderId, orderName);
		request.setAttribute("orderList", list);
		return "Manager/order.jsp";
	}
	
	//��ҳ��ʾ������Ϣ
	public String showOrder(HttpServletRequest request, HttpServletResponse response) {
		//��ʼҳ��
		int pageNumber = 1;
		//�������ݰ���ҳ��ʾ����ҳ��
		int pageCount = service.getCount();
		//���մӱ��д��ݵ�ҳ��
		String page = request.getParameter("pageNumber");
		if(page!=null) {
			pageNumber =  Integer.parseInt(page);
		}
		ArrayList<Order> list = service.findPageOrder(pageNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("orderList", list);
		return "Manager/order.jsp";
	}
	
	
	//ɾ������
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		//������ȡ��ǰ����һҳ���е��޸ģ�ɾ���������������ɹ�����ʧ�ܺ�ص�֮ǰҳ�棬����鿴�޸Ľ�������ٴ��޸�����
		String pageNumber = request.getParameter("pageNumber");
		boolean flag = service.deleteOrderAndItem(orderId);
		if(!flag) {
			request.setAttribute("error", "ɾ����������ʧ�ܣ������²�����");
			return "ManagerOrderServlet.do?method=showOrder&pageNumber="+pageNumber;
		}
		return "Manager/manage-result.html";
	}
	
	//�޸Ķ���
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String pageNumber = request.getParameter("pageNumber");
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		String totalPrice = request.getParameter("totalPrice");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		boolean flag = service.updateOrdersById(orderId, orderName, Double.parseDouble(totalPrice), address, telephone);
		if(!flag) {
			request.setAttribute("error", "�޸Ķ�������ʧ�ܣ������²�����");
			return  "ManagerOrderServlet.do?method=showOrder&pageNumber="+pageNumber;
		}
		return "Manager/manage-result.html";
	}
}
