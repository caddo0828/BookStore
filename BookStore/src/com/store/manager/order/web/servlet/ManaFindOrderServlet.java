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
 * 根据类型判断是单纯的进行分页显示订单数据，还是通过查询的方式显示数据
 * @author 老腰
 */
@WebServlet("/ManaFindOrderServlet.do")
public class ManaFindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pageNumber = 1; //初始页面
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收处理类型,判断是从查询type=search过来的，还是单纯显示数据 
		String type = request.getParameter("type");
		//接收要进行查询的订单号，订购者姓名
		String orderId = request.getParameter("orderId");
		String orderName = request.getParameter("orderName");
		
		ArrayList<Order> list = null; 
		if(type!=null&&type.equals("search")) {
			list = OrderDao.searchByIdOrName(orderId, orderName);
		}else {
			//订单数据按分页显示的总页数
			int pageCount = OrderDao.getCount();
			//接收从表单中传递的页数
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
