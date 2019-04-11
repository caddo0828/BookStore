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
 * ҵ���߼��࣬���������û����ݿ��ҵ���߼�
 * ��Ӧ���ݿ�Ϊusers
 * @author ����
 */
public class UserDao {
	private static QueryRunner qr = new TxQueryRunner();
	
	/**
	 * ����ע�ᣬ��������,ͨ���������û���������
	 * @param user ע����û���Ϣ
	 * @return �Ƿ�ע��ɹ�
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
	 * �����û��绰����������ȡ�û�
	 * @param name �û���
	 * @param pwd  �û�����
	 * @return �û��Ƿ����
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
	 * �����û����ж��û��Ƿ���ڣ�������ڣ������û��˺����Ѿ���ע�ᣬ���û���Ϊ�ڶ�������
	 * @param name �û�ע����
	 * @return �û����Ƿ��Ѿ�ע��
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
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param email ע�������
	 * @return ע�������Ƿ����
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
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param tel �û�ע��绰
	 * @return �绰�����Ƿ��Ѿ�ע�����
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
	 * �����û��ļ������ҵ���Ӧ���û�
	 * @param code  ������
	 * @return �Ƿ�����û�
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
	 * �޸Ķ�Ӧ�û��ļ���״̬Ϊ ��1��,�����ѱ�����������������
	 * @param user ��Ҫ������û�
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
	
	//���������û�
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
	 * �����û�id,�޸��û���Ϣ(�˺��������룬�����ַ���绰���룬����״̬��������)
	 * @param user Ҫ�����޸ĵ��û�
	 * @return �Ƿ��޸ĳɹ�
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
	 * �����û�id��ɾ���û�
	 * @param id �û�id��
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

