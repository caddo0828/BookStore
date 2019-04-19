package com.store.product.dao;

import java.util.ArrayList;

import com.store.product.domain.Product;

public interface ProductDao {
	/**
	 * 根据产品的类型，进行数据的获取 ，当前查询类型为  "今日特价"
	 * @return
	 */
	ArrayList<Product> findProductsByDisCount();
	 
	 /**
	 * 根据产品的类型，进行数据的获取 ，当前查询类型为  "热卖"
	 * @return
	 */
	 ArrayList<Product> findProductsHotBuy();
}
