package com.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 对所有的网站前台有关特定的jsp文件进行过滤
 * @author 老腰
 */
public class HallFilter implements Filter {
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		if(req.getSession().getAttribute("loginUser")==null) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}else {
			chain.doFilter(request, response);			
		}
		
		
		    
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
