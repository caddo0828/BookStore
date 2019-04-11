package com.store.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.activation.DataHandler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.book.dao.BookDao;
import com.store.book.domain.Book;
import com.store.order.service.DataService;
import com.store.user.domain.User;
/**
 * ���ύ�Ķ������д���Ĳ����࣬���ҽ�������ӵ�������������
 * @author ����
 */
@WebServlet("/OrderCLServlet")
public class OrderCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����һ���������鼮�Ķ���
		DataService helper = new DataService();
		request.getSession().setAttribute("dataHelper",helper);
		
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user!=null) {
			//�ж�����ֱ�ӹ���������ݣ����Ǵӹ��ﳵ��ת��
			//�����жϺ�,��֮ǰ�Ķ������ݽ�����clear��������¶���
			String type = request.getParameter("type");
			Book book = new Book();
			if(type!=null) {
				if(type.equals("imme")) {
					//����ֱ�ӹ���ʱ���ݵ��鼮id
					String id = request.getParameter("id");
					//���մ��ݵ��������������
					String buyNums = request.getParameter("buyNums");
					if(id!=null) {
						//���֮ǰ�Ƚ�֮ǰ�Ķ����������
						helper.clear();
						book = BookDao.findById(id);
						//�����鼮��ʼ����
						book.setShopNums(Integer.parseInt(buyNums));
						helper.add(book);
					}
				}else if(type.equals("shoppingCar")) {
					//�ӹ��ﳵ�д��ݵ�Ҫ���й������Ϣ
					String[] books =  request.getParameterValues("books");
					//���֮ǰ�Ƚ�֮ǰ�Ķ����������
					helper.clear();
					if(books!=null) {
						for(int i=0;i<books.length;i++) {
							String[] array = books[i].split("=");
							//��ʱ�鼮��id��Ӧ��array����ĵ�һ��ֵ��������Ӧ�ڶ���ֵ
							String id = array[0];
							int num = Integer.parseInt(array[1]);
							//ͨ��id�ҵ���Ӧ���鼮
							book = BookDao.findById(id);
							//���ж��鼮���ܿ����鼮�Ĺ�������С
							if(book.getNums()>=num) {
								book.setShopNums(num);
								helper.add(book);
							}else {
								//ֻҪ��һ���鼮�Ŀ�治�����������Ͱ�֮ǰ�ɹ�����ڶ������������е��������
								helper.clear();
								request.setAttribute("error", book.getName()+",�˱����治��,��ǰ�ܿ��Ϊ"+book.getNums()+",�����޸Ĺ�������!");
								request.getRequestDispatcher("/ShowCarServlet").forward(request, response);
								return;
							}
						}
					}	
				}
				request.setAttribute("bookList",helper.findAllBook());
				request.setAttribute("totalPrice", helper.getTotalPrice());
				request.getRequestDispatcher("order.jsp").forward(request, response);
				return ;
			}
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
