package com.store.order.service.impl;

import java.util.ArrayList;

import com.store.order.domain.History;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.order.service.HistoryService;
import com.store.order.service.OrderService;

public class HistoryServiceImpl implements HistoryService{
	private OrderService service = new OrderServiceImpl();
	
	@Override
	public ArrayList<History> getHistoryList(String userID) {
		ArrayList<History> arrayList = new ArrayList<>();
		//���ȸ����û�ID��ѯorders��
		ArrayList<Order> findOrderList = service.findOrder(userID);
		//һ���û����ܶ�Ӧ���������¼��������ȡÿ����������Լ���Ӧ�Ķ���ʱ�䣬�����ջ���ַ����ϵ�绰
		if(findOrderList!=null) {
			for(Order order: findOrderList) {
				ArrayList<OrderItem> ordertemList = service.findOrdertems(order.getId());
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
