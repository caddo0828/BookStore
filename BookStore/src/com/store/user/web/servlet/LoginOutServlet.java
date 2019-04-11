package com.store.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全退出控制类，对清除用户此前登录保存的一切数据
 */
@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//安全退出,删除掉保存在session中的信息，减轻服务器的压力,彻底删除session中所有属性
		request.getSession().invalidate();
		//从客户端删除自动登录的cookie,获取当前保存了用户自动登录信息的cookie并且删除
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			//遍历所有的cookie，找到匹配的数据进行删除
			for(Cookie c : cookies) {
				if(c.getName().equals("autoLogin")) {
					Cookie cook = new Cookie(c.getName(), c.getValue());
					cook.setMaxAge(0);
					cook.setPath(request.getContextPath());
					response.addCookie(cook);
				}
			}
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	    return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
