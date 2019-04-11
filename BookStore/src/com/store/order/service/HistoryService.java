package com.store.order.service;

import java.util.ArrayList;

import com.store.order.dao.OrderDao;
import com.store.order.domain.History;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;

/**
 * 根据用户id,对当前用户的订单历史记录进行操作的辅助类
 * @author 老腰
 */
public class HistoryService {

	public ArrayList<History> getHistoryList(String userID) {
		ArrayList<History> arrayList = new ArrayList<>();
		//首先根据用户ID查询orders表
		ArrayList<Order> findOrderList = OrderDao.findOrder(userID);
		//一个用户可能对应多个订单记录，遍历获取每个订单编号以及对应的订单时间，订单收货地址，联系电话
		if(findOrderList!=null) {
			for(Order order: findOrderList) {
				ArrayList<OrderItem> ordertemList = OrderDao.findOrdertems(order.getId());
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
