package com.store.manager.book.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;

/**
 * ��̨����Ա���鼮����ɾ������
 * @author ����
 */
@WebServlet("/ManaBookDelServlet.do")
public class ManaBookDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����Ҫ����ɾ����ͼ���id��
		String bid = request.getParameter("bid");
		boolean flag = BookDao.deleteBookById(bid);
		if(!flag) {
			request.setAttribute("error", "ɾ��ͼ�����ʧ��,�����²���!");
			request.getRequestDispatcher("ManaFindBookServlet.do").forward(request, response);
			return ;
		}else {
			//ɾ���ɹ�
			request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
			return ;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
