package com.store.manager.book.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.book.dao.BookDao;

/**
 * 后台管理员对书籍进行删除操作
 * @author 老腰
 */
@WebServlet("/ManaBookDelServlet.do")
public class ManaBookDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收要进行删除的图书的id号
		String bid = request.getParameter("bid");
		boolean flag = BookDao.deleteBookById(bid);
		if(!flag) {
			request.setAttribute("error", "删除图书操作失败,请重新操作!");
			request.getRequestDispatcher("ManaFindBookServlet.do").forward(request, response);
			return ;
		}else {
			//删除成功
			request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
			return ;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
