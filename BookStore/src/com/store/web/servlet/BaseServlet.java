package com.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		
		try {
			//判断方法名称进行执行
			String mt = request.getParameter("method");
			//判断参数是否为空，如果为空，让其执行默认方法
			if(mt == null || mt.trim().length()==0 || mt=="") {
				mt = "index";
			}
			
			//获取方法对象
			Method method = this.getClass().getMethod(mt, HttpServletRequest.class,HttpServletResponse.class);
			
			//让方法执行，获取返回值
			String path = (String) method.invoke(this, request ,response);
			//根据返回值判断是否要进行请求转发
			if(path!=null) {
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}	
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "index.jsp";
	}
}
