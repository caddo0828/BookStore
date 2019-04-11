package com.store.book.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;
import com.store.cart.service.MyCar;

/**
 * �Թ��ﳵ�е����ݽ������ӣ�ɾ�����޸��������Լ���չ��ﳵ�����Ŀ�����
 * @author ����
 */
@WebServlet("/BookCLServlet")
public class BookCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ﳵ����
	    MyCar mycar = (MyCar) request.getSession().getAttribute("myCar");
	    
	    //���մ��ݵ��鼮idֵ
	    String id = request.getParameter("id");
	  
	    //���ձ����������
	    String type = request.getParameter("type");
	    if(type!=null&&mycar!=null) {
	    	if(id!=null&&type.equals("add")) {
	    		//���ճ�ʼ�Ĺ�������
	    		String buyNums = request.getParameter("buyNums");
		    	mycar.add(id , BookDao.findById(id),Integer.parseInt(buyNums));
		    }else if(id!=null&&type.equals("delete")) {
				mycar.delete(id);
			}else if(type.equals("update")) {
				//���������һ�������ֵ�ķ�ʽ�������ݻ�ȡ����������ݶ�ʧ��ֻ�ܵĵ���һ���޸ĵ��鼮�����Լ�ID��
				String[] updateID = request.getParameterValues("updateID");
				//�õ��޸ĺ������������ÿ���������
				String[] nums = request.getParameterValues("updateNums");
				for(int i=0;i<updateID.length;i++) {
					mycar.update(updateID[i], Integer.parseInt(nums[i]));
				}
			}else if(type.equals("clear")) {
				//��չ��ﳵ
				mycar.clear();
			}
	    	response.sendRedirect("/BookStore/ShowCarServlet");
	    }else {
	    	request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
	    }   
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
