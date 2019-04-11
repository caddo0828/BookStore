package com.store.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.mail.SendMail;
import com.store.web.service.ObjMapper;
import com.store.web.service.UUIDImpl;

/**
 *将用户注册信息插入到数据库，生成激活码，发送激活邮件
 * @author 老腰
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//接收从表单中提交的数据
		String name = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		User user = new User(UUIDImpl.getUID(),name, pwd, email, tel);
		
		//手动改设置激活状态
		user.setStatus(0);
		//手动设置激活码
		user.setActivationCode(user.getId());
		if(UserDao.insert(user)) {
			//发送激活邮件
			String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://47.102.207.63:8080/BookStore/ActionValServlet?code="+user.getActivationCode()+"'>点击此处激活</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 //注册成功跳转到reg-result.html界面，直接转向到登录界面
	    	 response.sendRedirect("/BookStore/reg-result.html");
	    	 return;
	    }else {
	    	//注册失败
	    	response.sendRedirect("/BookStore/register.html");
	    	return;
	    }
	
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
