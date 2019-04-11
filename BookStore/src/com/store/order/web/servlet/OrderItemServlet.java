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

/** 对订单辅助类中的数据进行处理。操作形成数据订单表以及子表
 * @author 老腰
 */
@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 直接从对应的订单数据处理对象中获取所有的订单数据
		DataService helper = (DataService) request.getSession().getAttribute("dataHelper");
		// 获取用户
		User user = (User) request.getSession().getAttribute("loginUser");
		
		// 接收表单传入的订货人的数据
		String orderName = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		String address = request.getParameter("address");
		// 创建订购者信息
		Order order = new Order(orderName, tel, address);

		if (helper != null&&user!=null) {
			ArrayList<Book> list = helper.findAllBook();
			if (list != null) {
				// 保存订单信息，生成数据库表,成功插入则跳转
				boolean flag = OrderDao.saveOrder(helper, user, order);
				if (flag) {
					// 取出所有订单书籍，当真正购买成功时的时候再对数据库的书籍数量进行修改
					for (Book book : list) {
						// 修改数据库中书籍的数量
						BookDao.updateNums(book);
						// 根据对应的书籍找到id，并且清除对应的购物车中的书籍数据
						new MyCar().delete(book.getId());
						// 购买成功后，清除所有订单数据
						helper.clear();
					}
					request.getRequestDispatcher("shopping-result.html").forward(request, response);
					return;
				}
			}

		} else {
			request.setAttribute("err", "请先登录再进行操作");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
