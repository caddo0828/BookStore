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
 * 根据类型判断，对订单信息进行修改，还是删除操作
 * @author 老腰
 */
@WebServlet("/ManaOrderServlet.do")
public class ManaOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收处理类型
		String type = request.getParameter("type");
		//这样获取当前在那一页进行的修改，删除操作，当操作成功或者失败后回到之前页面，方面查看修改结果或者再次修改数据
		String pageNumber = request.getParameter("pageNumber");
		
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		String totalPrice = request.getParameter("totalPrice");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		
		if(type.equals("delete")) {
			boolean flag = OrderDao.deleteOrderAndItem(orderId);
			if(!flag) {
				request.setAttribute("error", "删除订单操作失败，请重新操作！");
				request.getRequestDispatcher("ManaFindOrderServlet.do?pageNumber="+pageNumber).forward(request, response);
				return;
			}
		}else if(type.equals("update")) {
			boolean flag = OrderDao.updateOrdersById(orderId, orderName, Double.parseDouble(totalPrice), address, telephone);
			if(!flag) {
				request.setAttribute("error", "修改订单数据失败，请重新操作！");
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
