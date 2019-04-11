package com.store.user.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.MyCar;
import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.service.CurrentDate;
/**
 * ʵ�ֶ��û���У�飬�γɹ��ﳵ������ת�����������
 * @author ����
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ձ��ύ�ĵ绰�����Լ�����
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		//��ȡ�洢��session�е����ɵ���λ����֤�룬���û��������ƥ��
		String checkNumber = (String) request.getSession().getAttribute("checkNumber");
		//��ȡ�û��������֤��
		String veryCode = request.getParameter("veryCode");
		
		//�Ƚ�����֤����֤���� �����ݿ�ƥ���¼�û�����Ϣ
		if(veryCode!=null&&veryCode.equals(checkNumber)) {
			User user = UserDao.search(name, pwd);
			if(user==null) {
				//�û������ڣ��������
				request.setAttribute("err", "�û���ϵ�绰�������������������������!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}else if(user.getStatus() == 1){
				//�û���ȷ�����Ѿ��������¼��ת,Ϊ�䴴��һ�����ﳵ����������û���Ϣ,���ҽ��û���Ϣ�洢��session��
				 request.getSession().setAttribute("loginUser", user);
				
				//��ǰ��¼ʱ��
				request.getSession().setAttribute("time", CurrentDate.getTime());
				
				//�������ﳵ����,������ӵ�session��
				MyCar mycar = new MyCar();
				request.getSession().setAttribute("myCar", mycar);
				
				//���Զ���¼��cookie,���û��绰�Լ����뱣�棬���ҽ��м��ܴ���
				String keepTime = request.getParameter("keepTime");
				if(keepTime!=null){
					//Ϊ��֤cookie�п��Ա����������ݣ�����base64����
					Cookie cookie = new Cookie("autoLogin",java.net.URLEncoder.encode(name, "utf-8")
							+"="+java.net.URLEncoder.encode(pwd, "utf-8"));
					cookie.setMaxAge(Integer.parseInt(keepTime));
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				//����ٻ���Ʒ��ҳ
				response.sendRedirect("/BookStore/ProductServlet");
				return ;
			}else {
				//�û�δ����
				request.setAttribute("err", "��ǰ�û�δ����!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}else {
			//��������֤�����ʱ����������ʾ
			request.setAttribute("err", "��֤�������������������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return ;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
