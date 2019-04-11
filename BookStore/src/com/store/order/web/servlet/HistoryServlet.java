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
 * չʾ�����������ʷ��¼�Ŀ�����
 * @author ����
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("loginUser");
		HistoryService service = new HistoryService();
		
		if(user!=null) {
			//�����û�id�Ų�ѯ��Ӧ�Ķ�����Ϣ
			ArrayList<History> historyList = service.getHistoryList(user.getId());
			request.setAttribute("historyList", historyList);
			request.getRequestDispatcher("history_data.jsp").forward(request, response);
			return ;
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
