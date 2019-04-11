package com.store.product.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.product.dao.ProductDao;
import com.store.product.domain.Product;

/**
 * 显示百货大厅数据的控制器
 * @author 老腰
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> disCountList = ProductDao.findProductsByDisCount();
		request.setAttribute("disCountList", disCountList);
		ArrayList<Product> hotList = ProductDao.findProductsHotBuy();
		request.setAttribute("hotList",hotList );
		request.getRequestDispatcher("product_hall.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
