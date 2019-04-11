package com.store.manager.user.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.user.dao.UserDao;
import com.store.user.domain.User;

/**
 * �������е��û������Ҷ�̬��ʾ�����û���Ϣ��user.jsp������
 * @author ����
 */
@WebServlet("/ManafindUserServlet.do")
public class ManafindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> list = UserDao.findAllUser();
		request.setAttribute("userList", list);
		request.getRequestDispatcher("Manager/user.jsp").forward(request, response);
		return ;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
