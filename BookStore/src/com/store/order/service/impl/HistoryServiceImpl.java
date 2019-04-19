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
		//首先根据用户ID查询orders表
		ArrayList<Order> findOrderList = service.findOrder(userID);
		//一个用户可能对应多个订单记录，遍历获取每个订单编号以及对应的订单时间，订单收货地址，联系电话
		if(findOrderList!=null) {
			for(Order order: findOrderList) {
				ArrayList<OrderItem> ordertemList = service.findOrdertems(order.getId());
				//遍历得到每一个orderitem对象
				for(OrderItem item : ordertemList) {
					History	history = new History(order,item);
					arrayList.add(history);
				}
			}
		}
		return arrayList;
	}

}
