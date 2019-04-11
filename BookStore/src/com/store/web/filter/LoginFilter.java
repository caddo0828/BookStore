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
 * ��login.jsp, �Լ�index.jsp���й��ˣ� �ж��Ƿ�Ҫʵ���Զ���½����
 * ���е�¼����
 * @author ����
 */
public class LoginFilter implements Filter{
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//����ͳһ�����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//��ȡcookie��Ϣ
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals("autoLogin")) {
					//˵���ҵ��˶�Ӧ��cookie��ȡ��ֵ�����session�����У���Login.jsp����ֱ��ʹ��
					String[] autoLogin = cookies[i].getValue().split("=");
				    String tel="";
				    String pwd="";
					try {
						tel = java.net.URLDecoder.decode(autoLogin[0], "utf-8");
						pwd = java.net.URLDecoder.decode(autoLogin[1], "utf-8");
						User user = UserDao.search(tel, pwd);
						//�������������󣬻���reload��Ӧ��sessionʧЧ����ʱ�޷��Ӷ�Ӧ��session��ȡ���洢���û���Ϣ��Ҳ�����޷��Զ���¼
						((HttpServletRequest)request).getSession().setAttribute("loginUser", user);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				
				}
			}
		}
		
		//����
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
