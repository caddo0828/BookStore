package com.store.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.user.domain.User;
import com.store.user.dao.UserDao;
/**
 * 对login.jsp, 以及index.jsp进行过滤， 判断是否要实现自动登陆功能
 * 进行登录操作
 * @author 老腰
 */
public class LoginFilter implements Filter{
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//设置统一编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取cookie信息
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals("autoLogin")) {
					//说明找到了对应的cookie，取出值存放在session对象中，供Login.jsp界面直接使用
					String[] autoLogin = cookies[i].getValue().split("=");
				    String tel="";
				    String pwd="";
					try {
						tel = java.net.URLDecoder.decode(autoLogin[0], "utf-8");
						pwd = java.net.URLDecoder.decode(autoLogin[1], "utf-8");
						User user = UserDao.search(tel, pwd);
						//当重启服务器后，或者reload对应的session失效，此时无法从对应的session中取出存储的用户信息，也就是无法自动登录
						((HttpServletRequest)request).getSession().setAttribute("loginUser", user);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				
				}
			}
		}
		
		//放行
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
