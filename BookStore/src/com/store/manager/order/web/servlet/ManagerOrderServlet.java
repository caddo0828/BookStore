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
	
	
	//根据订单号和订单名称查询订单
	public String search(HttpServletRequest request, HttpServletResponse response) {
		//接收要进行查询的订单号，订购者姓名
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		ArrayList<Order> list = service.searchByIdOrName(orderId, orderName);
		request.setAttribute("orderList", list);
		return "Manager/order.jsp";
	}
	
	//分页显示订单信息
	public String showOrder(HttpServletRequest request, HttpServletResponse response) {
		//初始页面
		int pageNumber = 1;
		//订单数据按分页显示的总页数
		int pageCount = service.getCount();
		//接收从表单中传递的页数
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
	
	
	//删除订单
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		//这样获取当前在那一页进行的修改，删除操作，当操作成功或者失败后回到之前页面，方面查看修改结果或者再次修改数据
		String pageNumber = request.getParameter("pageNumber");
		boolean flag = service.deleteOrderAndItem(orderId);
		if(!flag) {
			request.setAttribute("error", "删除订单操作失败，请重新操作！");
			return "ManagerOrderServlet.do?method=showOrder&pageNumber="+pageNumber;
		}
		return "Manager/manage-result.html";
	}
	
	//修改订单
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String pageNumber = request.getParameter("pageNumber");
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		String totalPrice = request.getParameter("totalPrice");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		boolean flag = service.updateOrdersById(orderId, orderName, Double.parseDouble(totalPrice), address, telephone);
		if(!flag) {
			request.setAttribute("error", "修改订单数据失败，请重新操作！");
			return  "ManagerOrderServlet.do?method=showOrder&pageNumber="+pageNumber;
		}
		return "Manager/manage-result.html";
	}
}
