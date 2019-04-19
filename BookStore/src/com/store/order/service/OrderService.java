package com.store.order.service;

import java.util.ArrayList;

import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.user.domain.User;

public interface OrderService{
	 boolean saveOrder(DataService helper ,User user,Order order);
	 
	 ArrayList<Order>  findOrder(String userID) ;
	 
	 ArrayList<OrderItem> findOrdertems(String orderID);
		
	 int getCount();
	 
	 ArrayList<Order> findPageOrder(int pageNumber) ;
	 
	 boolean deleteOrderAndItem(String orderId);
	 
	 ArrayList<Order> searchByIdOrName(String orderId,String orderName);
	 
	 boolean updateOrdersById(String orderId,String orderName,double totalPrice,String address, String telephone) ;
	 
}
