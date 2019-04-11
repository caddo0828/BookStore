package com.store.manager.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.manager.domain.Manager;
/**
 * 过滤器判断后台管理操作，只有成功登录后才能进行访问后台管理，单=但对初始的登录界面进行放行
 * @author 老腰
 */
public class ManaFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Manager manager = (Manager) ((HttpServletRequest)request).getSession().getAttribute("loginManager");
		
		String path = ((HttpServletRequest)request).getServletPath();
		String uri = path.substring(path.lastIndexOf("/")+1, path.length());
		if(uri.equals("login.html")||uri.equals("mana.css")||uri.equals("comm.js")||uri.equals("button_login.gif")||uri.equals("jquery-2.1.0.min.js")||manager!=null) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect("/BookStore/Manager/login.html");
		}	
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
