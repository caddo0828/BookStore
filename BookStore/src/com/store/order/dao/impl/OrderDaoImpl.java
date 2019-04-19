package com.store.order.dao.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.book.domain.Book;
import com.store.order.dao.OrderDao;
import com.store.order.domain.Order;
import com.store.order.domain.OrderItem;
import com.store.order.service.DataService;
import com.store.user.domain.User;
import com.store.web.service.CurrentDate;
import com.store.web.service.UUIDImpl;
import com.store.web.utils.TxQueryRunner;

public class OrderDaoImpl implements OrderDao{
	private static QueryRunner qr = new TxQueryRunner();
	private static int pageSize = 12; //�������ݷ�ҳ��ʾ��ÿҳ��ʾ����������
	
	@Override
	public boolean saveOrder(DataService helper, User user, Order order) {
		String sql = "insert into orders(id,userID,orderName,telephone,address,totalPrice,orderDate) values(?,?,?,?,?,?,?)";
		String orderId = UUIDImpl.getUID();
		Object[] params = {orderId ,user.getId(),order.getOrderName()
						,order.getTelephone(),order.getAddress(),helper.getTotalPrice()
						,CurrentDate.getTime()};
		try {
			qr.update(sql, params);
			sql = "insert into orderitem(id,orderID,bookID,bookName,bookNum,bookPrice) values(?,?,?,?,?,?)";
			ArrayList<Book> list = helper.findAllBook();
			for(Book book : list) {
				Object[] para = {UUIDImpl.getUID(),orderId,book.getId(),book.getName(),book.getShopNums(),book.getPrice()};
				qr.update(sql, para);
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return false;
	}

	@Override
	public ArrayList<Order> findOrder(String userID) {
		//�������ɶ����Ľ�����в�ѯ�������繺���������ʾ����ǰ��
		String sql = "select * from orders  where userID=? order by orderDate desc";
		ArrayList<Order> list = null;
		try {
			list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class), userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<OrderItem> findOrdertems(String orderID) {
		String sql = "select * from orderitem where orderID=?";
		ArrayList<OrderItem> orderitemList = null;
		try {
			orderitemList = (ArrayList<OrderItem>) qr.query(sql, new BeanListHandler<>(OrderItem.class), orderID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitemList;
	}

	@Override
	public int getCount() {
		int pageCount =0;  //��ҳ��
		int totalRecounds = 0;  //���ݿ��ܼ�¼
		String sql = "SELECT COUNT(*) from orders";
		Object obj;
		try {
			obj = qr.query(sql, new ScalarHandler());
			Number number = (Number) obj;
			//���ݿ��鼮�ܼ�¼��
			totalRecounds = number.intValue();
			//������ж���ҳ
			pageCount = (totalRecounds-1)/pageSize+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}

	@Override
	public ArrayList<Order> findPageOrder(int pageNumber) {
		ArrayList<Order> list = null;
		//�����￪ʼ��ȡ����
		int pages = pageSize*(pageNumber-1);
		String sql = "select * from orders order by id limit"+" "+pages+","+pageSize;
		try {
			list = (ArrayList<Order>) qr.query(sql,new BeanListHandler<>(Order.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteOrderAndItem(String orderId) {
		String sql = "delete from orders where id=?";
		try {
			int num = qr.update(sql, orderId);
			if(num>=1) {
				sql = "delete from orderitem where orderID=?";
				int i = qr.update(sql, orderId);
				if(i>=1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Order> searchByIdOrName(String orderId, String orderName) {
		//�ж������ַ�ʽ��ѯƥ����Ϣ
		String sql = "";
		ArrayList<Order> list = null;
		if(orderId!= null && orderName=="") {
			//����id����ƥ��
			sql ="select * from orders where id = ?";
			try {
				list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class), orderId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(orderId == "" && orderName != null) {
			//���ݶ����˽���ģ��ƥ��    //"%" + bname + "%"
			sql = "select * from orders where orderName like ? ";
			try {
				list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class),"%" + orderName + "%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(orderId!=null && orderName!=null) {
			//����ͬʱƥ�䣬����Ϊ  "" ��ѯʱƥ��
			sql = "select * from orders where orderName like ? and id =? ";
			try {
				Object[] params = {"%" + orderName + "%",orderId};
				list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class),params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean updateOrdersById(String orderId, String orderName, double totalPrice, String address,
			String telephone) {
		String sql = "update orders set orderName=? , totalPrice=? , address=? , telephone=? where id=?";
		Object[] params = {orderName,totalPrice,address,telephone,orderId};
		int num = 0;
		try {
			num = qr.update(sql, params);
			if(num>=1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
