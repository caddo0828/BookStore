package com.store.product.service.impl;

import java.util.ArrayList;

import com.store.product.dao.ProductDao;
import com.store.product.dao.impl.ProductDaoImpl;
import com.store.product.domain.Product;
import com.store.product.service.ProductService;

public class ProductServiceImpl implements ProductService{
	private ProductDao dao = new ProductDaoImpl();
	
	@Override
	public ArrayList<Product> findProductsByDisCount() {
		return dao.findProductsByDisCount();
	}

	@Override
	public ArrayList<Product> findProductsHotBuy() {
		return dao.findProductsByDisCount();
	}

}
