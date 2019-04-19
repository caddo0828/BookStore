package com.store.manager.web.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.manager.domain.Manager;
import com.store.manager.service.ManagerService;
import com.store.manager.service.impl.ManagerServiceImpl;
import com.store.web.servlet.BaseServlet;

/**
 * �������Թ���Ա��¼����У�飬У�����Ա��Ϣ�ɹ�ƥ�����ת��̨��������
 * @author ����
 */
@WebServlet("/ManaLoginServlet")
public class ManaLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("passWord");
		ManagerService service = new ManagerServiceImpl();
		Manager manager = (Manager) service.find(name, pwd);
		if(manager!=null) {
			request.getSession().setAttribute("loginManager", manager);
			return "Manager/index.html";
		}else {
			return "Manager/login.html";
		}	
	}
}
