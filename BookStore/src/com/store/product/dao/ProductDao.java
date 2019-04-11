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
	 * ���ݲ�Ʒ�����ͣ��������ݵĻ�ȡ ����ǰ��ѯ����Ϊ  "�����ؼ�"
	 * @return
	 */
	public static ArrayList<Product> findProductsByDisCount() {
		ArrayList<Product> list = null;
		String sql = "select * from products where type=? ";
		try {
			list = (ArrayList<Product>)qr.query(sql, new BeanListHandler<>(Product.class), "�����ؼ�");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ���ݲ�Ʒ�����ͣ��������ݵĻ�ȡ ����ǰ��ѯ����Ϊ  "����"
	 * @return
	 */
	public static ArrayList<Product> findProductsHotBuy() {
		ArrayList<Product> list = null;
		String sql = "select * from products where type=? ";
		try {
			list = (ArrayList<Product>)qr.query(sql, new BeanListHandler<>(Product.class), "����");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;

	}
}
