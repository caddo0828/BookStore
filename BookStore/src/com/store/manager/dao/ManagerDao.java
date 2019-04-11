package com.store.manager.dao;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.store.manager.domain.Manager;
import com.store.web.utils.TxQueryRunner;
/**
 * 数据库表操作类，对应数据库表manager，对管理员身份进行验证处理
 * @author 老腰
 */
public class ManagerDao {
	private static QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 判断数据库表manager中的管理员信息是否匹配，匹配成功则返回正确匹配的对象
	 * 否则对象为空
	 * @param name  管理员账号
	 * @param pwd   管理员密码
	 * @return 根据账号，及账号查询到的管理员对象
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
