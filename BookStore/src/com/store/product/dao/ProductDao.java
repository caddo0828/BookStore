package com.store.product.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.store.product.domain.Product;
import com.store.web.utils.TxQueryRunner;

public class ProductDao {
	private static QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 根据产品的类型，进行数据的获取 ，当前查询类型为  "今日特价"
	 * @return
	 */
	public static ArrayList<Product> findProductsByDisCount() {
		ArrayList<Product> list = null;
		String sql = "select * from products where type=? ";
		try {
			list = (ArrayList<Product>)qr.query(sql, new BeanListHandler<>(Product.class), "今日特价");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据产品的类型，进行数据的获取 ，当前查询类型为  "热卖"
	 * @return
	 */
	public static ArrayList<Product> findProductsHotBuy() {
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
