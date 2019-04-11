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
 * 对购物车中的数据进行增加，删除。修改数量，以及清空购物车操作的控制器
 * @author 老腰
 */
@WebServlet("/BookCLServlet")
public class BookCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取购物车对象
	    MyCar mycar = (MyCar) request.getSession().getAttribute("myCar");
	    
	    //接收传递的书籍id值
	    String id = request.getParameter("id");
	  
	    //接收表单处理的类型
	    String type = request.getParameter("type");
	    if(type!=null&&mycar!=null) {
	    	if(id!=null&&type.equals("add")) {
	    		//接收初始的购买数量
	    		String buyNums = request.getParameter("buyNums");
		    	mycar.add(id , BookDao.findById(id),Integer.parseInt(buyNums));
		    }else if(id!=null&&type.equals("delete")) {
				mycar.delete(id);
			}else if(type.equals("update")) {
				//如果不采用一个键多个值的方式进行数据获取，会造成数据丢失，只能的到第一次修改的书籍数量以及ID号
				String[] updateID = request.getParameterValues("updateID");
				//得到修改后的输入框的数量每本书的数量
				String[] nums = request.getParameterValues("updateNums");
				for(int i=0;i<updateID.length;i++) {
					mycar.update(updateID[i], Integer.parseInt(nums[i]));
				}
			}else if(type.equals("clear")) {
				//清空购物车
				mycar.clear();
			}
	    	response.sendRedirect("/BookStore/ShowCarServlet");
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
