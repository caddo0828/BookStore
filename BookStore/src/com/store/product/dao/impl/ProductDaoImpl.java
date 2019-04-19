package com.store.product.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.product.dao.ProductDao;
import com.store.product.domain.Product;
import com.store.web.utils.TxQueryRunner;

public class ProductDaoImpl implements ProductDao{
	private static QueryRunner qr = new TxQueryRunner();
	
	@Override
	public ArrayList<Product> findProductsByDisCount() {
		ArrayList<Product> list = null;
		String sql = "select * from products where type=? ";
		try {
			list = (ArrayList<Product>)qr.query(sql, new BeanListHandler<>(Product.class), "今日特价");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Product> findProductsHotBuy() {
		ArrayList<Product> list = null;
		String sql = "select * from products where type=? ";
		try {
			list = (ArrayList<Product>)qr.query(sql, new BeanListHandler<>(Product.class), "热卖");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
