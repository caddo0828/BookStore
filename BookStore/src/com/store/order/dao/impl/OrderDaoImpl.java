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
	private static int pageSize = 12; //订单数据分页显示，每页显示多少条数据
	
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
		//根据生成订单的降序进行查询，将最早购买的数据显示在最前面
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
		int pageCount =0;  //总页数
		int totalRecounds = 0;  //数据库总记录
		String sql = "SELECT COUNT(*) from orders";
		Object obj;
		try {
			obj = qr.query(sql, new ScalarHandler());
			Number number = (Number) obj;
			//数据库书籍总记录数
			totalRecounds = number.intValue();
			//算出共有多少页
			pageCount = (totalRecounds-1)/pageSize+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageCount;
	}

	@Override
	public ArrayList<Order> findPageOrder(int pageNumber) {
		ArrayList<Order> list = null;
		//从哪里开始获取数据
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
		//判断是哪种方式查询匹配信息
		String sql = "";
		ArrayList<Order> list = null;
		if(orderId!= null && orderName=="") {
			//根据id进行匹配
			sql ="select * from orders where id = ?";
			try {
				list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class), orderId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(orderId == "" && orderName != null) {
			//根据订货人进行模糊匹配    //"%" + bname + "%"
			sql = "select * from orders where orderName like ? ";
			try {
				list = (ArrayList<Order>) qr.query(sql, new BeanListHandler<>(Order.class),"%" + orderName + "%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(orderId!=null && orderName!=null) {
			//两者同时匹配，或者为  "" 查询时匹配
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
