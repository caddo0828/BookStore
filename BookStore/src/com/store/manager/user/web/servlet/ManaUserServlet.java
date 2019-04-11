package com.store.manager.user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.mail.SendMail;
import com.store.web.service.UUIDImpl;

/**
 * ʵ�ֶ��û������ӣ�ɾ�����޸Ĳ����������ɹ�����תmanage-result.html
 * @author ����
 */
@WebServlet("/ManaUserServlet.do")
public class ManaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String type = request.getParameter("type");
		if(type.equals("update")) {
			//���ж��û���status���޸ļ�����
			if(sta==0) {
				//δ������ö�Ӧ�ļ�����
				user.setActivationCode(user.getId());
				//���ͼ����ʼ�
				String content = "��ϲ"+user.getName()+"��Ϊ�̳ǵ��û���"+"<a href='http://47.102.207.63:8080/BookStore/ActionValServlet?code="+user.getActivationCode()+"'>����˴�����</a>";
				try {
					SendMail.sendMail(user.getEmail(), content);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			boolean flag = UserDao.updateUser(user);
			if(!flag) {
				request.setAttribute("error", "�û���Ϣ�޸�ʧ�ܣ������²�����");
				request.getRequestDispatcher("/ManafindUserServlet.do").forward(request, response);
				return;
			}
		}else if(type.equals("delete")) {
			//�����û�idɾ���û�
			 boolean b = UserDao.deleteUser(uid);
			 if(!b) {
				 request.setAttribute("error", "ɾ���û�����ʧ�ܣ������²�����");
				 request.getRequestDispatcher("/ManafindUserServlet.do").forward(request, response);
				 return;
			 } 
		}else if(type.equals("add")) {
			user.setId(UUIDImpl.getUID());
			if(sta==0) {
				user.setActivationCode(user.getId());
				//���ͼ����ʼ�
				String content = "��ϲ"+user.getName()+"��Ϊ�̳ǵ��û���"+"<a href='http://47.102.207.63:8080/BookStore/ActionValServlet?code="+user.getActivationCode()+"'>����˴�����</a>";
				try {
					SendMail.sendMail(user.getEmail(), content);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			boolean flag = UserDao.insert(user);
			if(!flag) {
				request.setAttribute("error", "����û�����ʧ�ܣ������²�����");
				request.getRequestDispatcher("Manager/user-add.html").forward(request, response);
				return;
			}
		}	
		request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
		return ;
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
