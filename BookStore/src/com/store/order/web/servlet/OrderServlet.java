package com.store.order.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.book.service.impl.BookServiceImpl;
import com.store.cart.service.CarService;
import com.store.cart.service.impl.CarServiceImpl;
import com.store.order.domain.Order;
import com.store.order.service.DataService;
import com.store.order.service.OrderService;
import com.store.order.service.impl.DataServiceImpl;
import com.store.order.service.impl.OrderServiceImpl;
import com.store.user.domain.User;
import com.store.web.servlet.BaseServlet;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet{
	private BookService service = new BookServiceImpl();
	private DataService helper = new DataServiceImpl();
	
	public String immeBuy(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("loginUser");
		
		if(user!=null) {
			//����ֱ�ӹ���ʱ���ݵ��鼮id
			String id = request.getParameter("id");
			//���մ��ݵ��������������
			String buyNums = request.getParameter("buyNums");
			if(id!=null) {
				//���֮ǰ�Ƚ�֮ǰ�Ķ����������
				helper.clear();
				Book book = service.findById(id);
				//�����鼮��ʼ����
				book.setShopNums(Integer.parseInt(buyNums));
				helper.add(book);
				request.setAttribute("bookList",helper.findAllBook());
				request.setAttribute("totalPrice", helper.getTotalPrice());
				return "order.jsp";
			}
		}else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
		return null;
	}
	
	
	public String shoppingCar(HttpServletRequest request, HttpServletResponse response) {
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
				Book book = service.findById(id);
				//���ж��鼮���ܿ����鼮�Ĺ�������С
				if(book.getNums()>=num) {
					book.setShopNums(num);
					helper.add(book);
				}else {
					//ֻҪ��һ���鼮�Ŀ�治�����������Ͱ�֮ǰ�ɹ�����ڶ������������е��������
					helper.clear();
					request.setAttribute("error", book.getName()+",�˱����治��,��ǰ�ܿ��Ϊ"+book.getNums()+",�����޸Ĺ�������!");
					return "ShowCarServlet?method=showCar";
				}
			}
			request.setAttribute("bookList",helper.findAllBook());
			request.setAttribute("totalPrice", helper.getTotalPrice());
			return "order.jsp";
		}	
		return null;
	}
	
	
	public String payItem(HttpServletRequest request, HttpServletResponse response) {
		OrderService orderService = new OrderServiceImpl();
		// ��ȡ�û�
		User user = (User) request.getSession().getAttribute("loginUser");
		BookService service = new BookServiceImpl();
		CarService car = new CarServiceImpl();
				
		// ���ձ�����Ķ����˵�����
		String orderName = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		String address = request.getParameter("address");
		// ������������Ϣ
		Order order = new Order(orderName, tel, address);
		if (helper != null&&user!=null) {
			ArrayList<Book> list = helper.findAllBook();
			if (list != null) {
				// ���涩����Ϣ���������ݿ��,�ɹ���������ת
				boolean flag = orderService.saveOrder(helper, user, order);
				if (flag) {
					// ȡ�����ж����鼮������������ɹ�ʱ��ʱ���ٶ����ݿ���鼮���������޸�
					for (Book book : list) {
						// �޸����ݿ����鼮������
						service.updateNums(book);
						// ���ݶ�Ӧ���鼮�ҵ�id�����������Ӧ�Ĺ��ﳵ�е��鼮����
						car.delete(book.getId());
						// ����ɹ���������ж�������
						helper.clear();
					}
					return "shopping-result.html";
				}
			}

		} else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			return "login.jsp";
		}
		return null;
	}
}
