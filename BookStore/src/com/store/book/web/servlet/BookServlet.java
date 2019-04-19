package com.store.book.web.servlet;


import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.book.service.impl.BookServiceImpl;
import com.store.cart.service.CarService;
import com.store.web.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet{
	private BookService service = new BookServiceImpl();
	private static int pageNumber = 1; //定义初始的显示从第一页数据开始显示
	
	public String showAllBook(HttpServletRequest request,HttpServletResponse response) {
		int pageCount = service.getPageCount();
		//接收从表单中传递的页数
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		ArrayList<Book> list = service.findAllBook(pageNumber);
		//如果输入的页数大于pageCount最大页，则回到在第一页中
		if(pageNumber > pageCount) {
			list = service.findAllBook(1);
			pageNumber = 1;
		}
		request.setAttribute("bookList", list);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount",pageCount);
		return "book_list.jsp";
	}
	
	public String bookView(HttpServletRequest request,HttpServletResponse response) {
		//接受传递的书籍id值
		String id = request.getParameter("id");
		String pageNumber = request.getParameter("pageNumber");
		Book book = service.findById(id);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("bookView", book);
		return "book_view.jsp";
	}
	
	//对购物车中的数据进行增加
	public String add(HttpServletRequest request,HttpServletResponse response) {
		//获取购物车对象
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");
		if(mycar!=null) {
			//接收传递的书籍id值
		    String id = request.getParameter("id");
		    //接收初始的购买数量
			String buyNums = request.getParameter("buyNums");
	    	mycar.add(id , service.findById(id),Integer.parseInt(buyNums));
	    	return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}
	
	//删除购物车对应书籍
	public String delete(HttpServletRequest request,HttpServletResponse response) {
		//获取购物车对象
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");
			   
		//接收传递的书籍id值
		String id = request.getParameter("id");
		if(mycar!=null) {
			mycar.delete(id);
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}
	
	//清空购物车书籍
	public String clear(HttpServletRequest request,HttpServletResponse response) {
		//获取购物车对象
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");		   
		if(mycar!=null) {
			mycar.clear();
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}
	
	//对购物车书籍数量进行修改
	public String updateNums(HttpServletRequest request,HttpServletResponse response) {
		//获取购物车对象
		CarService mycar = (CarService) request.getSession().getAttribute("myCar");		   
		if(mycar!=null) {
			//如果不采用一个键多个值的方式进行数据获取，会造成数据丢失，只能的到第一次修改的书籍数量以及ID号
			String[] updateID = request.getParameterValues("updateID");
			//得到修改后的输入框的数量每本书的数量
			String[] nums = request.getParameterValues("updateNums");
			for(int i=0;i<updateID.length;i++) {
				mycar.update(updateID[i], Integer.parseInt(nums[i]));
			}
			return "ShowCarServlet?method=showCar";
		}else {
			request.setAttribute("err", "请先登录再进行操作");
			return "login.jsp";
		}
	}
	
}
