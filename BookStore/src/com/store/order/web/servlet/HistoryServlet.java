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
 * չʾ�����������ʷ��¼�Ŀ�����
 * @author ����
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String showHistory(HttpServletRequest request,HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("loginUser");
		HistoryService service = new HistoryServiceImpl();
		
		if(user!=null) {
			//�����û�id�Ų�ѯ��Ӧ�Ķ�����Ϣ
			ArrayList<History> historyList = service.getHistoryList(user.getId());
			request.setAttribute("historyList", historyList);
			return "history_data.jsp";
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
	}

}
