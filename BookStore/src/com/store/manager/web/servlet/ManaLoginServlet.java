package com.store.manager.web.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.manager.domain.Manager;
import com.store.manager.service.ManagerService;
import com.store.manager.service.impl.ManagerServiceImpl;
import com.store.web.servlet.BaseServlet;

/**
 * 控制器对管理员登录进行校验，校验管理员信息成功匹配后，跳转后台管理中心
 * @author 老腰
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
