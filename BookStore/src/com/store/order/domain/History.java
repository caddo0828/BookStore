package com.store.order.domain;
/**
 * ��ʷ��¼����������Ϣ��, ����order�����Լ�orderItem������Ϣ
 * @author ����
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
