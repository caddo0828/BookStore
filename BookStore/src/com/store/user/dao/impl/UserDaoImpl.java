package com.store.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.user.dao.UserDao;
import com.store.user.domain.User;
import com.store.web.utils.DBCPUtils;
import com.store.web.utils.TxQueryRunner;

public class UserDaoImpl implements UserDao{
	private static QueryRunner qr = new TxQueryRunner();

	@Override
	public boolean insert(User user) {
		String sql = "insert into users values(?,?,?,?,?,?,?)";
		Object[] params = {user.getId(),user.getName(),user.getPwd()
						   ,user.getEmail(),user.getTel(),user.getStatus()
						   ,user.getActivationCode() };
		try {
			int row = qr.update(sql, params);
			if(row >= 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User search(String name, String pwd) {
		String sql = "select * from users where name=? and pwd=?";
		Object[] params = {name,pwd};
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<>(User.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean findByName(String name) {
		String sql = "select name from users where name=?";
		try {
			Object query = qr.query(sql, new ScalarHandler(), name);
			if(query!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean findByEmail(String email) {
		String sql = "select email from users where email=?";
		try {
			Object query = qr.query(sql, new ScalarHandler(), email);
			if(query!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean findByTel(String tel) {
		String sql = "select tel from users where tel=?";
		try {
			Object query = qr.query(sql, new ScalarHandler(), tel);
			if(query!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUserByCode(String code) {
		String sql = "select * from users where activationCode=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<>(User.class), code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void setStatus(User user) {
		String sql = "update users SET status = ? , activationCode =? where id=?";
		Object[] params = {1,"",user.getId()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<User> findAllUser() {
		String sql = "select * from users";
		ArrayList<User> list = null;
		try {
			list = (ArrayList<User>) qr.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "update users set name=? ,pwd=?,email=?,tel=?,status=?,activationCode=? where id=? ";
		Object[] params = {user.getName(), user.getPwd(),user.getEmail()
							,user.getTel(),user.getStatus(),user.getActivationCode()
							,user.getId()};
		try {
			int num = qr.update(sql, params);
			if(num>=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String id) {
		String sql = "delete from users where id=?";
		int num =0;
		try {
			num = qr.update(sql, id);
			if(num>=1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
