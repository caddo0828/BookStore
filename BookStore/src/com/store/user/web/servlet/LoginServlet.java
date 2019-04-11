package com.store.user.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.MyCar;
import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.service.CurrentDate;
/**
 * 实现对用户的校验，形成购物车对象，跳转购物大厅操作
 * @author 老腰
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收表单提交的电话号码以及密码
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		//获取存储在session中的生成的四位数验证码，与用户输入进行匹配
		String checkNumber = (String) request.getSession().getAttribute("checkNumber");
		//获取用户输入的验证码
		String veryCode = request.getParameter("veryCode");
		
		//先进行验证码验证，再 到数据库匹配登录用户的信息
		if(veryCode!=null&&veryCode.equals(checkNumber)) {
			User user = UserDao.search(name, pwd);
			if(user==null) {
				//用户不存在，输入错误
				request.setAttribute("err", "用户联系电话或者密码输入错误，请重新输入!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}else if(user.getStatus() == 1){
				//用户正确并且已经被激活，登录跳转,为其创建一个购物车，用于添加用户信息,并且将用户信息存储到session中
				 request.getSession().setAttribute("loginUser", user);
				
				//当前登录时间
				request.getSession().setAttribute("time", CurrentDate.getTime());
				
				//创建购物车对象,并且添加到session中
				MyCar mycar = new MyCar();
				request.getSession().setAttribute("myCar", mycar);
				
				//做自动登录的cookie,将用户电话以及密码保存，并且进行加密处理
				String keepTime = request.getParameter("keepTime");
				if(keepTime!=null){
					//为保证cookie中可以保存中文数据，进行base64编码
					Cookie cookie = new Cookie("autoLogin",java.net.URLEncoder.encode(name, "utf-8")
							+"="+java.net.URLEncoder.encode(pwd, "utf-8"));
					cookie.setMaxAge(Integer.parseInt(keepTime));
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				//进入百货商品首页
				response.sendRedirect("/BookStore/ProductServlet");
				return ;
			}else {
				//用户未激活
				request.setAttribute("err", "当前用户未激活!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}else {
			//当输入验证码错误时给出错误提示
			request.setAttribute("err", "验证码输入错误，请重新输入");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
