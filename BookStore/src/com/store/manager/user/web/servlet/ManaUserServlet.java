package com.store.manager.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.mail.SendMail;
import com.store.web.service.UUIDImpl;

/**
 * 实现对用户的增加，删除，修改操作，操作成功后跳转manage-result.html
 * @author 老腰
 */
@WebServlet("/ManaUserServlet.do")
public class ManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收表单的数据
		String uid = request.getParameter("uid");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String code = request.getParameter("code");  //激活码
		int sta = 0; //转换激活状态
		if(status!=null) {
			 sta = Integer.parseInt(status);
		}
		
		User user = new User(uid,userName,passWord,email,tel,sta,code);
		String type = request.getParameter("type");
		if(type.equals("update")) {
			//先判断用户的status，修改激活码
			if(sta==0) {
				//未激活，设置对应的激活码
				user.setActivationCode(user.getId());
				//发送激活邮件
				String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://47.102.207.63:8080/BookStore/ActionValServlet?code="+user.getActivationCode()+"'>点击此处激活</a>";
				try {
					SendMail.sendMail(user.getEmail(), content);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			boolean flag = UserDao.updateUser(user);
			if(!flag) {
				request.setAttribute("error", "用户信息修改失败，请重新操作！");
				request.getRequestDispatcher("/ManafindUserServlet.do").forward(request, response);
				return;
			}
		}else if(type.equals("delete")) {
			//根据用户id删除用户
			 boolean b = UserDao.deleteUser(uid);
			 if(!b) {
				 request.setAttribute("error", "删除用户操作失败，请重新操作！");
				 request.getRequestDispatcher("/ManafindUserServlet.do").forward(request, response);
				 return;
			 } 
		}else if(type.equals("add")) {
			user.setId(UUIDImpl.getUID());
			if(sta==0) {
				user.setActivationCode(user.getId());
				//发送激活邮件
				String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://47.102.207.63:8080/BookStore/ActionValServlet?code="+user.getActivationCode()+"'>点击此处激活</a>";
				try {
					SendMail.sendMail(user.getEmail(), content);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			boolean flag = UserDao.insert(user);
			if(!flag) {
				request.setAttribute("error", "添加用户操作失败，请重新操作！");
				request.getRequestDispatcher("Manager/user-add.html").forward(request, response);
				return;
			}
		}	
		request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
		return ;
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
