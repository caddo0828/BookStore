package com.store.manager.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.store.manager.dao.ManagerDao;
import com.store.manager.domain.Manager;
import com.store.web.utils.TxQueryRunner;

public class ManagerDaoImpl implements ManagerDao{
	private static QueryRunner qr = new TxQueryRunner();
	@Override
	public Manager find(String name, String pwd) {
		String sql = "select * from managers where name=? and pwd=?";
		Object[] params = {name,pwd};
		Manager manager = null;
		try {
			manager = qr.query(sql, new BeanHandler<>(Manager.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}

}
