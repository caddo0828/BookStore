package com.store.product.service;

import java.util.ArrayList;

import com.store.product.domain.Product;

public interface ProductService {
	ArrayList<Product> findProductsByDisCount();
	 
	ArrayList<Product> findProductsHotBuy();
}
