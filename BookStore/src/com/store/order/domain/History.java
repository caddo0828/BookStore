package com.store.order.domain;
/**
 * 历史记录辅助对象信息类, 包含order对象，以及orderItem对象信息
 * @author 老腰
 */
public class History {
	private Order order;
	private OrderItem orderItem;
	public History() {
	}
	public History(Order order, OrderItem orderItem) {
		super();
		this.order = order;
		this.orderItem = orderItem;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

}
