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
 * 交互式验证邮箱，手机，用户名是否已经存在
 * @author 老腰
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
			mapper.writeValue(response.getOutputStream(),"{nameExit:'true',nameMsg:'用户名已经被占用，请更换一个'}" );
		}
		
		if(UserDao.findByTel(tel)) {
			mapper.writeValue(response.getOutputStream(), "{telExit:'true',telMsg:'此电话号码已经被注册使用，请更换一个'}");
		}
		
		if(UserDao.findByEmail(email)){
			mapper.writeValue(response.getOutputStream(),"{emailExit:'true',emailMsg:'该邮箱已经被注册使用，请更换一个'}");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
