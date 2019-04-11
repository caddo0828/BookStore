package com.store.manager.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.store.manager.domain.Manager;
import com.store.web.utils.TxQueryRunner;
/**
 * ���ݿ������࣬��Ӧ���ݿ��manager���Թ���Ա��ݽ�����֤����
 * @author ����
 */
public class ManagerDao {
	private static QueryRunner qr = new TxQueryRunner();
	
	/**
	 * �ж����ݿ��manager�еĹ���Ա��Ϣ�Ƿ�ƥ�䣬ƥ��ɹ��򷵻���ȷƥ��Ķ���
	 * �������Ϊ��
	 * @param name  ����Ա�˺�
	 * @param pwd   ����Ա����
	 * @return �����˺ţ����˺Ų�ѯ���Ĺ���Ա����
	 */
	public static Manager find(String name,String pwd) {
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
