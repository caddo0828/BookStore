package com.store.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.order.dao.OrderDao;
import com.store.order.domain.History;
import com.store.order.domain.Order;
import com.store.order.service.HistoryService;
import com.store.user.domain.User;

/**
 * 展示订单购买的历史记录的控制器
 * @author 老腰
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("loginUser");
		HistoryService service = new HistoryService();
		
		if(user!=null) {
			//根据用户id号查询对应的订单信息
			ArrayList<History> historyList = service.getHistoryList(user.getId());
			request.setAttribute("historyList", historyList);
			request.getRequestDispatcher("history_data.jsp").forward(request, response);
			return ;
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
