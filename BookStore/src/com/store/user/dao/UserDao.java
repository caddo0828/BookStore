package com.store.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.store.user.domain.User;
import com.store.web.utils.DBCPUtils;
import com.store.web.utils.TxQueryRunner;

/**
 * 业务逻辑类，用来处理用户数据库的业务逻辑
 * 对应数据库为users
 * @author 老腰
 */
public class UserDao {
	private static QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 检验注册，插入数据,通过创建的用户插入数据
	 * @param user 注册的用户信息
	 * @return 是否注册成功
	 */
	public static boolean insert(User user) {
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

	/**
	 * 根据用户电话号码和密码获取用户
	 * @param name 用户名
	 * @param pwd  用户密码
	 * @return 用户是否存在
	 */
	public static User search(String name,String pwd) {
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
	
	/**
	 * 根据用户名判断用户是否存在，如果存在，表明用户账号名已经被注册，（用户名为第二主键）
	 * @param name 用户注册名
	 * @return 用户名是否已经注册
	 */
	public static boolean findByName(String name) {
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

	/**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param email 注册邮箱号
	 * @return 注册邮箱是否存在
	 */
	public static boolean findByEmail(String email) {
		String sql = "select email from users where email=?";
		try {
			Object query = qr.query(DBCPUtils.getConnection(),sql, new ScalarHandler(), email);
			if(query!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param tel 用户注册电话
	 * @return 电话号码是否已经注册存在
	 * @throws SQLException 
	 */
	public static boolean findByTel(String tel)  {
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
	
	/**
	 * 根据用户的激活码找到对应的用户
	 * @param code  激活码
	 * @return 是否存在用户
	 */
	public static User findUserByCode(String code)  {
		String sql = "select * from users where activationCode=?";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<>(User.class), code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 修改对应用户的激活状态为 “1”,表明已被激活，并且清除激活码
	 * @param user 需要激活的用户
	 */
	public static void setStatus(User user) {
		String sql = "update users SET status = ? , activationCode =? where id=?";
		Object[] params = {1,"",user.getId()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	//查找所有用户
	public static ArrayList<User> findAllUser() {
		String sql = "select * from users";
		ArrayList<User> list = null;
		try {
			list = (ArrayList<User>) qr.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据用户id,修改用户信息(账号名，密码，邮箱地址，电话号码，激活状态，激活码)
	 * @param user 要进行修改的用户
	 * @return 是否修改成功
	 */
	public static boolean updateUser(User user) {
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

	/**
	 * 根据用户id号删除用户
	 * @param id 用户id号
	 * @return
	 */
	public static boolean deleteUser(String id) {
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

