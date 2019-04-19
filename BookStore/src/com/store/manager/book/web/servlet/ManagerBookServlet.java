package com.store.manager.book.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.book.service.impl.BookServiceImpl;
import com.store.web.servlet.BaseServlet;
@WebServlet("/ManagerBookServlet.do")
public class ManagerBookServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private BookService service = new BookServiceImpl();;
	
	//通过查询的方式显示数据
	public String find(HttpServletRequest request ,HttpServletResponse response) {
		//要进行查询的书籍id , 书籍名
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		
		//根据查询条件获取结果集
		ArrayList<Book> list  = service.searchByIdOrName(bookId, bookName);	
		request.setAttribute("bookList", list);
		return "Manager/book.jsp";
	}
	
	//分页显示书籍
	public String showBook(HttpServletRequest request ,HttpServletResponse response) {
		//定义初始显示的页面为第一页
		int pageNumber = 1;
		//数据显示的总页数
		int  pageCount = service.getPageCount();
		//单纯根据从表单中传递的页数，进行书籍分页显示
		String page = request.getParameter("pageNumber");
		if(page!=null) {
			pageNumber =  Integer.parseInt(page);
		}
		ArrayList<Book> list = service.findAllBook(pageNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("bookList", list);
		return  "Manager/book.jsp";
	}
	
	//删除书籍
	public String delBook(HttpServletRequest request,HttpServletResponse response) {
		//接收要进行删除的图书的id号
		String bid = request.getParameter("bid");
		boolean flag = service.deleteBookById(bid);
		if(!flag) {
			request.setAttribute("error", "删除图书操作失败,请重新操作!");
			return "/ManagerBookServlet.do?method=find";
		}else {
			//删除成功
			return "Manager/manage-result.html";
		}
	}
	
	//修改书籍信息
	public String update(HttpServletRequest request,HttpServletResponse response) {
		Book book = service.fileUploadBook(request, response);
		boolean flag = service.updateBook(book);
		if(!flag) {
			request.setAttribute("error", "修改商品信息操作失败,请重新操作!");
			return "ManagerBookServlet.do?method=showBook";
		}
		return "Manager/manage-result.html";
	}
	
	//添加书籍
	public String add(HttpServletRequest request,HttpServletResponse response) {
		Book book = service.fileUploadBook(request, response);
		boolean flag = service.insertBook(book);
		if(!flag) {
			request.setAttribute("error", "添加商品操作失败,请重新操作!");
			return "Manager/book-add.jsp";
		}
		return "Manager/manage-result.html";
		
	}
	
}
