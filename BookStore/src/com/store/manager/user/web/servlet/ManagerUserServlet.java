package com.store.manager.user.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.user.domain.User;
import com.store.user.service.UserService;
import com.store.user.service.impl.UserServiceImpl;
import com.store.web.mail.SendMail;
import com.store.web.service.UUIDImpl;
import com.store.web.servlet.BaseServlet;

@WebServlet("/ManagerUserServlet.do")
public class ManagerUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserServiceImpl();
	
	//显示所有用户
	public String showUser(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> list = service.findAllUser();
		request.setAttribute("userList", list);
		return "Manager/user.jsp";
	}
	
	//删除用户
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		boolean b = service.deleteUser(uid);
		 if(!b) {
			 request.setAttribute("error", "删除用户操作失败，请重新操作！");
			 return "ManagerUserServlet.do?method=showUser";
		 }  
		 return "Manager/manage-result.html";
	}
	
	//修改用户信息
	public String update(HttpServletRequest request, HttpServletResponse response) {
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
		//先判断用户的status，修改激活码
		if(sta==0) {
			//未激活，设置对应的激活码
			user.setActivationCode(user.getId());
			//发送激活邮件
			String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>点击此处激活</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = service.updateUser(user);
		if(!flag) {
			request.setAttribute("error", "用户信息修改失败，请重新操作！");
			return "ManagerUserServlet.do?method=showUser";
		}
		return "Manager/manage-result.html";
	}
	
	//添加用户
	public String add(HttpServletRequest request, HttpServletResponse response) {
		//接收表单的数据
		String uid = UUIDImpl.getUID();
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
		if(sta==0) {
			user.setActivationCode(user.getId());
			//发送激活邮件
			String content = "恭喜"+user.getName()+"成为商城的用户，"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>点击此处激活</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = service.insert(user);
		if(!flag) {
			request.setAttribute("error", "添加用户操作失败，请重新操作！");
			return "Manager/user-add.html";
		}
		return "Manager/manage-result.html";
	}
}
