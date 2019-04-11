package com.store.manager.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.manager.dao.ManagerDao;
import com.store.manager.domain.Manager;

/**
 * �������Թ���Ա��¼����У�飬У�����Ա��Ϣ�ɹ�ƥ�����ת��̨��������
 * @author ����
 */
@WebServlet("/ManaLoginServlet")
public class ManaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("passWord");
		
		Manager manager = ManagerDao.find(name, pwd);
		if(manager!=null) {
			request.getSession().setAttribute("loginManager", manager);
			request.getRequestDispatcher("Manager/index.html").forward(request, response);
			return ;
		}else {
			request.getRequestDispatcher("Manager/login.html").forward(request, response);
			return ;
		}	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
