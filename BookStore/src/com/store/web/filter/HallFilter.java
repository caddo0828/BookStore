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
/** �����е���վǰ̨�й��ض���jsp�ļ����й���
 * @author ����
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
