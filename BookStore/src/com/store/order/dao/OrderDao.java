package com.store.order.dao;

import java.util.ArrayList;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.order.service.DataService;
import com.store.user.domain.User;

public interface OrderDao {
	/** 对orders表 以及orderitem表插入对应的数据
	 *@param helper 订单辅助类，将购买的数据添加进订单中
	 * @param user  用户类，用于获取用户的id号，保存在对应的数据库表orders中
	 * @param order 订单信息类，获取收货人姓名，收货电话，收货地址，插入数据库表orders中
	 * @return 代表数据库表信息是否成功插入
	 */
	 boolean saveOrder(DataService helper ,User user,Order order);

	 /**
	 * 根据用户的id号查询对应的订单表（主要用在前台界面用户查询历史订单)
	 * @param userID 用户id
	 * @return
	 */
	 ArrayList<Order>  findOrder(String userID) ;
	 
	//根据orders表中的订单号查询orderitem表中的数据
	ArrayList<OrderItem> findOrdertems(String orderID);
	
	/**
	 * 查询订单表中的总页数
	 * @return  订单表orders的总页数
	 */
	int getCount();
	
	/**
	 * 将订单主表orders进行分页处理
	 * @param pageNumber  要显示的是哪一页的数据
	 * @return  对应页数的订单数据
	 */
	 ArrayList<Order> findPageOrder(int pageNumber) ;
	 
	 /**
	 * 根据订单号删除订单，并且删除对应的订单子表的数据
	 * @param orderId 订单号
	 * @return  是否删除成功
	 */
	 boolean deleteOrderAndItem(String orderId);
	 
	 /**
	 * 根据订单号或者收货人姓名进行模糊匹配查找订单数据
	 * @param orderId  orders表中的订单号
	 * @param orderName  订购人姓名
	 * @return  订购数据的集合
	 */
	 ArrayList<Order> searchByIdOrName(String orderId,String orderName);
	 
	 
	 /**
	 * 根据订单号修改orders表中的订货人姓名，商品总价，订购电话，收货地址
	 * @param orderId    订单表orders中的订单号
	 * @param orderName  订货人姓名
	 * @param totalPrice 商品总价
	 * @param address    收货地址
	 * @param telephone  订购电话
	 * @return 是否操作成功
	 */
	 boolean updateOrdersById(String orderId,String orderName,double totalPrice,String address, String telephone) ;
	 
	 
}
