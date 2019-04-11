package com.store.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ȫ�˳������࣬������û���ǰ��¼�����һ������
 */
@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȫ�˳�,ɾ����������session�е���Ϣ�������������ѹ��,����ɾ��session����������
		request.getSession().invalidate();
		//�ӿͻ���ɾ���Զ���¼��cookie,��ȡ��ǰ�������û��Զ���¼��Ϣ��cookie����ɾ��
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			//�������е�cookie���ҵ�ƥ������ݽ���ɾ��
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
