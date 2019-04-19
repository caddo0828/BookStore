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
	
	//��ʾ�����û�
	public String showUser(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<User> list = service.findAllUser();
		request.setAttribute("userList", list);
		return "Manager/user.jsp";
	}
	
	//ɾ���û�
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		boolean b = service.deleteUser(uid);
		 if(!b) {
			 request.setAttribute("error", "ɾ���û�����ʧ�ܣ������²�����");
			 return "ManagerUserServlet.do?method=showUser";
		 }  
		 return "Manager/manage-result.html";
	}
	
	//�޸��û���Ϣ
	public String update(HttpServletRequest request, HttpServletResponse response) {
		//���ձ�������
		String uid = request.getParameter("uid");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String code = request.getParameter("code");  //������
		int sta = 0; //ת������״̬
		if(status!=null) {
			 sta = Integer.parseInt(status);
		}
		
		User user = new User(uid,userName,passWord,email,tel,sta,code);
		//���ж��û���status���޸ļ�����
		if(sta==0) {
			//δ������ö�Ӧ�ļ�����
			user.setActivationCode(user.getId());
			//���ͼ����ʼ�
			String content = "��ϲ"+user.getName()+"��Ϊ�̳ǵ��û���"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>����˴�����</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = service.updateUser(user);
		if(!flag) {
			request.setAttribute("error", "�û���Ϣ�޸�ʧ�ܣ������²�����");
			return "ManagerUserServlet.do?method=showUser";
		}
		return "Manager/manage-result.html";
	}
	
	//����û�
	public String add(HttpServletRequest request, HttpServletResponse response) {
		//���ձ�������
		String uid = UUIDImpl.getUID();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String code = request.getParameter("code");  //������
		int sta = 0; //ת������״̬
		if(status!=null) {
			 sta = Integer.parseInt(status);
		}
		
		User user = new User(uid,userName,passWord,email,tel,sta,code);
		if(sta==0) {
			user.setActivationCode(user.getId());
			//���ͼ����ʼ�
			String content = "��ϲ"+user.getName()+"��Ϊ�̳ǵ��û���"+"<a href='http://localhost:8080/BookStore/UserServlet?method=actival&code="+user.getActivationCode()+"'>����˴�����</a>";
			try {
				SendMail.sendMail(user.getEmail(), content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean flag = service.insert(user);
		if(!flag) {
			request.setAttribute("error", "����û�����ʧ�ܣ������²�����");
			return "Manager/user-add.html";
		}
		return "Manager/manage-result.html";
	}
}
