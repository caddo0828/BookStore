package com.store.cart.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.cart.service.CarService;
import com.store.web.servlet.BaseServlet;

/**
 * 动态显示购物车中的数据  (主要避免页面进行刷新)
 * @author 老腰
 */
@WebServlet("/ShowCarServlet")
public class ShowCarServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String showCar(HttpServletRequest request, HttpServletResponse response) {
		//获取购物车中的所有数据信息，并传递给购物车显示界面
		 CarService car = (CarService) request.getSession().getAttribute("myCar");
		
		//判断购物车对象是否为空，空就先登录
		if(car!=null) {
			//当书籍从购物车界面进行订单提交时，接收当库存不足时的错误信息
			request.setAttribute("errorMsg", request.getAttribute("error"));
			request.setAttribute("bookList", car.findAllBook());
			return "shoppingCar.jsp";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}
}
