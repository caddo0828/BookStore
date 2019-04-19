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
			//接收直接购买时传递的书籍id
			String id = request.getParameter("id");
			//接收传递的立即购买的数量
			String buyNums = request.getParameter("buyNums");
			if(id!=null) {
				//添加之前先将之前的订单数据清空
				helper.clear();
				Book book = service.findById(id);
				//设置书籍初始数量
				book.setShopNums(Integer.parseInt(buyNums));
				helper.add(book);
				request.setAttribute("bookList",helper.findAllBook());
				request.setAttribute("totalPrice", helper.getTotalPrice());
				return "order.jsp";
			}
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
		return null;
	}
	
	
	public String shoppingCar(HttpServletRequest request, HttpServletResponse response) {
		//从购物车中传递的要进行购买的信息
		String[] books =  request.getParameterValues("books");
		//添加之前先将之前的订单数据清空
		helper.clear();
		if(books!=null) {
			for(int i=0;i<books.length;i++) {
				String[] array = books[i].split("=");
				//此时书籍的id对应第array数组的第一个值，数量对应第二个值
				String id = array[0];
				int num = Integer.parseInt(array[1]);
				//通过id找到对应的书籍
				Book book = service.findById(id);
				//先判断书籍的总库存跟书籍的购买量大小
				if(book.getNums()>=num) {
					book.setShopNums(num);
					helper.add(book);
				}else {
					//只要有一本书籍的库存不满足条件，就把之前成功添加在订单辅助对象中的数据清除
					helper.clear();
					request.setAttribute("error", book.getName()+",此本书库存不足,当前总库存为"+book.getNums()+",请您修改购买数量!");
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
		// 获取用户
		User user = (User) request.getSession().getAttribute("loginUser");
		BookService service = new BookServiceImpl();
		CarService car = new CarServiceImpl();
				
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
				boolean flag = orderService.saveOrder(helper, user, order);
				if (flag) {
					// 取出所有订单书籍，当真正购买成功时的时候再对数据库的书籍数量进行修改
					for (Book book : list) {
						// 修改数据库中书籍的数量
						service.updateNums(book);
						// 根据对应的书籍找到id，并且清除对应的购物车中的书籍数据
						car.delete(book.getId());
						// 购买成功后，清除所有订单数据
						helper.clear();
					}
					return "shopping-result.html";
				}
			}

		} else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
		return null;
	}
}
