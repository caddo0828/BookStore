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


public interface UserDao {
	/**
	 * 检验注册，插入数据,通过创建的用户插入数据
	 * @param user 注册的用户信息
	 * @return 是否注册成功
	 */
	boolean insert(User user);
	
	/**
	 * 根据用户电话号码和密码获取用户
	 * @param name 用户名
	 * @param pwd  用户密码
	 * @return 用户是否存在
	 */
	User search(String name,String pwd);
	
	/**
	 * 根据用户名判断用户是否存在，如果存在，表明用户账号名已经被注册，（用户名为第二主键）
	 * @param name 用户注册名
	 * @return 用户名是否已经注册
	 */
	 boolean findByName(String name) ;
	 
	 /**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param email 注册邮箱号
	 * @return 注册邮箱是否存在
	 */
	 boolean findByEmail(String email);
	 
	 /**
	 * 根据用户注册邮箱号判断用户是否存在，如果存在，表明用户邮箱已经被注册
	 * @param tel 用户注册电话
	 * @return 电话号码是否已经注册存在
	 * @throws SQLException 
	 */
	 boolean findByTel(String tel);
	 
	 /**
	 * 根据用户的激活码找到对应的用户
	 * @param code  激活码
	 * @return 是否存在用户
	 */
	 User findUserByCode(String code);
	 
	 /**
	 * 修改对应用户的激活状态为 “1”,表明已被激活，并且清除激活码
	 * @param user 需要激活的用户
	 */
	 void setStatus(User user);
	 
	 ArrayList<User> findAllUser() ;
	 
	 /**
	 * 根据用户id,修改用户信息(账号名，密码，邮箱地址，电话号码，激活状态，激活码)
	 * @param user 要进行修改的用户
	 * @return 是否修改成功
	 */
	 boolean updateUser(User user);
	 
	 /**
	 * 根据用户id号删除用户
	 * @param id 用户id号
	 * @return
	 */ 
	 boolean deleteUser(String id);
}

