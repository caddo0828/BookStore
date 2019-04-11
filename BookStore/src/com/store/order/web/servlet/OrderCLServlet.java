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
 * 对提交的订单进行处理的操作类，并且将数据添加到订单辅助类中
 * @author 老腰
 */
@WebServlet("/OrderCLServlet")
public class OrderCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建一个处理订单书籍的对象
		DataService helper = new DataService();
		request.getSession().setAttribute("dataHelper",helper);
		
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user!=null) {
			//判断是由直接购买传入的数据，还是从购物车跳转的
			//做出判断后,对之前的订单数据进行先clear，在添加新订单
			String type = request.getParameter("type");
			Book book = new Book();
			if(type!=null) {
				if(type.equals("imme")) {
					//接收直接购买时传递的书籍id
					String id = request.getParameter("id");
					//接收传递的立即购买的数量
					String buyNums = request.getParameter("buyNums");
					if(id!=null) {
						//添加之前先将之前的订单数据清空
						helper.clear();
						book = BookDao.findById(id);
						//设置书籍初始数量
						book.setShopNums(Integer.parseInt(buyNums));
						helper.add(book);
					}
				}else if(type.equals("shoppingCar")) {
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
							book = BookDao.findById(id);
							//先判断书籍的总库存跟书籍的购买量大小
							if(book.getNums()>=num) {
								book.setShopNums(num);
								helper.add(book);
							}else {
								//只要有一本书籍的库存不满足条件，就把之前成功添加在订单辅助对象中的数据清除
								helper.clear();
								request.setAttribute("error", book.getName()+",此本书库存不足,当前总库存为"+book.getNums()+",请您修改购买数量!");
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
			request.setAttribute("err", "请先登录再进行操作");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
