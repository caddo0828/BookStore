package com.store.user.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.user.dao.UserDao;
import com.store.user.domain.User;
/**
 * ���û����м�����߼���״̬�жϵĿ�����
 * @author ����
 */
@WebServlet("/ActionValServlet")
public class ActionValServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if(code!=null) {
			User user = UserDao.findUserByCode(code);
			if(user!=null) {
				//�޸ļ����״̬
				UserDao.setStatus(user);
				request.setAttribute("err", "�û�����ɹ�");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}else {
				request.setAttribute("err", "�û��Ѿ��������,����Ҫ���еڶ��μ���");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
