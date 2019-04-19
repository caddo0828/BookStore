package com.store.order.service.impl;

import java.util.ArrayList;

import com.store.order.dao.OrderDao;
import com.store.order.dao.impl.OrderDaoImpl;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.order.service.DataService;
import com.store.order.service.OrderService;
import com.store.user.domain.User;

public class OrderServiceImpl implements OrderService{
	private OrderDao dao = new OrderDaoImpl();
	@Override
	public boolean saveOrder(DataService helper, User user, Order order) {
		return dao.saveOrder(helper, user, order);
	}

	@Override
	public ArrayList<Order> findOrder(String userID) {
		return dao.findOrder(userID);
	}

	@Override
	public ArrayList<OrderItem> findOrdertems(String orderID) {
		return dao.findOrdertems(orderID);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

	@Override
	public ArrayList<Order> findPageOrder(int pageNumber) {
		return dao.findPageOrder(pageNumber);
	}

	@Override
	public boolean deleteOrderAndItem(String orderId) {
		return dao.deleteOrderAndItem(orderId);
	}

	@Override
	public ArrayList<Order> searchByIdOrName(String orderId, String orderName) {
		return dao.searchByIdOrName(orderId, orderName);
	}

	@Override
	public boolean updateOrdersById(String orderId, String orderName, double totalPrice, String address,
			String telephone) {
		return dao.updateOrdersById(orderId, orderName, totalPrice, address, telephone);
	}

}
