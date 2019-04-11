package com.store.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.book.dao.BookDao;
import com.store.book.domain.Book;
import com.store.cart.service.MyCar;
import com.store.order.dao.OrderDao;
import com.store.order.domain.Order;
import com.store.order.service.DataService;
import com.store.user.domain.User;

/** �Զ����������е����ݽ��д��������γ����ݶ������Լ��ӱ�
 * @author ����
 */
@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ֱ�ӴӶ�Ӧ�Ķ������ݴ�������л�ȡ���еĶ�������
		DataService helper = (DataService) request.getSession().getAttribute("dataHelper");
		// ��ȡ�û�
		User user = (User) request.getSession().getAttribute("loginUser");
		
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
				boolean flag = OrderDao.saveOrder(helper, user, order);
				if (flag) {
					// ȡ�����ж����鼮������������ɹ�ʱ��ʱ���ٶ����ݿ���鼮���������޸�
					for (Book book : list) {
						// �޸����ݿ����鼮������
						BookDao.updateNums(book);
						// ���ݶ�Ӧ���鼮�ҵ�id�����������Ӧ�Ĺ��ﳵ�е��鼮����
						new MyCar().delete(book.getId());
						// ����ɹ���������ж�������
						helper.clear();
					}
					request.getRequestDispatcher("shopping-result.html").forward(request, response);
					return;
				}
			}

		} else {
			request.setAttribute("err", "���ȵ�¼�ٽ��в���");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
