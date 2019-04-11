package com.store.order.service;

import java.util.ArrayList;

import com.store.order.dao.OrderDao;
import com.store.order.domain.History;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;

/**
 * �����û�id,�Ե�ǰ�û��Ķ�����ʷ��¼���в����ĸ�����
 * @author ����
 */
public class HistoryService {

	public ArrayList<History> getHistoryList(String userID) {
		ArrayList<History> arrayList = new ArrayList<>();
		//���ȸ����û�ID��ѯorders��
		ArrayList<Order> findOrderList = OrderDao.findOrder(userID);
		//һ���û����ܶ�Ӧ���������¼��������ȡÿ����������Լ���Ӧ�Ķ���ʱ�䣬�����ջ���ַ����ϵ�绰
		if(findOrderList!=null) {
			for(Order order: findOrderList) {
				ArrayList<OrderItem> ordertemList = OrderDao.findOrdertems(order.getId());
				//�����õ�ÿһ��orderitem����
				for(OrderItem item : ordertemList) {
					History	history = new History(order,item);
					arrayList.add(history);
				}
			}
		}
		return arrayList;
	}
	
}
