package com.store.order.web.servlet;


import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.order.domain.History;
import com.store.order.service.HistoryService;
import com.store.order.service.impl.HistoryServiceImpl;
import com.store.user.domain.User;
import com.store.web.servlet.BaseServlet;

/**
 * 展示订单购买的历史记录的控制器
 * @author 老腰
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String showHistory(HttpServletRequest request,HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("loginUser");
		HistoryService service = new HistoryServiceImpl();
		
		if(user!=null) {
			//根据用户id号查询对应的订单信息
			ArrayList<History> historyList = service.getHistoryList(user.getId());
			request.setAttribute("historyList", historyList);
			return "history_data.jsp";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}

}
