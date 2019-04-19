package com.store.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.store.user.service.UserService;
import com.store.user.service.impl.UserServiceImpl;
import com.store.web.service.ObjMapper;
import com.store.web.servlet.BaseServlet;

/**
 * ����ʽ��֤���䣬�ֻ����û����Ƿ��Ѿ�����
 * @author ����
 */
@WebServlet("/UserAjaxServlet")
public class UserAjaxServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public void ajaxRequest(HttpServletRequest request ,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		String  userName = request.getParameter("userName");
		String  tel = request.getParameter("tel");
		String  email = request.getParameter("email");
		
		UserService service = new UserServiceImpl();
		ObjMapper mapper = new ObjMapper();	
		if(service.findByName(userName)) {
			mapper.writeValue(response.getOutputStream(),"{nameExit:'true',nameMsg:'�û����Ѿ���ռ�ã������һ��'}" );
		}
		
		if(service.findByTel(tel)) {
			mapper.writeValue(response.getOutputStream(), "{telExit:'true',telMsg:'�˵绰�����Ѿ���ע��ʹ�ã������һ��'}");
		}
		
		if(service.findByEmail(email)){
			mapper.writeValue(response.getOutputStream(),"{emailExit:'true',emailMsg:'�������Ѿ���ע��ʹ�ã������һ��'}");
		}
		
	}

}
