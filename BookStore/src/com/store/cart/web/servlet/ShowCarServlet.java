package com.store.cart.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.MyCar;
import com.store.user.domain.User;

/**
 * 动态显示购物车中的数据  (主要避免页面进行刷新)
 * @author 老腰
 */
@WebServlet("/ShowCarServlet")
public class ShowCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取购物车中的所有数据信息，并传递给购物车显示界面
		MyCar mycar = (MyCar) request.getSession().getAttribute("myCar");
		
		//判断购物车对象是否为空，空就先登录
		if(mycar!=null) {
			//当书籍从购物车界面进行订单提交时，接收当库存不足时的错误信息
			request.setAttribute("errorMsg", request.getAttribute("error"));
			
			request.setAttribute("bookList", mycar.findAllBook());
			request.getRequestDispatcher("shoppingCar.jsp").forward(request, response);
			return;
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
