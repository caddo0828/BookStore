package com.store.product.dao;

import java.util.ArrayList;

import com.store.product.domain.Product;

public interface ProductDao {
	/**
	 * ���ݲ�Ʒ�����ͣ��������ݵĻ�ȡ ����ǰ��ѯ����Ϊ  "�����ؼ�"
	 * @return
	 */
	ArrayList<Product> findProductsByDisCount();
	 
	 /**
	 * ���ݲ�Ʒ�����ͣ��������ݵĻ�ȡ ����ǰ��ѯ����Ϊ  "����"
	 * @return
	 */
	 ArrayList<Product> findProductsHotBuy();
}
