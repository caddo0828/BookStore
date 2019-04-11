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
 * 对用户进行激活，或者激活状态判断的控制器
 * @author 老腰
 */
@WebServlet("/ActionValServlet")
public class ActionValServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		if(code!=null) {
			User user = UserDao.findUserByCode(code);
			if(user!=null) {
				//修改激活的状态
				UserDao.setStatus(user);
				request.setAttribute("err", "用户激活成功");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}else {
				request.setAttribute("err", "用户已经激活过了,不需要进行第二次激活");
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
