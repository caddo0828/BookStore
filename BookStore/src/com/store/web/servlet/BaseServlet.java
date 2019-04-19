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
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		
		try {
			//�жϷ������ƽ���ִ��
			String mt = request.getParameter("method");
			//�жϲ����Ƿ�Ϊ�գ����Ϊ�գ�����ִ��Ĭ�Ϸ���
			if(mt == null || mt.trim().length()==0 || mt=="") {
				mt = "index";
			}
			
			//��ȡ��������
			Method method = this.getClass().getMethod(mt, HttpServletRequest.class,HttpServletResponse.class);
			
			//�÷���ִ�У���ȡ����ֵ
			String path = (String) method.invoke(this, request ,response);
			//���ݷ���ֵ�ж��Ƿ�Ҫ��������ת��
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
