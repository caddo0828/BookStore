package com.store.product.web.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.product.domain.Product;
import com.store.product.service.ProductService;
import com.store.product.service.impl.ProductServiceImpl;
import com.store.web.servlet.BaseServlet;

/**
 * 显示百货大厅数据的控制器
 * @author 老腰
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	public String showHall(HttpServletRequest request,HttpServletResponse response) {
		ProductService service = new ProductServiceImpl();
		ArrayList<Product> disCountList = service.findProductsByDisCount();
		request.setAttribute("disCountList", disCountList);
		ArrayList<Product> hotList = service.findProductsHotBuy();
		request.setAttribute("hotList",hotList );
		return "product_hall.jsp";
	}
	

}
