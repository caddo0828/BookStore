package com.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.user.dao.UserDao;
import com.store.web.service.ObjMapper;

/**
 * ����ʽ��֤���䣬�ֻ����û����Ƿ��Ѿ�����
 * @author ����
 */
@WebServlet("/UserAjaxServlet")
public class UserAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  userName = request.getParameter("userName");
		String  tel = request.getParameter("tel");
		String  email = request.getParameter("email");
		
		ObjMapper mapper = new ObjMapper();	
		if(UserDao.findByName(userName)) {
			mapper.writeValue(response.getOutputStream(),"{nameExit:'true',nameMsg:'�û����Ѿ���ռ�ã������һ��'}" );
		}
		
		if(UserDao.findByTel(tel)) {
			mapper.writeValue(response.getOutputStream(), "{telExit:'true',telMsg:'�˵绰�����Ѿ���ע��ʹ�ã������һ��'}");
		}
		
		if(UserDao.findByEmail(email)){
			mapper.writeValue(response.getOutputStream(),"{emailExit:'true',emailMsg:'�������Ѿ���ע��ʹ�ã������һ��'}");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
