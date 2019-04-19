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
	 * ����ע�ᣬ��������,ͨ���������û���������
	 * @param user ע����û���Ϣ
	 * @return �Ƿ�ע��ɹ�
	 */
	boolean insert(User user);
	
	/**
	 * �����û��绰����������ȡ�û�
	 * @param name �û���
	 * @param pwd  �û�����
	 * @return �û��Ƿ����
	 */
	User search(String name,String pwd);
	
	/**
	 * �����û����ж��û��Ƿ���ڣ�������ڣ������û��˺����Ѿ���ע�ᣬ���û���Ϊ�ڶ�������
	 * @param name �û�ע����
	 * @return �û����Ƿ��Ѿ�ע��
	 */
	 boolean findByName(String name) ;
	 
	 /**
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param email ע�������
	 * @return ע�������Ƿ����
	 */
	 boolean findByEmail(String email);
	 
	 /**
	 * �����û�ע��������ж��û��Ƿ���ڣ�������ڣ������û������Ѿ���ע��
	 * @param tel �û�ע��绰
	 * @return �绰�����Ƿ��Ѿ�ע�����
	 * @throws SQLException 
	 */
	 boolean findByTel(String tel);
	 
	 /**
	 * �����û��ļ������ҵ���Ӧ���û�
	 * @param code  ������
	 * @return �Ƿ�����û�
	 */
	 User findUserByCode(String code);
	 
	 /**
	 * �޸Ķ�Ӧ�û��ļ���״̬Ϊ ��1��,�����ѱ�����������������
	 * @param user ��Ҫ������û�
	 */
	 void setStatus(User user);
	 
	 ArrayList<User> findAllUser() ;
	 
	 /**
	 * �����û�id,�޸��û���Ϣ(�˺��������룬�����ַ���绰���룬����״̬��������)
	 * @param user Ҫ�����޸ĵ��û�
	 * @return �Ƿ��޸ĳɹ�
	 */
	 boolean updateUser(User user);
	 
	 /**
	 * �����û�id��ɾ���û�
	 * @param id �û�id��
	 * @return
	 */ 
	 boolean deleteUser(String id);
}

